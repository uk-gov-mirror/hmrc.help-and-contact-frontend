/*
 * Copyright 2023 HM Revenue & Customs
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
import views.html.transcripts.add_a_tax

import scala.collection.JavaConverters._

class AddATaxViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "add.a.tax.transcript"

  def createView = () => inject[add_a_tax].apply(frontendAppConfig)(Some(HtmlFormat.empty))(fakeRequest, messages)

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
      val bulletList4 = List("if you’re currently submitting VAT returns", "the total from your latest VAT return and,", "the last month of your latest VAT accounting period")

      val bulletsList = bulletList1 ++ bulletList2 ++ bulletList3 ++ bulletList4

      val contentList = List(
        "You can add more than 40 services to your business tax account. Here’s how you add the 4 main ones:",
        "This is the business tax account homepage. Any services you add will appear here.",
        "For some services, we’ll send you an activation pin within 10 working days. Once you put the pin in, you can start using the service.",
        "Let‘s start. Select ‘add a tax, duty or scheme’.",
        "Select ‘Corporation Tax’. On the following screen, enter your 10-digit Corporation Tax Unique Taxpayer Reference.",
        "Then enter either:",
        "If your company is not based in the UK – then select this option.",
        "To add Self Assessment, you’ll need your Unique Taxpayer Reference number.",
        "If you don’t have it, you can’t add this service at this time. Instead you’ll be directed to register for Self Assessment.",
        "Once you receive your Unique Taxpayer Reference letter, log back into your business tax account and enter your 10-digit Taxpayer Reference number here.",
        "Then select if you’re:",
        "If you’re an individual or sole trader. You’ll be asked to enter your National Insurance number. If you don’t have it, you’ll be asked for your postcode.",
        "Next, confirm your identity to get access to Self Assessment right away. But don’t worry if you can’t do this. You can still request an activation pin in the post.",
        "To add PAYE for Employers, select this option (Employers or intermediaries, for example PAYE for employers, or CIS).",
        "If you have a PAYE reference number, enter it here, (Request access to PAYE for Employers page) as well as the HMRC office number and your Accounts office reference number.",
        "If you don’t have a PAYE reference number, you’ll be directed to register as an employer.",
        "Once you’ve received your reference numbers, log back into your Business Tax Account and add the details.",
        "And to add VAT, select the type of VAT service you want and enter your 9-digit VAT number.",
        "You’re then asked to enter the date you became VAT registered and the UK postcode for where your business is registered for VAT.",
        "You’ll also need to tell us:",
        "That’s it.",
        "You can find more information about the Business Tax Account on GOV.UK"

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


