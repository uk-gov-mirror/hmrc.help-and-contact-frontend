/*
 * Copyright 2020 HM Revenue & Customs
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

import views.behaviours.ViewBehaviours
import views.html.covid

class covidSectionSpec extends ViewBehaviours {

  val messageKeyPrefix = "help_and_contact"

  def createView =
    () =>
      covid(frontendAppConfig)(
        fakeRequest,
        messages
      )

  "Help and contact view" must {

    "contain heading" in {
      val doc = asDocument(createView())
      doc.getElementById("covid-help").html() mustBe "Coronavirus (COVID-19) support"
    }

    "have first row links" in {
      val doc = asDocument(createView())
      assertLinkById(
        doc,
        "covid-digital-assistant-for-help-and-support",
        "Digital assistant for help and support (opens in new tab)",
        "https://www.tax.service.gov.uk/ask-hmrc/virtual-assistant/support-for-coronavirus",
        "link - click:Help and contact:covid help digital assistant",
          expectedOpensInNewTab = true
      )
      assertLinkById(
        doc,
        "covid-videos-hmrc-youtube-channel",
        "Videos and webinars (HMRC You Tube channel) (opens in new tab)",
        "https://www.youtube.com/playlist?list=PL8EcnheDt1zhTsyhT9ak3xiXnmlvbHJJV",
        "link - click:Help and contact:covid help videos and webinars(opens in new tab)",
        expectedOpensInNewTab = true
      )
      assertLinkById(
        doc,
        "covid-financial-support-for-businesses",
        "Financial support for businesses (opens in new tab)",
        "https://www.gov.uk/government/collections/financial-support-for-businesses-during-coronavirus-covid-19#business-support-grant-funds",
        "link - click:Help and contact:covid help support businesses",
        expectedOpensInNewTab = true
      )
    }

    "have second row links" in {
      val doc = asDocument(createView())
      assertLinkById(
        doc,
        "covid-debt-management-for-tax-bills",
        "Debt management for tax bills: contact HMRC (opens in new tab)",
         "https://www.gov.uk/government/organisations/hm-revenue-customs/contact/coronavirus-covid-19-helpline",
        "link - click:Help and contact:covid help webchat tax bills",
        expectedOpensInNewTab = true
      )
      assertLinkById(
        doc,
        "covid-coronavirus-job-retention_scheme",
        "Coronavirus Job Retention Scheme (CJRS): contact HMRC (opens in new tab)",
        "https://www.gov.uk/government/organisations/hm-revenue-customs/contact/get-help-with-the-coronavirus-job-retention-scheme",
        "link - click:Help and contact:covid help webchat cjrs",
        expectedOpensInNewTab = true

      )
      assertLinkById(
        doc,
        "covid-self-employment-income-support-scheme",
        "Self-Employment Income Support Scheme (SEISS): contact HMRC (opens in new tab)",
        "https://www.gov.uk/government/organisations/hm-revenue-customs/contact/get-help-with-the-self-employment-income-support-scheme",
        "link - click:Help and contact:covid help webchat seiss",
          expectedOpensInNewTab = true
      )
    }
  }

}
