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

package views.vat

import play.twirl.api.{Html, HtmlFormat}
import uk.gov.hmrc.domain.Vrn
import views.behaviours.ViewBehaviours
import views.html.vat.payments_and_deadlines

class paymentsAndDeadlinesViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "vat.payments_and_deadlines"

  def createView = () => payments_and_deadlines(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "VAT Payments and Deadlines view" must {

    behave like normalPage(createView, messageKeyPrefix)

    "contain correct content" in {
      val doc = asDocument(createView())
      doc.getElementsByTag("h1").first().text() mustBe "How to pay VAT and deadlines"
    }

    "contain the 'online or telephone banking' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc, "online-or-telephone-banking", "online or telephone banking", "https://www.gov.uk/pay-vat/bank-details", expectedGAEvent = "HelpVatPaymentsDeadlinesContentLink:click:OnlineTelephoneBanking")
    }

    "contain the 'CHAPS' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc, "chaps", "CHAPS", "https://www.gov.uk/pay-vat/bank-details", expectedGAEvent = "HelpVatPaymentsDeadlinesContentLink:click:Chaps")
    }

    "contain the 'online by debit or credit card' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc, "debit-or-credit-card", "online by debit or credit card", "https://www.gov.uk/pay-vat/by-debit-or-credit-card-online", expectedGAEvent = "HelpVatPaymentsDeadlinesContentLink:click:OnlineDebitCreditCard")
    }

    "contain the 'bank or building society' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc, "bank-or-building-society", "bank or building society", "https://www.gov.uk/pay-vat/bank-or-building-society", expectedGAEvent = "HelpVatPaymentsDeadlinesContentLink:click:BankBuildingSociety")
    }

    "contain the 'direct debit' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc, "direct-debit", "Direct Debit", "https://www.gov.uk/pay-vat/direct-debit", expectedGAEvent = "HelpVatPaymentsDeadlinesContentLink:click:DirectDebit")
    }

    "contain the 'Bacs' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc, "bacs", "Bacs", "https://www.gov.uk/pay-vat/bank-details", expectedGAEvent = "HelpVatPaymentsDeadlinesContentLink:click:Bacs")
    }

    "contain the 'standing order' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc, "standing-order", "standing order", "https://www.gov.uk/pay-vat/standing-order", expectedGAEvent = "HelpVatPaymentsDeadlinesContentLink:click:StandingOrder")
    }

    "contain the 'get a refund of VAT paid in another EU country' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc, "get-eu-vat-refund", "get a refund of VAT paid in another EU country", "https://www.gov.uk/guidance/vat-refunds-for-uk-businesses-buying-from-other-eu-countries", expectedGAEvent = "HelpVatPaymentsDeadlinesContentLink:click:GetaRefund")
    }

    "contain the 'input tax' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc, "input-tax", "input tax", "https://www.gov.uk/government/publications/vat-notice-700-the-vat-guide/vat-notice-700-the-vat-guide#input-tax-introduction-and-general-rules", expectedGAEvent = "HelpVatPaymentsDeadlinesContentLink:click:InputTax")
    }

  }

}
