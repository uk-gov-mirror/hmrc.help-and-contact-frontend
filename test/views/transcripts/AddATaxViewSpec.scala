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

package views.transcripts

import play.twirl.api.HtmlFormat
import views.behaviours.ViewBehaviours
import views.html.transcripts.{add_a_tax, viewing_your_self_assessment_calculation}

import scala.collection.JavaConverters._

class AddATaxViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "add.a.tax.transcript"

  def createView = () => inject[add_a_tax].apply(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "Add a tax view" must {
    behave like normalPage(createView, messageKeyPrefix)

    "contain heading ID" in {
      val doc = asDocument(createView())
      doc.getElementsByTag("h1").attr("id") mustBe "add-a-tax-transcript"
    }

    "have correct content" in {
      val doc = asDocument(createView())

      val elements = doc.getElementsByTag("article").first().getElementsByTag("p").asScala.toList.map(_.text())
      val bullets = doc.getElementsByTag("article").first().getElementsByTag("li").asScala.toList.map(_.text())

      val bulletList1 = List("Corporation Tax", "Self Assessment", "PAYE for employers, and", "VAT")
      val bulletList2 = List("your Company Registration Number – sent to you by Companies House, or", "the postcode of your registered office.")
      val bulletList3 = List("an individual or sole trader", "partnership, or", "trust")
      val bulletList4 = List("your National Insurance number, or", "the postcode you used when registering for Self Assessment")
      val bulletList5 = List("the HMRC office number, and", "your Accounts Office reference number")
      val bulletList6 = List("VAT Registration Number", "postcode on your Certificate of Registration", "date of registration", "month your last VAT Return ended on, and", "amount from Box 5 on your last VAT Return")

      val bulletsList = bulletList1 ++ bulletList2 ++ bulletList3 ++ bulletList4 ++ bulletList5 ++ bulletList6

      val contentList = List(
        "You can add more than 40 services to your business tax account. Here’s how you add the 4 main ones:",
        "This is the business tax account homepage. Any services you add will appear here.",
        "Each time you add a service, we’ll send you an activation pin within 10 days. Once you put the pin in, you can start using the service.",
        "Let‘s start. Select ‘add a tax, duty or scheme’.",
        "Select ‘Corporation Tax’. On the following screen, enter your 10-digit Corporation Tax Unique Taxpayer Reference.",
        "Then enter either:",
        "If your company is not based in the UK – then select this option. That’s it.",
        "To add Self Assessment, you’ll need your Unique Taxpayer Reference number.",
        "If you don’t have it, you can’t add this service at this time. Instead you’ll be directed to register for Self Assessment.",
        "Once you receive your Unique Taxpayer Reference letter, log back into your business tax account and enter your 10-digit Taxpayer Reference number here.",
        "Then select if you’re:",
        "Re-enter your Taxpayer Reference number followed by either:",
        "If you live abroad, select this option. That’s it.",
        "To add PAYE for employers, select this option.",
        "If you’ve a PAYE reference number, enter it here, as well as:",
        "If you don’t have a PAYE reference number, you’ll be directed to register as an employer. Once you’ve received your reference numbers, log back into your Business Tax Account and add the details. That’s it.",
        "To add VAT, select the ‘type’ of VAT service you want.",
        "Enter your 9-digit VAT number, followed by the:",
        "That’s it.",
        "You can find more information about the business tax account on GOV.UK"

      )

      contentList.zipAll(elements, "", "").foreach {
        case (content, element) => element mustBe content
      }

      bulletsList.zipAll(bullets, "", "").foreach {
        case (expected, actual) => actual mustBe expected
      }
    }
  }
}


