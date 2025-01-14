/*
 * Copyright 2021 HM Revenue & Customs
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

import play.twirl.api.{Html, HtmlFormat}
import views.behaviours.ViewBehaviours
import views.html.paye_cis_refunds

class PayeCisRefundsViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "paye.cis.refunds"

  val serviceInfoContent: Option[Html] = Some(Html("<div>Service info content</div>"))

  def createView(): () => HtmlFormat.Appendable =
    () =>  paye_cis_refunds.apply()(messages)


  "Paye CIS and Refunds view" must {

    "contain heading ID" in {
      val doc = asDocument(createView()())
      doc.getElementsByTag("h1").attr("id") mustBe "paye-cis-refunds"
    }

    "have correct h2 headings" in {
      val doc      = asDocument(createView()())
      val headings = doc.getElementsByTag("h2").toString
      headings must include("PAYE refunds")
      headings must include("Apply for a refund by post")
      headings must include("CIS refunds")
    }

    "have correct content" in {
      val doc = asDocument(createView()())
      doc.text() must include("You will only get a refund if you do not owe money for other taxes.")
    }

    "have correct links" in {
      val doc = asDocument(createView()())
      assertLinkByLinkText(
        doc,
        "More about refunds (opens in new tab)",
        "https://www.gov.uk/payroll-errors/correcting-payments-to-hmrc"
      )
      assertLinkByLinkText(
        doc,
        "How to claim a CIS refund (opens in new tab)",
        "https://www.gov.uk/guidance/claim-a-refund-of-construction-industry-scheme-deductions-if-youre-a-limited-company"
      )
    }
  }
}
