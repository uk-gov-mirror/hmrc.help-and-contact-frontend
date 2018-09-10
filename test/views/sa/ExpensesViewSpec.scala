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

package views.sa

import play.twirl.api.HtmlFormat
import views.behaviours.ViewBehaviours
import views.html.sa.expenses

class ExpensesViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "sa.expenses"

  def createView = () => expenses(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "Self Assessment Expenses view" must {

    behave like normalPage(createView, messageKeyPrefix)

    "contain correct content" in {
      val doc = asDocument(createView())
      doc.getElementsByTag("h1").first().text() mustBe "Expenses"
    }

    "contain the 'Expenses if you’re self-employed - video transcript' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc,
        "expenses-if-you-are-self-employed-transcript",
        "Expenses if you’re self-employed - video transcript",
        "/business-account/help/transcript/expenses-if-you-are-self-employed",
        expectedGAEvent = "link - click:Expenses:Expenses if you are self employed - video transcript")
    }

    "contain the 'Calculating motoring expenses - video transcript' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc,
        "calculating-motoring-expenses-transcript",
        "Calculating motor expenses - video transcript",
        "/business-account/help/transcript/calculating-motoring-expenses",
        expectedGAEvent = "link - click:Expenses:Calculating motoring expenses - video transcript")
    }

    "have youtube url in html for each embedded video" in {
      val doc = asDocument(createView())
      val listOfVideoId: List[String] = List("ABA3Xv2V2MQ", "Y_lpys4Kksk")
      listOfVideoId.foreach(id => doc.toString must include(s"https://www.youtube.com/embed/$id?autoplay=0"))
    }

  }

}
