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

package views.sa

import views.behaviours.ViewBehaviours
import views.html.sa.help_with_making_tax_digital_for_income_tax
import play.twirl.api.HtmlFormat
import collection.JavaConverters._



class HelpWithMakingTaxDigitalForIncomeTaxViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "helpWithMTDITSA"

  def createView() = () => inject[help_with_making_tax_digital_for_income_tax].apply(frontendAppConfig)(Some(HtmlFormat.empty))(fakeRequest, messages)

  "SelfAssessmentTaxReturnCheck view" must {
    behave like normalPage(createView(), messageKeyPrefix)

    "contain heading ID" in {
      val doc = asDocument(createView()())
      doc.getElementsByTag("h1").attr("id") mustBe "help-with-making-tax-digital-for-income-tax"
    }

    "have correct h2 headings" in {
      val listOfHeadings: List[String] = List(
        "Making Tax Digital For Income Tax",
        "Self Assessment and Making Tax Digital for Income Tax",
        "Example: Changing from Self Assessment to Making Tax Digital for Income Tax",
        "Signing up for Making Tax Digital for Income Tax",
        "Getting Making Tax Digital for Income Tax software",
        "More help"
      )
      val doc = asDocument(createView()())
      val headings = doc.getElementsByTag("article").first.getElementsByTag("h2").asScala.toList.map(_.text())

      headings mustBe listOfHeadings
    }

    "have correct links" in {
      val doc = asDocument(createView()())
      assertLinkById(
        doc,
        "mtd-itsa-send-quarterly-updates",
        "send quarterly updates",
        "https://www.gov.uk/guidance/follow-the-rules-for-making-tax-digital-for-income-tax#send-updates-using-software",
        expectedOpensInNewTab = false
      )
      assertLinkById(
        doc,
        "mtd-itsa-final-declaration",
        "final declaration for Making Tax Digital for Income Tax",
        "https://www.gov.uk/guidance/follow-the-rules-for-making-tax-digital-for-income-tax#submitting-a-final-declaration",

        expectedOpensInNewTab = false
      )
      assertLinkById(
        doc,
        "mtd-itsa-find-out-more",
        "Find out more about Making Tax Digital for Income Tax",
        "https://www.gov.uk/guidance/follow-the-rules-for-making-tax-digital-for-income-tax",
        expectedOpensInNewTab = false
      )
      assertLinkById(
        doc,
        "mtd-itsa-sign-up",
        "sign up for Making Tax Digital for Income Tax",
        "https://www.gov.uk/guidance/sign-up-your-business-for-making-tax-digital-for-income-tax",
        expectedOpensInNewTab = false
      )
      assertLinkById(
        doc,
        "mtd-itsa-add-tax",
        "add it to your business tax account",
        "http://localhost:9020/business-account/add-tax"
      )
      assertLinkById(
        doc,
        "mtd-itsa-compatible-software",
        "Find software for Making Tax Digital for Income Tax",
        "https://www.gov.uk/guidance/find-software-thats-compatible-with-making-tax-digital-for-income-tax",

        expectedOpensInNewTab = false
      )
      assertLinkById(
        doc,
        "mtd-itsa-collections-mtd-itsa",
        "Making Tax Digital for Income Tax",
        "https://www.gov.uk/government/collections/making-tax-digital-for-income-tax",

        expectedOpensInNewTab = false
      )
    }
  }
}
