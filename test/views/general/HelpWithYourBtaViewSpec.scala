/*
 * Copyright 2022 HM Revenue & Customs
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

package views.general

import play.twirl.api.HtmlFormat
import views.behaviours.ViewBehaviours
import views.html.general.help_with_your_bta

class HelpWithYourBtaViewSpec extends ViewBehaviours {

  def createView = () => inject[help_with_your_bta].apply(frontendAppConfig)(Some(HtmlFormat.empty))(fakeRequest, messages)

  "Help with you BTA view" must {

    "contain heading ID" in {
      val doc = asDocument(createView())
      doc.getElementsByTag("h1").attr("id") mustBe "help-with-your-bta"
    }
    
    "have correct links" in {
      val doc = asDocument(createView())
      assertLinkById(
        doc,
        "help-with-your-bta-addTaxLink",
        "add a tax to your business tax account",
        "http://localhost:9020/business-account/add-tax"
      )
      assertLinkById(
        doc,
        "how-do-i-add-a-tax-video",
        "Video - How do I add a tax to my business tax account? (opens in new tab)",
        "https://www.youtube.com/watch?v=b2wLXZZddGc",
        expectedOpensInNewTab = true
      )
      assertLinkById(
        doc,
        "how-do-i-add-a-tax-transcript",
        "How do I add a tax to my business tax account? - video transcript",
        "/business-account/help/transcript/add-a-tax"
      )
      assertLinkById(
        doc,
        "how-do-i-add-a-tax-BTA-home",
        "business tax account homepage",
        "http://localhost:9020/business-account"
      )
      assertLinkById(
        doc,
        "why-register-or-stopping",
        "Self Assessment",
        "/business-account/help/self-assessment/register-or-stopping"
      )
      assertLinkById(
        doc,
        "manage-best-out-of",
        "use only one ID for all your online business tax services",
        "https://www.gov.uk/government/publications/use-hmrcs-business-tax-account/use-hmrcs-business-tax-account#best_out_of",
        expectedOpensInNewTab = false
      )
      assertLinkById(
        doc,
        "manage-profile-management",
        "Manage your Government Gateway details",
        "/user-profile-redirect-frontend/profile-management"
      )
      assertLinkById(
        doc,
        "difference-PTA",
        "Read more about your personal tax account",
        "https://www.gov.uk/personal-tax-account",

        expectedOpensInNewTab = false
      )
      assertLinkById(
        doc,
        "difference-read-full-list",
        "Read the full list of taxes, duties and schemes you can manage",
        "https://www.gov.uk/guidance/sign-in-to-your-hmrc-business-tax-account#tax_services",

        expectedOpensInNewTab = false
      )
      assertLinkById(
        doc,
        "check-tax-position",
        "check your tax position for taxes that you’ve registered for",
        "http://localhost:9020/business-account"
      )
      assertLinkById(
        doc,
        "make-returns-and-payments",
        "make returns and payments",
        "http://localhost:9020/business-account",

      )
      assertLinkById(
        doc,
        "add-or-remove-tax",
        "add or remove a tax, duty or scheme",
        "http://localhost:9020/business-account/add-tax",

      )
      assertLinkById(
        doc,
        "give-someone-access-tax",
        "give someone else access to a tax, duty or scheme",
        "http://localhost:9020/business-account/manage-account",

      )
      assertLinkById(
        doc,
        "check-secure-messages",
        "check secure messages from HMRC",
        "http://localhost:9020/business-account/messages",

      )
      assertLinkById(
        doc,
        "add-view-change-tax-agent",
        "add, view or change a tax agent",
        "http://localhost:9020/business-account/manage-account",

      )
      assertLinkById(
        doc,
        "get-help-with-taxes",
        "get help with your taxes",
        "/business-account/help",

      )
      assertLinkById(
        doc,
        "update-contact-details",
        "update your contact details",
        "http://localhost:9020/business-account/manage-account/account-details",

      )
      assertLinkById(
        doc,
        "manage-your-contact-preferences",
        "manage your contact preferences",
        "http://localhost:9020/business-account/manage-account/account-details",

      )
      assertLinkById(
        doc,
        "change-government-gateway-password",
        "change your Government Gateway password",
        "http://localhost:9020/business-account/manage-account/account-details",

      )
    }
  }



  }
