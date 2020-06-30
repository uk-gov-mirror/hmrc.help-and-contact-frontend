/*
 * Copyright 2020 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers

import models.HelpCategory
import models.requests.{AuthenticatedRequest, ServiceInfoRequest}
import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.mockito.MockitoSugar
import play.api.mvc.AnyContentAsEmpty
import play.api.test.FakeRequest
import play.api.test.Helpers._
import play.twirl.api.HtmlFormat
import uk.gov.hmrc.domain.SaUtr
import uk.gov.hmrc.http.HeaderCarrier
import views.ViewSpecBase
import views.html.ct._
import views.html.epaye._
import views.html.help_and_contact
import views.html.sa._
import views.html.vat._

class HelpAndContactControllerSpec extends ControllerSpecBase with MockitoSugar with ScalaFutures with ViewSpecBase {

  implicit val hc: HeaderCarrier = HeaderCarrier()

  def SUT: HelpAndContactController = inject[HelpAndContactController]

  def fakeServiceInfoRequest(utr: Option[SaUtr] = None): ServiceInfoRequest[AnyContentAsEmpty.type] =
    ServiceInfoRequest[AnyContentAsEmpty.type](AuthenticatedRequest(FakeRequest(), utr, Some("user@example.com")), HtmlFormat.empty)

  def pageRedirect(helpCategory: HelpCategory, page: String, destinationUrl: String) =
    "HelpAndContactController onPageLoad" must {
      s"redirect /business-account/help/$helpCategory/$page to $destinationUrl" in {
        val result = SUT.onPageLoad(helpCategory, page).apply(fakeRequest)
        status(result) mustBe MOVED_PERMANENTLY
        redirectLocation(result).get mustBe destinationUrl
      }
    }

  def pageRouter(helpCategory: HelpCategory, page: String, view: () => HtmlFormat.Appendable) =
    "HelpAndContactController onPageLoad" must {
      s"display the correct $helpCategory view for /$page" in {
        val result = SUT.onPageLoad(helpCategory, page).apply(fakeRequest)
        status(result) mustBe OK
        contentAsString(result) mustBe view().toString
      }
    }

  def pageRouterWithEnrolments(helpCategory: HelpCategory,
                               page: String,
                               view: () => HtmlFormat.Appendable,
                               requestToApply: ServiceInfoRequest[AnyContentAsEmpty.type]) =
    "HelpAndContactController onPageLoad" must {
      s"display the correct $helpCategory view for /$page" in {
        val result = SUT.onPageLoad(helpCategory, page)(requestToApply)
        status(result) mustBe OK
        contentAsString(result) mustBe view().toString
      }
    }

  "HelpAndContact Controller" must {

    "return 404 for a page that does not exist" in {
      HelpCategory.values.foreach { category =>
        val result = SUT.onPageLoad(category, "abcdefgh").apply(fakeRequest)
        status(result) mustBe NOT_FOUND
      }
    }

    "serve the main page" in {
      val result = SUT.mainPage().apply(fakeRequest)
      status(result) mustBe OK
      contentAsString(result) mustBe inject[help_and_contact]
        .apply(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)
        .toString()
    }
  }

  behave like pageRouter(
    HelpCategory.VAT,
    "how-to-pay",
    () => inject[payments_and_deadlines].apply(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)
  )

  behave like pageRouter(
    HelpCategory.VAT,
    "register-or-deregister",
    () => inject[register_or_deregister].apply(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)
  )

  behave like pageRouter(
    HelpCategory.SelfAssessment,
    "register-or-deregister",
    () => inject[register_deregister].apply(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)
  )

  behave like pageRouter(
    HelpCategory.SelfAssessment,
    "how-to-pay",
    () =>
      inject[how_to_pay_self_assessment].apply(frontendAppConfig)(HtmlFormat.empty)(fakeServiceInfoRequest(Some(SaUtr("abcdefgh"))), messages)
  )

  behave like pageRouter(
    HelpCategory.SelfAssessment,
    "help-with-return",
    () =>
      inject[help_with_your_self_assessment_tax_return].apply(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)
  )

  behave like pageRouter(
    HelpCategory.SelfAssessment,
    "expenses",
    () => inject[expenses].apply(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)
  )

  behave like pageRouter(
    HelpCategory.CorporationTax,
    "contact-hmrc",
    () => inject[contact_hmrc_about_ct].apply(frontendAppConfig)(HtmlFormat.empty)(fakeServiceInfoRequest(), messages)
  )

  behave like pageRouter(
    HelpCategory.CorporationTax,
    "how-to-pay",
    () =>
      inject[how_to_pay_corporation_tax].apply(frontendAppConfig)(HtmlFormat.empty)(fakeServiceInfoRequest(), messages)
  )

  behave like pageRouter(
    HelpCategory.CorporationTax,
    "register-or-tell-hmrc-you-are-no-longer-trading",
    () =>
      inject[register_or_deregister_corporation_tax]
        .apply(frontendAppConfig)(HtmlFormat.empty)(fakeServiceInfoRequest(), messages)
  )

  behave like pageRouter(
    HelpCategory.Epaye,
    "refunds",
    () => inject[paye_and_cis_refunds].apply(frontendAppConfig)(HtmlFormat.empty)(fakeServiceInfoRequest(), messages)
  )

  behave like pageRouter(
    HelpCategory.Epaye,
    "view-check-correct-submissions",
    () =>
      inject[view_check_correct_submissions]
        .apply(frontendAppConfig, Some("user@example.com"))(HtmlFormat.empty)(fakeServiceInfoRequest(), messages)
  )

  behave like pageRedirect(
    HelpCategory.Epaye,
    "check-submissions",
    "/business-account/help/epaye/view-check-correct-submissions"
  )

  behave like pageRedirect(
    HelpCategory.Epaye,
    "paye-refund",
    "/business-account/help/epaye/refunds"
  )

  behave like pageRedirect(
    HelpCategory.Epaye,
    "latency",
    "/business-account/help/epaye/view-check-correct-submissions"
  )

  behave like pageRedirect(
    HelpCategory.Epaye,
    "",
    "/business-account/help"
  )
  behave like pageRedirect(
    HelpCategory.Epaye,
    "change-employee-circumstances",
    "/business-account/epaye/change-employee-circumstances"
  )

  "behave appropriately for enrolments" when {
    "the user has an SA enrolment" must {
      val testUtr = Some(SaUtr("abcdefgh"))

      behave like pageRouterWithEnrolments(
        HelpCategory.SelfAssessment,
        "evidence-of-income",
        () =>
          inject[sa_evidence].apply(frontendAppConfig, true, "http://localhost:9020/business-account/self-assessment")(
            HtmlFormat.empty
          )(fakeServiceInfoRequest(testUtr), messages),
        fakeServiceInfoRequest(testUtr)
      )
    }
  }
}
