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

import scala.collection.JavaConverters._

class TailorYourTaxReturnViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "tailorYourTaxReturnTranscript"

  def createView = () => tailor_your_tax_return(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "TailerYourTaxReturn view" must {
    behave like normalPage(createView, messageKeyPrefix)

    "have correct content" in {
      val doc = asDocument(createView())

      val elements = doc.getElementsByTag("article").first().getElementsByTag("p").asScala.toList.map(_.text())

      val contentList = List(
        "This is one of a series of videos about online Self Assessment.",
        "Once you’re in the online tax return you need to ‘Tailor your return’. " +
          "Three pages, of Yes/No questions ensuring that you’re only given the sections you need.",
        "Page 1 is about the sources of income you had, for example employment, self-employment or partnership income. " +
          "If you’re unsure about a question, select the ‘question mark’.",
        "You’ll then be given guidance. You must select ‘Yes’ or ‘No’ to all questions with a red star. " +
          "On this page, that’s all of them. When you choose ‘Yes’, you may be asked for further information. " +
          "For example, if you choose ‘Yes’ for the employment section, you’re asked how many employments or directorships " +
          "you had, and you have to give the employer’s name.",
        "You don’t have to complete your return all in one go. " +
          "You can leave at any time and come back later which can be helpful if you don’t have everything to hand. " +
          "You use the ‘Save’ button to do this.",
        "When you select the ‘next’ button, what you’ve already entered is saved.",
        "‘Next’ takes you to page 2. This asks about other types of income like bank interest, dividends and pensions. " +
          "This question can cause confusion – it’s for losses on other taxable income such as casual earnings or commission. " +
          "It’s not for losses you would enter in another section of the return, for example, self-employed losses " +
          "in the self-employed section. If in doubt use the question mark.",
        "‘Next’ takes you to page 3. This is mainly about tax reliefs, including pension contributions or charitable gifts, " +
          "Married Couple’s Allowance and Marriage Allowance.",
        "This is not refunds you’ve received during the tax year for an earlier year, or refunds through an employer. " +
          "It’s the 2016-17 tax you’ve paid, which has already been refunded by HMRC or Jobcentre Plus. " +
          "‘Next’ takes you to ‘Fill in your return’.",
        "From here you give details of the income you had and any tax you paid.",
        "Remember the tax return due on 31 January 2018 is for the tax year 6 April 2016 to 5 April 2017. " +
          "Any tax you owe must be paid by 31 January 2018.",
        "And don’t forget you can file your Self Assessment anytime, so don’t leave it until the last minute. " +
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