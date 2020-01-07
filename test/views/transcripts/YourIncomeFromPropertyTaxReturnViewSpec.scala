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
import views.html.transcripts.your_income_from_property_tax_return

import scala.collection.JavaConverters._

class YourIncomeFromPropertyTaxReturnViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "yourIncomeFromPropertyTaxReturnTranscript"

  def createView = () => your_income_from_property_tax_return(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "YourSelfEmployedTaxReturn view" must {
    behave like normalPage(createView, messageKeyPrefix)

    "contain heading ID" in {
      val doc = asDocument(createView())
      doc.getElementsByTag("h1").attr("id") mustBe "income-from-property-transcript"
    }

    "have correct content" in {
      val doc = asDocument(createView())

      val elements = doc.getElementsByTag("article").first().getElementsByTag("p").asScala.toList.map(_.text())

      val bullets = doc.getElementsByTag("article").first().getElementsByTag("li").asScala.toList.map(_.text())

      val bulletList = List(
        "you receive income from letting furnished accommodation in your home that amounts to a trade - for example, " +
          "you run a guest house or offer bed and breakfast. " +
          "Fill in the ‘self-employment’ section instead.",
        "your property is outside the European Economic Area - fill in the ‘Foreign’ section.",
        "your property income was from a partnership - fill in the Partnership section."
      )

      val contentList = List(
        "This is one of a series of videos about online Self Assessment.",
        "On your Self Assessment tax return you’ve said you have income from UK property over £1,000 including foreign property income.",
        "This is the section of the tax return you go to.",
        "Don’t start filling in this section if:",
        "Enter details about your property on these pages.",
        "This is where you claim any Rent a Room relief.",
        "If your income was above the Rent a Room threshold you make an adjustment for the exempt amount later.",
        "Tell HMRC the amount of property income you received here.",
        "For jointly let property, only put your share. Use form 17 if you want to change the split of the income.",
        "You can claim the £1,000 property income allowance here, but remember you can’t then deduct any expenses against your income. " +
          "See the question mark for further guidance.",
        "From April 2017 the default basis for calculating your income and expenses is cash basis. " +
          "If you use traditional accounting select ‘Yes’ here, otherwise select ‘No’.",
        "UK tax is only taken off if you’re a non-resident landlord.",
        "Then, your total income from property is calculated.",
        "This is where you enter your running costs.",
        "Allowable expenses include the cost of repairs where you’re restoring an asset to its original condition " +
          "but not for improvements or upgrades. These are a capital expense.",
        "In the tax year 2017-18 finance costs for residential properties are restricted to 75%. " +
          "The figure you enter here will be 75% of your total residential finance costs and you’ll claim a basic rate tax reduction shortly. " +
          "This doesn’t affect commercial lettings, see the guidance for more details.",
        "If your total property income is below £85,000 you don’t need to fill in all the boxes.",
        "Add up your expenses and put the total in this box, less any Loan interest and financial costs, these must be entered separately.",
        "Adjustments go here.",
        "For residential lettings only, this is where you claim the cost of replacing domestic items. " +
          "Use the question mark for more information.",
        "If you’re claiming Rent a Room relief but received more than the threshold, enter the exempt amount here.",
        "The adjusted profit for the year will be calculated for you.",
        "If there’s a profit you must enter any unused losses from earlier years.",
        "The taxable profit or adjusted loss will display below and the loss to carry forward will be calculated for you.",
        "Enter here the amount of residential finance costs that you couldn’t deduct from your expenses. For 2017/18 that’s 25% of your total.",
        "Enter any other information you feel is needed to support your entries, then check your figures on the final summary screen.",
        "If you spot an error you can always go back and change it.",
        "You’ll find more help and support on GOV.UK.",
        "Webinars and other videos about Self Assessment are available from HMRC.",
        "Thanks for watching."
      )

      contentList.zipAll(elements, "", "").foreach {
        case (content, element) => element mustBe content
      }
      bulletList.zipAll(bullets,"","").foreach{
        case (expected, actual) => actual mustBe expected
      }
    }
  }
}