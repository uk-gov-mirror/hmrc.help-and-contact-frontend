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

package views.transcripts

import play.twirl.api.HtmlFormat
import views.behaviours.ViewBehaviours
import views.html.transcripts.budgeting_your_self_assessment_tax_bill
import collection.JavaConverters._

class BudgetingYourSelfAssessmentTaxBillViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "budgetingYourSelfAssessmentTaxBillTranscript"

  def createView = () => budgeting_your_self_assessment_tax_bill(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "BudgetingYourSelfAssessment view" must {
    behave like normalPage(createView, messageKeyPrefix)

    "contain heading ID" in {
      val doc = asDocument(createView())
      doc.getElementsByTag("h1").attr("id") mustBe "budgeting-your-sa-tax-bill-transcript"
    }

    "have correct content" in {
      val doc = asDocument(createView())

      val elements = doc.getElementsByTag("article").first().getElementsByTag("p").asScala.toList.map(_.text())

      val contentList = List(
        "This is one of a series of videos about online Self Assessment.",
        "Why is it so important to budget for payments to HMRC early on? Well, HMRC wants you to pay the right tax, at the right time.",
        "Depending on when you first start self-employment, it can be some time before you get your first tax bill. " +
          "Added to that, if your bill for tax and class 4 National Insurance is over £1000, your first bill will be one " +
          "and a half times what you owe as you also need to make a payment on account.",
        "Don’t worry though as this is going towards next year’s tax bill. It’s really easy to budget for these payments " +
          "as HMRC offers a ‘Ready Reckoner’ tool. Just search GOV.UK. When you get to this page click ‘start now’. " +
          "Using the ‘Ready Reckoner’ tool is easy. Just enter an estimate of your self-employed profit for the month " +
          "and it will give you an idea of what to put aside towards your tax and National Insurance bill. However, it’s " +
          "important to be aware, that although it’ll give you an idea of what to pay, it only covers income from self-employment.",
        "For example, some people stay in employment while they start their business just to see how things go. " +
          "Once you know what you want to pay, go online and you can set up a budget payment plan via direct debit.",
        "As long as you are up to date with previous Self Assessment payments, you can make regular payments in advance. " +
          "It’s really flexible. You can stop paying if you want to. You need to remember though, that if you’ve not paid " +
          "enough to cover your bill in full you’ll still need to pay the difference by the deadline. " +
          "Budgeting helps with cash flow because you can see exactly how much is going out of the business at any time.",
        "Because you have an idea of how your business is doing, you can also decide whether to reduce the payment on account. " +
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