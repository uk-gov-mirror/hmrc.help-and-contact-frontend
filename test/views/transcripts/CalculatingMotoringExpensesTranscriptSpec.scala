/*
 * Copyright 2019 HM Revenue & Customs
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
import views.html.transcripts.calculating_motoring_expenses

import scala.collection.JavaConverters._

class CalculatingMotoringExpensesTranscriptSpec extends ViewBehaviours {

  val messageKeyPrefix = "sa.expenses.calculating_motoring_expenses"

  def createView = () => calculating_motoring_expenses(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "CalculatingMotoringExpensesTranscript view" must {
    behave like normalPage(createView, messageKeyPrefix)

    "have correct content" in {
      val doc = asDocument(createView())
      val elements: List[String] = doc.getElementsByTag("article").first().getElementsByTag("p").asScala.toList.map(_.text())
      val contentList: List[String] = List(
        "If you work for yourself you may have a car, van or motorcycle for both business and personal use.",
        "You can claim business expenses for a number of things including fuel and vehicle insurance.",
        "But you can’t claim for non-business driving, fines or travel between home and work.",
        "There are two ways of working out your motoring expenses: using the actual costs, or a flat rate for business mileage.",
        "Once you’ve chosen one of these methods for a particular vehicle, you must continue to use it for that vehicle.",
        "If you decide to use the actual costs, you’ll need to know how many miles the vehicle has done over the year.",
        "Then work out what percentage was for business.",
        "For example, if your business use was 75%, and your total running costs are £6,000, then the business use of that is £4,500 – which is the figure to include on your Self Assessment tax return.",
        "If you use the actual cost method, you can also claim a capital allowance for the purchase of the vehicle itself.",
        "You can find out more about Capital Allowances on the GOV.UK website.",
        "Alternatively, you can claim expenses using a flat rate for business mileage.",
        "The flat rate covers the whole cost of buying, running and maintaining the vehicle – so you can’t claim a capital allowance for its purchase.",
        "You can find the current flat rates on GOV.UK.",
        "You’ll need to keep a record of how many business miles you travel.",
        "Remember, once you start using the flat rate method for a vehicle, you can’t switch to the actual cost method. But you can use the actual cost method for a different vehicle.",
        "There’s a checker on GOV.UK to work out which method is best for you. Use the link at the end of this video to go to it.",
        "You’ll find more help and support on GOV.UK.",
        "Webinars and other videos about Self Assessment are available from HMRC.",
        "Thanks for watching."
      )

      contentList.zip(elements).foreach {
        case (content, element) => element mustBe content
      }
    }
  }
}
