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

import models.PageType
import play.twirl.api.{Html, HtmlFormat}
import views.behaviours.ViewBehaviours
import views.html.general.contact_hmrc

class ContactHMRCViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "contact.hmrc"

  val serviceInfoContent: Option[Html] = Some(Html("<div>Service info content</div>"))

  def createView(): () => HtmlFormat.Appendable =
    () =>  contact_hmrc.apply(PageType.ContactHMRC.name, frontendAppConfig)(messages)


  "Contact HMRC view" must {

    "contain heading ID" in {
      val doc = asDocument(createView()())
      doc.getElementsByTag("h1").attr("id") mustBe "page-title-contact-hmrc"
    }

    "have correct links" in {
      val doc = asDocument(createView()())
      assertLinkByLinkText(
        doc,
        "Contact HMRC about Self Assessment (opens in new tab)",
        "https://www.gov.uk/government/organisations/hm-revenue-customs/contact/self-assessment"
      )
      assertLinkByLinkText(
        doc,
        "Contact HMRC about VAT (opens in new tab)",
        "https://www.gov.uk/government/organisations/hm-revenue-customs/contact/vat-enquiries"
      )
      assertLinkByLinkText(
        doc,
        "Contact HMRC about PAYE for employers (opens in new tab)",
        "https://www.gov.uk/government/organisations/hm-revenue-customs/contact/employer-enquiries"
      )
      assertLinkByLinkText(
        doc,
        "Contact HMRC about Corporation Tax (opens in new tab)",
        "https://www.gov.uk/government/organisations/hm-revenue-customs/contact/corporation-tax-enquiries"
      )
    }
  }
}
