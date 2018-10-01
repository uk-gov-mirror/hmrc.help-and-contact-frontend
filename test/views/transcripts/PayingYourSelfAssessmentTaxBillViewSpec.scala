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

package views.transcripts

import play.twirl.api.HtmlFormat
import views.behaviours.ViewBehaviours
import views.html.transcripts._
import collection.JavaConverters._

class PayingYourSelfAssessmentTaxBillViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "payingYourSelfAssessmentTaxBillTranscript"

  def createView = () => paying_your_self_assessment_tax_bill(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "PayingYourSelfAssessment view" must {
    behave like normalPage(createView, messageKeyPrefix)

    "have correct content" in {
      val doc = asDocument(createView())

      val elements = doc.getElementsByTag("article").first().getElementsByTag("p").asScala.toList.map(_.text())

      val contentList = List(
        "This is one of a series of videos about online Self Assessment.",
        "The term ‘tax bill’ refers to your bill for Income Tax and any Class 2 and class 4 National Insurance you may " +
          "be due to pay.",
        "It may also include student loan repayments and capital gains tax.",
        "The deadlines for paying are:",
        "31 January for a tax bill for the previous year and your first payment on account",
        "31 July for your second payment on account",
        "Make sure you pay HMRC on time. You’ll be charged interest and may have to pay a penalty if your payment is late.",
        "You don’t have to wait until 31 January or 31 July to pay. If you do, you might miss the deadline, depending on how you pay.",
        "As soon as you have filled in your tax return online, you can see your tax calculation and how much you are due to pay.",
        "This won’t include any payments you might have already made.",
        "For payments to reach HMRC on the same or next day, you should use any of these methods:",
        "You need a paying-in slip from HMRC to pay at a bank or building society.",
        "If you have time to allow 3 working days for your payment to reach HMRC, you can use one of these methods:",
        "If paying by post, you can include a letter with your payment to ask for a receipt from HMRC.",
        "The first time you set up a direct debit, you need to allow 5 working days for payments to reach HMRC.",
        "If you prefer to pay regularly throughout the year, you can use a budget payment plan.",
        "It’s easy to use and really flexible. Find out more in our video, ‘Budgeting for your Self Assessment tax bill’.",
        "You can view your HMRC online account to check if your payment’s been received – it should show as paid 4 – 7 working days later.",
        "Online payment services may be slow at busy times. Check if there are any delays by referring to ‘service availability and " +
          "issues’ on the GOV.UK website.",
        "Don’t wait until 31 January to do this!",
        "You’ll find more help and support on GOV.UK.",
        "Webinars and other videos about Self Assessment are available from HMRC.",
        "Thanks for watching."
      )

      contentList.zipAll(elements, "", "").foreach {
        case (content, element) => element mustBe content
      }
    }
  }
}