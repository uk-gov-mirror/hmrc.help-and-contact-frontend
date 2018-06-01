/*
 * Copyright 2018 HM Revenue & Customs
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

package views

import play.twirl.api.HtmlFormat
import views.behaviours.ViewBehaviours
import views.html.help_and_contact

import scala.collection.JavaConverters._

class helpAndContactViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "help_and_contact"

  def createView = () => help_and_contact(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "Help and contact view" must {

    behave like normalPage(createView, messageKeyPrefix)

    "have correct h2 headings" in {
      val listOfHeadings: List[String] = List(
        "Self Assessment",
        "VAT",
        "Corporation Tax",
        "PAYE for employers"
      )
      val doc = asDocument(createView())
      val headings = doc.getElementsByTag("article").first.getElementsByTag("h2").asScala.toList.map(_.text())

      headings mustBe listOfHeadings
    }

    "have correct links" in {
      val doc = asDocument(createView())
      assertLinkById(
        doc,
        "register-deregister-self-assessment",
        "Register or deregister for Self Assessment",
        "/business-account/help/self-assessment/register-or-deregister",
        "HelpAndContact:click:RegisterDeregisterSelfAssessment")
      assertLinkById(
        doc,
        "how-to-pay-self-assessment",
        "How to pay your Self Assessment",
        "/business-account/help/self-assessment/how-to-pay",
        "HelpAndContact:click:HowToPaySelfAssessment")
      assertLinkById(
        doc,
        "expenses",
        "Expenses",
        "/business-account/help/self-assessment/expenses",
        "HelpAndContact:click:Expenses")
      assertLinkById(
        doc,
        "evidence-of-income",
        "Get evidence of your income (SA302)",
        "/business-account/help/self-assessment/evidence-of-income",
        "HelpAndContact:click:EvidenceOfIncome")
      assertLinkById(
        doc,
        "help-with-sa-return",
        "Help with your Self Assessment return",
        "/business-account/help/self-assessment/help-with-return",
        "HelpAndContact:click:HelpWithSelfAssessmentReturn")
      assertLinkById(
        doc,
        "contact-about-self-assessment",
        "Contact HMRC about Self Assessment",
        "/business-account/help/self-assessment/contact-hmrc",
        "HelpAndContact:click:ContactAboutSelfAssessment")

      assertLinkById(
        doc,
        "register-deregister-vat",
        "Register or deregister for VAT",
        "/business-account/help/vat/register-or-deregister",
        "HelpAndContact:click:RegisterDeregisterVat")
      assertLinkById(
        doc,
        "how-to-pay-vat",
        "How to pay VAT and deadlines",
        "/business-account/help/vat/how-to-pay",
        "HelpAndContact:click:HowToPayVat")
      assertLinkById(
        doc,
        "contact-about-vat",
        "Contact HMRC about VAT",
        "/business-account/help/vat/questions",
        "HelpAndContact:click:ContactAboutVat")

      assertLinkById(
        doc,
        "register-or-tell-no-longer-trading-corporation-tax",
        "Register for Corporation Tax or tell HMRC you are no longer trading",
        "/business-account/help/corporation-tax/register-or-tell-hmrc-you-are-no-longer-trading",
        "HelpAndContact:click:RegisterOrTellNoLongerTradingCorporationTax")
      assertLinkById(
        doc,
        "how-to-pay-corporation-tax",
        "How to pay your Corporation Tax",
        "/business-account/help/corporation-tax/how-to-pay",
        "HelpAndContact:click:HowToPayCorporationTax")
      assertLinkById(
        doc,
        "contact-about-corporation-tax",
        "Contact HMRC about Corporation Tax",
        "/business-account/help/corporation-tax/contact-hmrc",
        "HelpAndContact:click:ContactAboutCorporationTax")

      assertLinkById(
        doc,
        "view-check-or-correct-epaye",
        "View, check or correct your submissions",
        "/business-account/help/epaye/view-check-correct-submissions",
        "HelpAndContact:click:ViewCheckCorrectEpaye")
      assertLinkById(
        doc,
        "paye-and-cis-refunds",
        "PAYE refunds and Construction Industry Scheme (CIS) refunds",
        "/business-account/help/epaye/refunds",
        "HelpAndContact:click:PayeAndCisRefunds")
      assertLinkById(
        doc,
        "contact-about-epaye",
        "Contact HMRC about PAYE",
        "/business-account/help/epaye/contact-hmrc",
        "HelpAndContact:click:ContactAboutEpaye")
    }
  }
}
