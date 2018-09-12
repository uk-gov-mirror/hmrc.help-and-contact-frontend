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

class ViewingYourSelfAssessmentCalculationViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "viewingYourCalculationTranscript"

  def createView = () => viewing_your_self_assessment_calculation(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "ViewingYourCalculation view" must {
    behave like normalPage(createView, messageKeyPrefix)

    "have correct content" in {
      val doc = asDocument(createView())

      val elements = doc.getElementsByTag("article").first().getElementsByTag("p").asScala.toList.map(_.text())
      val bullets = doc.getElementsByTag("article").first().getElementsByTag("li").asScala.toList.map(_.text())

      val bulletsList = List(
        "income",
        "Personal Allowance",
        "tax due",
        "Class 4 National Insurance",
        "Class 2 National Insurance",
        "payments due on 31 January 2019"
      )

      val contentList = List(
        "This is one of a series of videos about online Self Assessment.",
        "When you use HMRC’s online tax return, it automatically works out how much you need to pay.",
        "You fill in your figures and once you have checked that everything is correct, view your calculation.",
        "You will then see this screen.",
        "This shows the amount you’re due to pay for 2017-18. " +
          "This includes Class 4 and Class 2 National Insurance, if any are due.",
        "If you’re due to pay £1,000 or more, you also make a ‘payment on account’.",
        "A ‘payment on account’ is half of your previous year’s tax and class 4 National Insurance bill.",
        "For 2017-18, your payment is due by 31 January 2019.",
        "You’ll make a second ‘payment on account’ in July 2019.",
        "But don’t worry, these payments on account will be deducted from the amount you’re due to pay for the 2018-19 tax year on your " +
          "Self Assessment statement when you complete the tax return next year.",
        "If you’d like to see how the figures have been worked out " +
          "in more detail, select ‘View and print your full calculation’.",
        "You can then see exactly how your tax bill has been worked out.",
        "It shows your:",
        "These estimated payments don’t include any payments you may have already made.",
        "You can print a copy of this for your own records.",
        "You’ll find more help and support on GOV.UK.",
        "Webinars and other videos about Self Assessment are available from HMRC.",
        "Thanks for watching."
      )

      contentList.zipAll(elements, "", "").foreach {
        case (content, element) => element mustBe content
      }

      bulletsList.zipAll(bullets, "", "").foreach {
        case (expected, actual) => actual mustBe expected
      }
    }
  }
}


