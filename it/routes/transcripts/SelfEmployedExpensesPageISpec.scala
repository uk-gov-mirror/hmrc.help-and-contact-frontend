package routes.transcripts

import org.jsoup.nodes.Document
import org.scalatest.{FeatureSpec, GivenWhenThen, MustMatchers}
import play.api.libs.ws.WSResponse
import play.api.test.Helpers._
import support.JsoupHelper._
import support.stubs.StubAuth.mockAuthorised
import support.stubs.StubBta.mockGetServiceInfo
import support.{HttpRequest, IntegrationTest}

class SelfEmployedExpensesPageISpec
    extends FeatureSpec
    with MustMatchers
    with GivenWhenThen
    with IntegrationTest {

  feature("Expenses if you're self employed  page") {
    scenario("loads correctly") {
      Given("user is authorised")
      mockAuthorised()

      And("service info loads correctly")
      mockGetServiceInfo()

      When("Expenses if you're self employed  page is called")
      val result: WSResponse =
        HttpRequest.get("/transcript/expenses-if-you-are-self-employed")

      Then("we get an OK response")
      result.status mustBe OK

      Then("we get the expenses if you're self employed page")
      val doc: Document = result.bodyAsDom

      // todo add an id to the page for test purposes instead of using content
      doc
        .getElementsByTag("h1")
        .text() mustBe "What expenses can I include in my Self Assessment tax return - video transcript"
    }
  }

}
