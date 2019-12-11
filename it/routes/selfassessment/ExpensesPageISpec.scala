package routes.selfassessment

import org.jsoup.nodes.Document
import org.scalatest.{FeatureSpec, GivenWhenThen, MustMatchers}
import play.api.libs.ws.WSResponse
import play.api.test.Helpers._
import support.JsoupHelper._
import support.stubs.StubAuth.mockAuthorised
import support.stubs.StubBta.mockGetServiceInfo
import support.{HttpRequest, IntegrationTest}

class ExpensesPageISpec extends FeatureSpec with MustMatchers with GivenWhenThen with IntegrationTest {

  feature("Expenses Page") {
    scenario("loads correctly") {
      Given("User is authorised")
      mockAuthorised()

      And("Service info is available")
      mockGetServiceInfo()

      When("the Expenses page is accessed")
      val result: WSResponse = HttpRequest.get("/self-assessment/expenses")

      Then("we get an OK response")
      result.status mustBe OK

      Then("we get the expenses page")
      val doc: Document = result.bodyAsDom

      // todo add an id to the page for test purposes instead of using content
      doc.getElementsByTag("h1").text() mustBe "Expenses"
    }
  }

}
