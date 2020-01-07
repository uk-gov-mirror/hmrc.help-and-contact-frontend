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

import play.twirl.api.{Html, HtmlFormat}
import views.behaviours.ViewBehaviours
import views.html.transcripts.expenses_if_you_are_self_employed

import scala.collection.JavaConverters._

class ExpensesIfYouAreSelfEmployedTranscriptSpec extends ViewBehaviours {

  val messageKeyPrefix = "sa.expenses.expenses_for_self_employed_transcript"

  def createView(): Html = expenses_if_you_are_self_employed(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "ExpensesIfYouAreSelfEmployedTranscript view" must {
    behave like normalPage(createView, messageKeyPrefix)

    "contain heading ID" in {
      val doc = asDocument(createView())
      doc.getElementsByTag("h1").attr("id") mustBe "expenses-if-youre-self-employed-video-transcript"
    }

    "have correct content" in {
      val doc = asDocument(createView())

      val h1s = doc.getElementsByTag("h1")
      h1s.size() mustBe 1
      h1s.first().text() mustBe "Expenses if you’re self-employed - video transcript"

      val elements = doc.getElementsByTag("article").first().getElementsByTag("p").asScala.toList.map(_.text())
      val bullets = doc.getElementsByTag("article").first().getElementsByTag("li").asScala.toList.map(_.text())

      val bulletList = List(
        "business costs for vehicles",
        "working from home",
        "living in your business premises",
        "office property and equipment",
        "car, van and travel",
        "staff",
        "legal and financial costs",
        "marketing and subscriptions"
      )

      val contentList = List(
        "This is one of a series of videos about online Self Assessment.",
        "If you’re self-employed your business will have various running costs.",
        "You can take away some of these costs to work out your taxable profit as long as they’re allowable expenses.",
        "You can’t claim any expenses if you use the £1,000 tax-free trading allowance. There’s more about this on GOV.UK.",
        "Allowable expenses don’t include money you take from your business to pay for private purchases.",
        "In other words, allowable business expenses don’t include things like accommodation, food, clothing, holidays and any other things you buy for you and your family.",
        "You need to keep records of all your business expenses as proof of your costs.",
        "You add up all of your allowable expenses for the tax year and put them on your Self-Assessment tax return.",
        "Don’t send in proof of your expenses when you send your tax return to HMRC. Keep them so that you can show them to HMRC, if asked.",
        "You can choose to use ‘Simplified expenses’.",
        "This is a way of working out some of your expenses using flat rates instead of working out your actual business costs.",
        "You don’t have to do this. You can decide if it suits your business.",
        "Simplified expenses can’t be used by limited companies or business partnerships involving a limited company.",
        "You can use flat rates for:",
        "Keep records of your business miles for vehicles, hours you work at home and how many people live at your business premises over the year.",
        "Then, at the end of the year, use the flat rate to work out your expenses and include the amounts in your Self Assessment tax return.",
        "Find out more about this on the GOV.UK website.",
        "For other expenses, and if you decide not to use simplified expenses, keep accurate records of what you buy and their costs.",
        "Here’s some commonly used business expenses:",
        "For the tax year 2017-18, if your business income is £85,000 or more, you need to enter each expense under the appropriate heading.",
        "If you didn’t trade for a full year, but if you had, and your income would have been more than £85,000 you need to enter each expense in the same way.",
        "For example:",
        "If you traded from October to April, (that’s six months), and your business income was £50,000, then your business income for a full 12 months would have been £100,000.",
        "If your business income is below £85,000 for the full year, you can still list your expenses, but you can also choose to add them all together and show them as a total figure on your Self-Assessment tax return.",
        "You’ll find more help and support on GOV.UK.",
        "Webinars and other videos about Self Assessment are available from HMRC.",
        "Thanks for watching."
      )

      contentList.zipAll(elements, "", "").foreach {
        case (content, element) => element mustBe content
      }

      bulletList.zipAll(bullets, "", "").foreach {
        case (expected, actual) => actual mustBe expected
      }
    }
  }
}
