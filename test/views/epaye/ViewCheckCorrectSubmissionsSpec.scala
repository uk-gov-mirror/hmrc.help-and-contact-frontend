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

package views.epaye

import collection.JavaConverters._
import play.twirl.api.HtmlFormat
import views.behaviours.ViewBehaviours
import views.html.epaye.view_check_correct_submissions

class ViewCheckCorrectSubmissionsSpec extends ViewBehaviours{
  val messageKeyPrefix = "epaye.submissions"

  def createView = () => view_check_correct_submissions(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "View Check Correct Submissions view" must {

    behave like normalPage(createView, messageKeyPrefix)

    "contain correct content" in {
      val doc = asDocument(createView())
      doc.getElementsByTag("h1").first().text() mustBe "View, check or correct your submissions"
      doc.text() must include("Submissions show up in your balance at different times depending on when you submit them.")
      doc.text() must include("Payments you made to HMRC can take up to 6 working days to appear in your balance.")
      doc.text() must include("We send an email to your Government Gateway address each time we receive a Full Payment Submission (FPS) or Employer Payment Summary (EPS).")
      doc.text() must include("Your Government Gateway email is !!TODO!!")
      doc.text() must include("You can also check or view submissions in HMRC’s Basic PAYE Tools (BPT) software.")
      doc.text() must include("To check if HMRC received a submission, select ‘View successful submissions’ from your employer homepage.")
      doc.text() must include("To view a specific submission, follow these instructions in the user guide, but select ‘View’ instead of ‘Change’:")
      doc.text() must include("Contact your software provider if you do not use BPT.")
      doc.text() must include("You must use your payroll software to correct Full Payment Submissions (FPSs) or Employer Payment Summaries (EPSs). HMRC staff cannot make changes for you.")
      doc.text() must include("You can make changes to submissions for the current tax year until April 19.")
      doc.text() must include("Follow the user guide if you use HMRC’s Basic PAYE Tools software (BPT):")
      doc.text() must include("Contact your software provider if you do not use BPT.")
      doc.text() must include("(page 25)")
      doc.text() must include("(page 37)")
      doc.text() must include("If you spot an error in a submission after the tax year has ended, you need to submit an ‘Earlier Year Update’ if it is after April 19.")
      doc.text() must include("You can")
      doc.text() must include("if you use BPT to manage your payroll.")
      doc.text() must include("If you use commercial software to manage your payroll, check with your software provider whether it can process Earlier Year Updates.")
      doc.text() must include("If your software doesn’t offer Earlier Year Updates, you will need to")
    }

    "have correct h2 headings" in {
      val listOfHeadings: List[String] = List(
        "When you can view your submissions",
        "Your payments to HMRC",
        "Check your PAYE submissions",
        "Correct a PAYE submission"
      )
      val doc = asDocument(createView())
      val headings = doc.getElementsByTag("article").first.getElementsByTag("h2").asScala.toList.map(_.text())

      headings mustBe listOfHeadings
    }

    "have correct h3 headings" in {
      val listOfHeadings: List[String] = List(
        "Email confirmations",
        "Payroll software confirmations",
        "Submissions for the current tax year",
        "Submissions from an earlier tax year"
      )
      val doc = asDocument(createView())
      val headings = doc.getElementsByTag("article").first.getElementsByTag("h3").asScala.toList.map(_.text())

      headings mustBe listOfHeadings
    }

    "have the correct 'Change this address' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc,"change-address", "Change this address.", "http://localhost:9020/business-account/manage-account",
       "HelpEPAYEContentLink:click:ChangeAddress", expectedOpensInNewTab = false)
    }

    "have the correct 'View an FPS' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc,"view-fps","view an FPS",
        "https://www.gov.uk/government/publications/basic-paye-tools-user-guide/basic-paye-tools-user-guide#page=25",
        "HelpEPAYEContentLink:click:ViewFPS", expectedIsExternal = true, expectedOpensInNewTab = true)
    }

    "have the correct 'View an EPS' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc, "view-eps", "view an EPS",
        "https://www.gov.uk/government/publications/basic-paye-tools-user-guide/basic-paye-tools-user-guide#page=37",
        "HelpEPAYEContentLink:click:ViewEPS", expectedIsExternal = true, expectedOpensInNewTab = true)
    }

    "have the correct 'correct an FPS' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc, "correct-fps", "correct an FPS",
        "https://www.gov.uk/government/publications/basic-paye-tools-user-guide/basic-paye-tools-user-guide#page=25",
        "HelpEPAYEContentLink:click:CorrectFPS", expectedIsExternal = true, expectedOpensInNewTab = true)
    }

    "have the correct 'correct an EPS' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc, "correct-eps", "correct an EPS",
        "https://www.gov.uk/government/publications/basic-paye-tools-user-guide/basic-paye-tools-user-guide#page=37",
        "HelpEPAYEContentLink:click:CorrectEPS", expectedIsExternal = true, expectedOpensInNewTab = true)
    }

    "have the correct 'submit an Earlier Year Update' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc, "submit-earlier-year-update", "submit an Earlier Year Update using Basic PAYE Tools",
        "https://www.gov.uk/government/publications/basic-paye-tools-earlier-year-update-payroll-user",
        "HelpEPAYEContentLink:click:SubmitEarlierYearUpdate", expectedIsExternal = true, expectedOpensInNewTab = true)
    }

    "have the correct 'install BPT' link" in {
      val doc = asDocument(createView())
      assertLinkById(doc, "install-bpt", "install BPT to submit an Earlier Year Update",
        "https://www.gov.uk/government/publications/basic-paye-tools-earlier-year-update-alongside-commercial-software",
        "HelpEPAYEContentLink:click:InstallBPT", expectedIsExternal = true, expectedOpensInNewTab = true)
    }
  }
}
