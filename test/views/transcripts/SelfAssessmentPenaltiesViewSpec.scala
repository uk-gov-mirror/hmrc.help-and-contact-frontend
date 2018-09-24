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

class SelfAssessmentPenaltiesViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "selfAssessmentPenaltiesTranscript"

  def createView = () => self_assessment_penalties(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "BudgetingYourSelfAssessment view" must {
    behave like normalPage(createView, messageKeyPrefix)

    "have correct content" in {
      val doc = asDocument(createView())

      val elements = doc.getElementsByTag("article").first().getElementsByTag("p").asScala.toList.map(_.text())

      val contentList = List(
        "This is one of a series of videos about online Self Assessment.",
        "You may have heard there are penalties if your tax return is sent to HMRC late.",
        "You need to be aware that you should submit and pay your online tax return by 31 January.",
        "You’ll usually be charged a penalty if you’re late.",
        "Here’s how to find out more.",
        "Search GOV.UK for ‘Self Assessment tax returns’.",
        "And then select ‘penalties’.",
        "You’ll see that you’ll be charged a £100 penalty if you your tax return is up to 3 months late, there’s also penalties " +
          "if you pay your tax bill late.",
        "Further penalties are added if you are more three months late with the return, or if you still haven’t paid.",
        "These can soon add up. There’s a calculator to help you work out what the penalties might be.",
        "Select ‘Start now’.",
        "Then choose the year and follow the steps through to get an estimate of what you could be charged in penalties.",
        "This is an estimate if your 2016-17 tax return is submitted online 31 July after the deadline (that’s 6 months late) " +
          "and the tax paid the same day.",
        "Of course, you can avoid any penalties by doing your tax return and paying anything you owe well before 31 January.",
        "Customers also tell us they are worried that a penalty will be charged if they make a mistake on the return.",
        "This depends on whether you have taken reasonable care when you have completed it. Keeping accurate records to fill in " +
          "your tax return will help to avoid this.",
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