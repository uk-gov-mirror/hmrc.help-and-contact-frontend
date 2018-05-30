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

package views

import play.twirl.api.HtmlFormat
import views.behaviours.ViewBehaviours
import views.html.help_and_contact
import collection.JavaConverters._

class helpAndContactViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "help_and_contact"

  def createView = () => help_and_contact(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "Help and contact view" must {

    behave like normalPage(createView, messageKeyPrefix)

    "have correct h2 headings" in {
      val listOfHeadings: List[String] = List(
        "Self Assessment"
      )
      val doc = asDocument(createView())
      val headings = doc.getElementsByTag("article").first.getElementsByTag("h2").asScala.toList.map(_.text())

      headings mustBe listOfHeadings
    }

    "have correct links" in {
      val doc = asDocument(createView())
      assertLinkById(
        doc,
        "register-deregister-self-assessment",
        "Register or deregister for Self Assessment",
        "/business-account/help/self-assessment/register-or-deregister",
        "HelpAndContact:click:RegisterDeregisterSelfAssessment")
    }
  }
}
