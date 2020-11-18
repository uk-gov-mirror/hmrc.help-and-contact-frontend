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

package views.sa

import play.twirl.api.HtmlFormat
import views.behaviours.ViewBehaviours
import views.html.sa.old_help_with_your_self_assessment_tax_return
import collection.JavaConverters._

class OldHelpWithYourSelfAssessmentTaxReturnViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "helpWithYourSelfAssessmentTaxReturn"

  def createView = () => inject[old_help_with_your_self_assessment_tax_return].apply(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "SelfAssessmentTaxReturnCheck view" must {
    behave like normalPage(createView, messageKeyPrefix)

    "contain heading ID" in {
      val doc = asDocument(createView())
      doc.getElementsByTag("h1").attr("id") mustBe "help-with-your-sa-tax-return"
    }

    "have correct h2 headings" in {
      val listOfHeadings: List[String] = List(
        "Who must send a tax return?",
        "Your first Self Assessment tax return",
        "Tailor your tax return",
        "Your self-employed tax return",
        "If I have income from property, how do I fill in my tax return?",
        "Basic record keeping when you’re self-employed"
      )
      val doc = asDocument(createView())
      val headings = doc.getElementsByTag("article").first.getElementsByTag("h2").asScala.toList.map(_.text())

      headings mustBe listOfHeadings
    }

    "have correct content" in {
      val doc = asDocument(createView())
      doc.text() must include("You will usually be sent a tax return if you are registered as self-employed.")

      doc.text() must include("You can anonymously")

      doc.text() must include("If you are sent a tax return, or if you get an email or letter from HMRC telling you to complete one, " +
        "you must do it - even if you do not have any tax to pay. If you do not send a tax return back by the deadline you may have to " +
        "pay a penalty. If you used to send a tax return but do not need to send one for the last tax year, contact HMRC to close your " +
        "Self Assessment account. You must also tell HMRC if you have stopped being self-employed.")

      doc.text() must include("Self Assessment is a system HM Revenue and Customs (HMRC) uses to collect Income Tax.")

      doc.text() must include("Tax is usually deducted automatically from wages, pensions and savings. People and businesses with " +
        "other income must report it in a tax return.")

      doc.text() must include("Self Assessment is not just for self-employed people. Everyone who has to complete a tax return must " +
        "do so, even if there is nothing to pay. You may be charged a penalty if you do not.")

      doc.text() must include("If you need to send one, you fill it in after the end of the tax year (5 April) it applies to.")

      doc.text() must include("If you did not send an online return last year, allow extra time (up to 20 working days) as you will " +
        "need to register first.")

      doc.text() must include("You will tailor your tax return on the first 3 pages. Use this section to tell us about your different " +
        "types of income, employments and self-employments so that you only see the parts of the tax return that you need to.")

      doc.text() must include("If you started or ceased self-employment during the year, it is important to enter the dates so HMRC " +
        "can update your records.")

      doc.text() must include("If you did not trade for a full year, but if you had and your income would have been over £85,000, " +
        "you answer “yes”. For example: If your business income over 6 months was £50,000, then for 12 months it would have been " +
        "£100,000.")

      doc.text() must include("Do not fill in this section if you run a guest house or offer bed and breakfast. That is classed as " +
        "self-employment and you should fill in that section.")

      doc.text() must include("If your property is outside the European Economic Area, fill in the Foreign section.")

      doc.text() must include("If your property income was from a partnership, fill in the Partnership section.")

      doc.text() must include("You must record any money going in and out of your business, whether the transaction is made in person" +
        " - by phone - or online.")

      doc.text() must include("You can keep your records on paper or digitally using a computer. These include items such as invoices," +
        " sale receipts, proof of purchases and bank statements. Remember your records must be accurate, clear and complete.")

      doc.text() must include("Some software suppliers offer free record keeping apps for small businesses. How you keep your records will" +
        " depend on whether you use the traditional or cash basis method of accounting, and how you work out your expenses.")

      doc.text() must include("You’ll find it easier if you organise your income and expenses into categories and update these regularly." +
        " And, if you’re keeping your records digitally, remember to back up your files.")

      doc.text() must include("It’s important to keep your business and private transactions separate. Drawing or using money from your" +
        " business for personal use isn’t an allowable business expense.")

      doc.text() must include("If you’re VAT registered, you’ll also need to keep records of how much VAT you’ve paid your suppliers" +
        " and charged your customers. If your income is above the VAT threshold, you’ll need to submit your records to us using Making Tax Digital software.")

      doc.text() must include("Make sure you store your records for at least 6 years, as we may need to see them to check you’re paying" +
        " the right amount of tax.")
    }

    "have correct links" in {
      val doc = asDocument(createView())
      assertLinkById(
        doc,
        "check-if-you",
        "check if you need to fill in a Self Assessment tax return",
        "https://www.gov.uk/check-if-you-need-a-tax-return",
        "link - click:Help with your Self Assessment return:check if you need to fill in a Self Assessment tax return")
      assertLinkById(
        doc,
        "why-have-been-sent-transcript",
        "Why have I been sent a tax return - video transcript",
        "/business-account/help/transcript/why-sent-tax-return",
        "link - click:Help with your Self Assessment return:Why have I been sent a tax return - video transcript")
      assertLinkById(
        doc,
        "your-first-tax-return-transcript",
        "Your first Self Assessment tax return - video transcript",
        "/business-account/help/transcript/your-first-tax-return",
        "link - click:Help with your Self Assessment return:Your first Self Assessment tax return - video transcript")
      assertLinkById(
        doc,
        "tailor-your-tax-return-transcript",
        "Tailor your tax return - video transcript",
        "/business-account/help/transcript/tailor-your-tax-return",
        "link - click:Help with your Self Assessment return:Tailor your tax return - video transcript")
      assertLinkById(
        doc,
        "your-self-employed-tax-return-transcript",
        "Your self-employed tax return - video transcript",
        "/business-account/help/transcript/your-self-employed-tax-return",
        "link - click:Help with your Self Assessment return:Your self-employed tax return - video transcript")
      assertLinkById(
        doc,
        "your-income-from-property-tax-return-transcript",
        "If I have income from property, how do I fill in my tax return? - video transcript",
        "/business-account/help/transcript/your-income-from-property-tax-return",
        "link - click:Help with your Self Assessment return:Your income from property tax return - video transcript")
      assertLinkById(
        doc,
        "record-keeping-transcript",
        "Basic record keeping when you’re self-employed - video transcript",
        "/business-account/help/transcript/record-keeping-for-self-employed",
        expectedGAEvent = "link - click:Help with your Self Assessment return:Record keeping - video transcript")

    }

    "have youtube url in html for each embedded video" in {
      val doc = asDocument(createView())
      val listOfVideoId: List[String] = List("5qOq9nWx-0c", "D-WSq_vSTU8", "8I9A4ZefLPU", "kqeoa1VNt9w", "ZKKVd1XQQJA", "4OlkduJ5MTU")
      listOfVideoId.foreach(id => doc.toString must include(s"https://www.youtube.com/embed/$id?autoplay=0"))
    }
  }
}
