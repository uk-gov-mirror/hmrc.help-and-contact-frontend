/*
 * Copyright 2022 HM Revenue & Customs
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

package views.ct

import play.twirl.api.HtmlFormat
import views.behaviours.ViewBehaviours
import views.html.ct.register_or_deregister_corporation_tax
import collection.JavaConverters._

class RegisterOrDeregisterCorporationTaxViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "ct.register_deregister"

  def createView = () => inject[register_or_deregister_corporation_tax].apply(frontendAppConfig)(Some(HtmlFormat.empty))(fakeRequest, messages)

  "/help/corporation-tax/register-or-tell-hmrc-you-are-no-longer-trading" must {

    behave like normalPage(createView, messageKeyPrefix)

    "contain heading ID" in {
      val doc = asDocument(createView())
      doc.getElementsByTag("h1").attr("id") mustBe "register-deregister-ct"
    }

    "have correct h2 headings" in {
      val listOfHeadings: List[String] = List(
        "Register for Corporation Tax with HMRC",
        "What you need to tell HMRC when registering for Corporation Tax",
        "Add Corporation Tax to your business tax account",
        "Tell HMRC you are no longer trading"
      )

      val doc = asDocument(createView())
      val headings = doc.getElementsByTag("article").first.getElementsByTag("h2").asScala.toList.map(_.text())

      headings mustBe listOfHeadings
    }

    "have correct content" in {
      val doc = asDocument(createView())
      doc.text() must include("When you have registered your company (opens in new tab) with Companies House, you must register for Corporation Tax with HM Revenue and Customs (HMRC) within 3 months of being active.")
      doc.text() must include("buying")
      doc.text() must include("selling")
      doc.text() must include("advertising")
      doc.text() must include("renting a property")
      doc.text() must include("employing someone")
      doc.text() must include("similar trading activity")
      doc.text() must include("Check if you’re unsure what counts as being active (opens in new tab).")
      doc.text() must include("You must also register your company for Corporation Tax with HMRC if your company is registered as dormant, but is now active")
      doc.text() must include("You may get a penalty if you register late.")
      doc.text() must include("You’ll need your company’s 10-digit Unique Taxpayer Reference (UTR).")
      doc.text() must include("You usually get your UTR from HMRC within a few days of registering your company with Companies House.")
      doc.text() must include("Request your company’s UTR online if you did not get one after registering your company.")
      doc.text() must include("You’ll also need:")
      doc.text() must include("your company’s registration number (CRN)")
      doc.text() must include("the date you started to be active - your company’s first accounting period (opens in new tab) will start from this date")
      doc.text() must include("the date your annual accounts are made up to (the accounts preparation date)")
      doc.getElementsByTag("article").first.getElementsByTag("h3").text() must include("What happens next")
      doc.text() must include("HMRC will tell you the deadline for paying Corporation Tax.")
      doc.text() must include("You must file a Company Tax Return (opens in new tab), even if you make a loss or have no Corporation Tax to pay.")
      doc.text() must include("Add Corporation Tax to your business tax account to:")
      doc.text() must include("file your Company Tax return online")
      doc.text() must include("make a payment online")
      doc.text() must include("To stop paying Corporation Tax, you must close the limited company (opens in new tab).")

    }

    "have correct links" in {
      val doc = asDocument(createView())
      assertLinkById(
        doc,
        "register-company-house",
        "registered your company (opens in new tab)",
        "https://www.gov.uk/limited-company-formation/register-your-company",
        expectedOpensInNewTab = true)

      assertLinkById(
        doc,
        "register-for-hmrc-taxes",
        "register for Corporation Tax",
        "http://localhost:8081/portal/business-registration/select-taxes?lang=eng",

        expectedIsExternal = false,
        expectedOpensInNewTab = false)

      assertLinkById(
        doc,
        "check-unsure-active",
        "Check if you’re unsure what counts as being active (opens in new tab)",
        "https://www.gov.uk/guidance/corporation-tax-trading-and-non-trading",

        expectedOpensInNewTab = true)

      assertLinkById(
        doc,
        "ask-for-utr",
        "Request your company’s UTR online",
        "http://localhost:9200/ask-for-copy-of-your-corporation-tax-utr",
        expectedIsExternal = false,
        expectedOpensInNewTab = false)

      assertLinkById(
        doc,
        "accounting-period",
        "accounting period (opens in new tab)",
        "https://www.gov.uk/corporation-tax-accounting-period",
        expectedOpensInNewTab = true)

      assertLinkById(
        doc,
        "company-tax-returns",
        "Company Tax Return (opens in new tab)",
        "https://www.gov.uk/company-tax-returns",
        expectedOpensInNewTab = true)

      assertLinkById(
        doc,
        "add-ct-bta",
        "Add Corporation Tax to your business tax account",
        "http://localhost:9020/business-account/add-tax")

      assertLinkById(
        doc,
        "close-limited-company",
        "close the limited company (opens in new tab)",
        "https://www.gov.uk/closing-a-limited-company",
        expectedOpensInNewTab = true)
    }
  }
}
