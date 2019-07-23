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

package views.epaye

import play.twirl.api.HtmlFormat
import views.behaviours.ViewBehaviours
import views.html.epaye.contact_hmrc_about_epaye

class ContactHmrcAboutEpayeViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "epaye.contact_hmrc"

  def createView = () => contact_hmrc_about_epaye(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "Contact HMRC About EPAYE view" must {

    behave like normalPage(createView, messageKeyPrefix)

    "contain correct content" in {
      val doc = asDocument(createView())
      doc.getElementsByTag("h1").first().text() mustBe "Contact HMRC about PAYE for employers"
    }

    "contain the 'contact us' link" in {
      val doc = asDocument(createView())
      assertLinkById(
        doc,
        "contact-us",
        "contact us",
        "https://www.gov.uk/government/organisations/hm-revenue-customs/contact/employer-enquiries",
        expectedGAEvent = "link - click:Contact HMRC about PAYE:contact us",
        expectedIsExternal = true,
        expectedOpensInNewTab = true
      )
    }

  }

}
