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
        "covid-deferring-income-tax-payments",
        "Deferring Income Tax payments",
        "https://www.gov.uk/government/publications/guidance-to-employers-and-businesses-about-covid-19/covid-19-support-for-businesses#support-for-businesses-through-deferring-income-tax-payments",
        "link - click:Help and contact:Deferring Income Tax payments"
      )
      assertLinkById(
        doc,
        "covid-deferring-vat-payments",
        "Deferring VAT payments",
        "https://www.gov.uk/government/publications/guidance-to-employers-and-businesses-about-covid-19/covid-19-support-for-businesses#support-for-businesses-through-deferring-income-tax-payments",
        "link - click:Help and contact:Deferring VAT payments"
      )
      assertLinkById(
        doc,
        "covid-sick-pay-support-for-small-and-medium-sized-businesses",
        "Sick pay support for small and medium sized businesses",
        "https://www.gov.uk/government/publications/guidance-to-employers-and-businesses-about-covid-19/covid-19-support-for-businesses#support-for-businesses-who-are-paying-sick-pay-to-employees",
        "link - click:Help and contact:Sick pay support for small and medium sized businesses"
      )
    }

    "have second row links" in {
      val doc = asDocument(createView())
      assertLinkById(
        doc,
        "covid-self-employed-worker-support",
        "Self-employed worker support",
         "https://www.gov.uk/government/publications/guidance-to-employers-and-businesses-about-covid-19/covid-19-support-for-businesses#support-for-self-employed-through-the-self-employment-income-support-scheme",
        "link - click:Help and contact:Self-employed worker support"
      )
      assertLinkById(
        doc,
        "covid-support-for-all-businesses-paying-tax",
        "Support for all businesses paying tax",
        "https://www.gov.uk/government/publications/guidance-to-employers-and-businesses-about-covid-19/covid-19-support-for-businesses#support-for-businesses-paying-tax-time-to-pay-service",
        "link - click:Help and contact:Support for all businesses paying tax"
      )
      assertLinkById(
        doc,
        "covid-support-for-uk-employers-operating-paye-under-the-coronavirus-job-retention-scheme",
        "Support for UK employers operating PAYE under the Coronavirus Job Retention Scheme",
        "https://www.gov.uk/government/publications/guidance-to-employers-and-businesses-about-covid-19/covid-19-support-for-businesses#support-for-businesses-through-the-coronavirus-job-retention-scheme",
        "link - click:Help and contact:Support for UK employers operating PAYE under the Coronavirus Job Retention Scheme"
      )
    }
  }

}
