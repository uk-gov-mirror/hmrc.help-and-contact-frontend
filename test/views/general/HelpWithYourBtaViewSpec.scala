/*
 * Copyright 2024 HM Revenue & Customs
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

import models.HelpCategory.BTA
import models.PageType
import views.behaviours.ViewBehaviours
import views.html.general.help_with_your_bta

class HelpWithYourBtaViewSpec extends ViewBehaviours {

  def createView = () => help_with_your_bta(PageType.HelpWithBTA.name, frontendAppConfig)(messages)

  "Help with you BTA view" must {

    "contain heading ID" in {
      val doc = asDocument(createView())
      doc.getElementsByTag("h1").attr("id") mustBe "help-with-bta"
    }
    
    "have correct links" in {
      val doc = asDocument(createView())
      println("0")
      assertLinkById(
        doc,
        "help-with-your-bta-addTaxLink",
        "add a tax to your business tax account.",
        "http://localhost:9020/business-account/add-tax"
      )
      assertLinkById(
        doc,
        "manage-best-out-of",
        "use only one HMRC sign in for all your online business tax services (opens in new tab)",
        "https://www.gov.uk/government/publications/use-hmrcs-business-tax-account/use-hmrcs-business-tax-account#getting-the-most-from-your-business-tax-account",
        expectedOpensInNewTab = true
      )
      assertLinkById(
        doc,
        "manage-profile-management",
        "Manage your HMRC sign in details",
        "/user-profile-redirect-frontend/profile-management"
      )
      assertLinkById(
        doc,
        "difference-PTA",
        "Read more about your personal tax account (opens in new tab)",
        "https://www.gov.uk/personal-tax-account",
        expectedOpensInNewTab = true
      )
      assertLinkById(
        doc,
        "difference-read-full-list",
        "View taxes, duties and schemes you can manage (opens in new tab)",
        "https://www.gov.uk/guidance/sign-in-to-your-hmrc-business-tax-account#tax_services",
        expectedOpensInNewTab = true
      )
    }
  }



  }
