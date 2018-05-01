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

class YourIncomeFromPropertyTaxReturnViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "yourIncomeFromPropertyTaxReturnTranscript"

  def createView = () => your_income_from_property_tax_return(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "YourSelfEmployedTaxReturn view" must {
    behave like normalPage(createView, messageKeyPrefix)

    "have correct content" in {
      val doc = asDocument(createView())

      val elements = doc.getElementsByTag("article").first().getElementsByTag("p").asScala.toList.map(_.text())

      val contentList = List(
        "This is one of a series of videos about online Self Assessment.",
        "On your Self Assessment tax return you’ve said you have income from property. " +
          "This is the section of the tax return you go to.",
        "Don’t start filling in this section if you receive income from letting furnished accommodation in your home that " +
          "amounts to a trade - for example, you run a guest house or offer bed and breakfast. " +
          "Fill in the ‘self-employment’ section instead.",
        "Don’t fill this in if your property is outside the European Economic Area either - " +
          "in that case fill in the foreign section. And if your property income was from a partnership - " +
          "fill in the Partnership section.",
        "Enter details about your property on these pages. This is where you claim any Rent a Room relief. " +
          "If your income was above the Rent a Room threshold you make an adjustment for the exempt amount later. " +
          "Tell HMRC the amount of property income you received here.",
        "For jointly let property, only put your share. Use form 17 if you want to change the split of the income. " +
          "UK tax is only taken off if you’re a non-resident landlord. Then, your total income from property is calculated. " +
          "This is where you enter your running costs.",
        "Allowable expenses include the cost of repairs where you’re restoring an asset to its original condition " +
          "but not for improvements or upgrades. These are a capital expense. " +
          "If your total property income is below £83,000 you don’t need to fill in all the boxes.",
        "Add up your expenses and put the total, less any furnished holiday lettings expenses, in this box.",
        "The renewals allowance and the 10% wear and tear allowance for the cost of replacing furniture or furnishings " +
          "are no longer available. Adjustments go here.",
        "For residential lettings only, this is where you claim the cost of replacing domestic items. " +
          "Use the question mark for more information.",
        "If you’re claiming Rent a Room relief but received more than the threshold, enter the exempt amount here.",
        "The adjusted profit for the year will be calculated for you. " +
          "If there’s a profit you must enter any unused losses from earlier years.",
        "The taxable profit or adjusted loss will display below and the loss to carry forward will be calculated for you. " +
          "Enter any other information then check your figures on the final summary screen. " +
          "If you spot an error you can always go back and change it.",
        "You’ll find more help and support on GOV.UK. Webinars and other videos about Self Assessment are available from HMRC.",
        "Thanks for watching."
      )

      contentList.zipAll(elements, "", "").foreach {
        case (content, element) => element mustBe content
      }
    }
  }
}