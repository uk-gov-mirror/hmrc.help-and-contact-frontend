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

package views.general

import play.twirl.api.HtmlFormat
import views.behaviours.ViewBehaviours
import views.html.general.help_with_your_bta

class HelpWithYourBtaViewSpec extends ViewBehaviours {

  def createView = () => inject[help_with_your_bta].apply(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "Help with you BTA view" must {

    "contain heading ID" in {
      val doc = asDocument(createView())
      doc.getElementsByTag("h1").attr("id") mustBe "help-with-your-bta"
    }

    "contain correct heading" in {
      val doc = asDocument(createView())

      val h2s = doc.getElementsByTag("h2")
      h2s.size() mustBe 10
    }

    "have correct links" in {
      val doc = asDocument(createView())
      assertLinkById(
        doc,
        "help-with-your-bta-addTaxLink",
        "add a tax to your business tax account",
        "http://localhost:9020/business-account/add-tax",
        "link - click: Help with your BTA: add a tax to your business tax account"
      )
      assertLinkById(
        doc,
        "how-do-i-add-a-tax-video",
        "Video - How do I add a tax to my business tax account? (opens in new tab)",
        "https://www.youtube.com/watch?v=b2wLXZZddGc",
        "link - click:Help with your BTA: Adding a tax to your business tax account video",
        expectedOpensInNewTab = true
      )
      assertLinkById(
        doc,
        "how-do-i-add-a-tax-transcript",
        "How do I add a tax to my business tax account? - video transcript",
        "/business-account/help/transcript/add-a-tax",
        "link - click:Help with your BTA: Adding a tax to your business tax account transcript"
      )
      assertLinkById(
        doc,
        "how-do-i-add-a-tax-BTA-home",
        "business tax account homepage",
        "http://localhost:9020/business-account",
        "link - click:Help with your BTA: bta home"
      )
      assertLinkById(
        doc,
        "why-register-or-stopping",
        "Self Assessment",
        "/business-account/help/self-assessment/register-or-stopping",
        "link - click:Help with your BTA: do not know why you have a business tax account self assessment"
      )
      assertLinkById(
        doc,
        "manage-best-out-of",
        "use only one ID for all your online business tax services (opens in new tab)",
        "https://www.gov.uk/government/publications/use-hmrcs-business-tax-account/use-hmrcs-business-tax-account#best_out_of",
        "link - click:Help with your BTA: How to manage your BTA account best out of",
        expectedOpensInNewTab = true
      )
      assertLinkById(
        doc,
        "manage-profile-management",
        "Manage your Government Gateway details",
        "/user-profile-redirect-frontend/profile-management",
        "link - click:Help with your BTA: Profile management"
      )
      assertLinkById(
        doc,
        "difference-PTA",
        "Read more about your personal tax account (opens in new tab)",
        "https://www.gov.uk/personal-tax-account",
        "link - click:Help with your BTA: difference between BTA and PTA more about PTA",
        expectedOpensInNewTab = true
      )
      assertLinkById(
        doc,
        "difference-read-full-list",
        "Read the full list of taxes, duties and schemes you can manage (opens in new tab)",
        "https://www.gov.uk/guidance/sign-in-to-your-hmrc-business-tax-account#tax_services",
        "link - click:Help with your BTA: difference between BTA and PTA read full list",
        expectedOpensInNewTab = true
      )
    }
  }



  }
