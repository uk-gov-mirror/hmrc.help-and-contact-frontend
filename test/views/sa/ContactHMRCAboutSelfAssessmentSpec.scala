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

package views.sa

import org.jsoup.nodes.{Document, Element}
import play.twirl.api.HtmlFormat
import views.behaviours.ViewBehaviours
import views.html.sa.contact_hmrc_about_sa

import scala.collection.JavaConverters._

class ContactHMRCAboutSelfAssessmentSpec extends ViewBehaviours {
  val messageKeyPrefix = "sa.contact_hmrc"
  def createView = () => contact_hmrc_about_sa(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)


  "Self Assessment Contact HMRC view" must {

    behave like normalPage(createView, messageKeyPrefix)

    "contain correct H1 heading" in {
      val doc: Document = asDocument(createView())
      doc.getElementsByTag("h1").first().text() mustBe "Contact HMRC about Self Assessment"
    }

    "contain correct H2 headings" in {
      val doc: Document = asDocument(createView())
      val listOfHeadings: List[String] = List(
        "Questions about Self Assessment",
        "Webchat",
        "Postal address for HMRC"
      )
      val headings = doc.getElementsByTag("article").first.getElementsByTag("h2").asScala.toList.map(_.text())

      headings mustBe listOfHeadings
    }

    "contain the 'call us' link" in {
      val doc: Document = asDocument(createView())
      assertLinkById(doc,
        "call-us",
        "call us",
        "https://www.gov.uk/government/organisations/hm-revenue-customs/contact/self-assessment",
        expectedGAEvent = "link - click:Contact HMRC about Self Assessment:call us",
        expectedIsExternal = true,
        expectedOpensInNewTab = true)
    }

    "contain the 'Talk to an adviser online' (webchat) link" in {
      val doc: Document = asDocument(createView())
      val webchatLink: Element = doc.getElementById("talk-to-adviser")
      webchatLink.attr("href") mustBe "javascript:void(0)"
      webchatLink.attr("data-journey-click") mustBe "link - click:Contact HMRC about Self Assessment:Talk to an adviser online"
      webchatLink.attr("onclick") mustBe "openChat()"
    }

    "contain the 'different address' link" in {
      val doc: Document = asDocument(createView())
      assertLinkById(doc,
        "different-address",
        "different address",
        "https://www.gov.uk/government/organisations/hm-revenue-customs/contact/couriers",
        expectedGAEvent = "link - click:Contact HMRC about Self Assessment:different address",
        expectedIsExternal = true,
        expectedOpensInNewTab = true)
    }
  }

}
