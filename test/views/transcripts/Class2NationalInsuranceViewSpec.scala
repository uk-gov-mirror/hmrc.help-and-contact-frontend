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
import views.html.transcripts.class_2_national_insurance

import scala.collection.JavaConverters._

class Class2NationalInsuranceViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "class2NationalInsuranceTranscript"

  def createView = () => class_2_national_insurance(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "Class2NationalInsurance view" must {
    behave like normalPage(createView, messageKeyPrefix)

    "have correct content" in {
      val doc = asDocument(createView())
      val elements: List[String] = doc.getElementsByTag("article").first().getElementsByTag("p").asScala.toList.map(_.text())
      val contentList: List[String] = List(
        "This is one of a series of videos about online Self Assessment.",
        "Class 2 National Insurance is paid through your Self Assessment tax return by 31 January following the end of the tax year.",
        "On your Self Assessment tax return you’ve said that you’re self-employed or in a partnership.",
        "This is where your Class 2 National Insurance contributions are included.",
        "The return will show the amount of Class 2 you owe for the year. " +
          "This is based on the number of weeks that you were self-employed during the tax year.",
        "For example, if you were self-employed all year, the Class 2 weekly rate of £2.85 would be multiplied by the 52 weeks you were self-employed, " +
          "giving total Class 2 National Insurance contributions for the year of £148.20.",
        "If the figure is wrong it may be because HMRC doesn’t know that you started or ended self-employment during the year.",
        "If this is the case, please update your self-employment details here.",
        "Once you’ve done this you can recalculate the Class 2 amount.",
        "If your profits were less than £6,025 you won’t have to pay Class 2.",
        "Although you don’t need to pay Class 2 there will still be an amount owed shown here.",
        "If you want to make voluntary Class 2 payments select ‘Yes’ here. Otherwise, select ‘No’.",
        "In some circumstances you can choose to pay " +
          "the tax you owe through your PAYE tax code, but you can’t pay Class 2 National Insurance this way and any due will have to be paid by " +
          "31 January 2019.",
        "The calculation summary screen shows the total tax and National Insurance contributions due and the payments on account.",
        "The Class 2 National Insurance contributions will be included in the total amount due for 2017-18. " +
          "They’re not included in the calculation of payments on account.",
        "You can view and print out the full calculation here.",
        "This gives a breakdown of the tax, Class 4 National Insurance and your Class 2 National Insurance contributions.",
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
