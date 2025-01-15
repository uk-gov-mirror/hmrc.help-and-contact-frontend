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

package views

import play.twirl.api.{Html, HtmlFormat}
import views.behaviours.ViewBehaviours
import views.html.epaye_view_or_correct_submissions

class EpayeViewCorrectSubmissionViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "epaye"

  val serviceInfoContent: Option[Html] = Some(Html("<div>Service info content</div>"))

  def createView(): () => HtmlFormat.Appendable =
    () =>  epaye_view_or_correct_submissions.apply()(messages)


  "Epaye View amd Correct Submission view" must {

    "contain heading ID" in {
      val doc = asDocument(createView()())
      doc.getElementsByTag("h1").attr("id") mustBe "epaye_view_or_correct_submissions"
    }

    "have correct h2 headings" in {
      val doc      = asDocument(createView()())
      val headings = doc.getElementsByTag("h2").toString
      headings must include("When you can view your submissions")
      headings must include("Employer Payment Summary (EPS)")
      headings must include("Your payments to HMRC")
      headings must include("Check your PAYE submissions")
    }

    "have correct content" in {
      val doc = asDocument(createView()())
      doc.text() must include("You can also check or view submissions in HMRC’s Basic PAYE Tools (BPT) software.")
    }

    "have correct links" in {
      val doc = asDocument(createView()())
      assertLinkByLinkText(
        doc,
        "Basic PAYE Tools user guide (opens in new tab)",
        "https://www.gov.uk/government/publications/basic-paye-tools-user-guide/basic-paye-tools-user-guide"
      )
    }
  }
}
