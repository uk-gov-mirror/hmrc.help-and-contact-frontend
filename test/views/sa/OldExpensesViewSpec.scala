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

package views.sa

import play.twirl.api.HtmlFormat
import views.behaviours.ViewBehaviours
import views.html.sa.old_expenses

class OldExpensesViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "sa.expenses"

  def createView = () => inject[old_expenses].apply(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "Self Assessment Expenses view" must {

    behave like normalPage(createView, messageKeyPrefix)

    "contain heading ID" in {
      val doc = asDocument(createView())
      doc.getElementsByTag("h1").attr("id") mustBe "expenses"
    }

    "contain correct heading" in {
      val doc = asDocument(createView())

      val h1s = doc.getElementsByTag("h1")
      h1s.size() mustBe 1
      h1s.first().text() mustBe "Expenses"
    }

    "contain correct content" in {
      val doc = asDocument(createView())
      doc.text() must include("You put all of your allowable expenses for the tax year on your Self-Assessment tax return. " +
        "Do not send proof of your expenses with your tax return. Keep them so that you can show them to HMRC, if asked.")

      doc.text() must include("You can choose to use ‘Simplified expenses’ to work out some of your expenses using flat " +
        "rates instead of working out your actual business costs. You can use flat rates for business costs for vehicles, " +
        "working from home, living in your business premises. Keep records of your business miles for vehicles, hours you " +
        "work at home and how many people live at your business premises over the year.")

      doc.text() must include("Simplified expenses cannot be used by limited companies or business partnerships involving a " +
        "limited company.")

      doc.text() must include("If you do not use simplified expenses and if your business income is £85,000 or more, you need " +
        "to enter each expense.")

      doc.text() must include("If your business income is below £85,000 for the full year, you can still list your expenses, " +
        "but you can also choose to add them all together and show them as a total figure on your Self-Assessment tax return.")

      doc.text() must include("You can claim business expenses for a number of things including fuel and vehicle insurance. " +
        "You cannot claim for non-business driving, or for fines or travel between home and work.")

      doc.text() must include("You can claim either the actual costs, or a flat rate for business mileage. Once you have chosen " +
        "one of these methods for a particular vehicle, you must continue to use it for that vehicle.")

      doc.text() must include("If you decide to use the actual costs, you will need to know how many miles the vehicle has done " +
        "over the year. Then work out what percentage was for business. You can also claim a capital allowance for the purchase " +
        "of the vehicle.")

      doc.text() must include("The flat rate covers the whole cost of buying, running and maintaining the vehicle – so you cannot " +
        "claim a capital allowance for its purchase.")

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

    "contain the 'What expenses can I include in my Self Assessment tax return - video transcript' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc,
        "what-expenses-can-i-include-in-my-sa-tax-return-transcript",
        "What expenses can I include in my Self Assessment tax return - video transcript",
        "/business-account/help/transcript/expenses-if-you-are-self-employed",
        expectedGAEvent = "link - click:Expenses:Expenses if you are self employed - video transcript")
    }

    "contain the 'Claiming motoring expenses if you’re self-employed - video transcript' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc,
        "calculating-motoring-expenses-transcript",
        "Claiming motoring expenses if you’re self-employed - video transcript",
        "/business-account/help/transcript/calculating-motoring-expenses",
        expectedGAEvent = "link - click:Expenses:Calculating motoring expenses - video transcript")
    }

    "contain the 'Record keeping for the self-employed - video transcript' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc,
        "record-keeping-transcript",
        "Basic record keeping when you’re self-employed - video transcript",
        "/business-account/help/transcript/record-keeping-for-self-employed",
        expectedGAEvent = "link - click:Expenses:Record keeping - video transcript")
    }

    "have youtube url in html for each embedded video" in {
      val doc = asDocument(createView())
      val listOfVideoId: List[String] = List("r2txvLXi_Fk", "cXdJSwunYt0", "4OlkduJ5MTU")

      listOfVideoId.foreach(id => doc.toString must include(s"https://www.youtube.com/embed/$id?autoplay=0"))
    }

  }

}