package routes

import org.jsoup.nodes.Document
import org.scalatest._
import play.api.libs.ws.WSResponse
import play.api.test.Helpers._
import support.{HttpRequest, IntegrationTest}
import support.stubs.StubAuth._
import support.stubs.StubBta._
import support.JsoupHelper._

class MainPageISpec extends FeatureSpec with MustMatchers with GivenWhenThen with IntegrationTest {

  feature("Help and Contact Page") {
    scenario("loads correctly") {
      Given("User is authorised")
      mockAuthorised()

      And("Service info is available")
      mockGetServiceInfo()

      When("the Main page is accessed")
      val result: WSResponse = HttpRequest.get("/")

      Then("we get an OK response")
      result.status mustBe OK

      Then("we get the main page with the service info bar")
      val doc: Document = result.bodyAsDom

      // todo add an id to the page for test purposes instead of using content
      doc.getElementsByTag("h1").text() mustBe "Help and contact"
      doc.select(".service-info").first().children().size() must not be 0
    }

    scenario("Navigation bar ist kaput") {
      Given("User is authorised")
      mockAuthorised()

      And("Service info is not available")
      mockGetServiceInfoFailure()

      When("the Main page is accessed")
      val result: WSResponse = HttpRequest.get("/")

      Then("we get an OK response")
      result.status mustBe OK

      Then("we get the main page without the service info bar")
      val doc: Document = result.bodyAsDom

      // todo add an id to the page for test purposes instead of using content
      doc.getElementsByTag("h1").text() mustBe "Help and contact"
      doc.select(".service-info").first().children().size() mustBe 0
    }
  }

}
