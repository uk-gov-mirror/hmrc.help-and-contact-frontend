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

package views.transcripts

import play.twirl.api.HtmlFormat
import views.behaviours.ViewBehaviours
import views.html.transcripts.cant_pay_taxbill

import scala.collection.JavaConverters._

class CantPayTaxbillViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "help.cant.pay.taxbill.transcript"

  def createView = () => inject[cant_pay_taxbill].apply(frontendAppConfig)(Some(HtmlFormat.empty))(fakeRequest, messages)

  "CantPayTaxbill view" must {
    behave like normalPage(createView, messageKeyPrefix)

    "contain heading ID" in {
      val doc = asDocument(createView())
      doc.getElementsByTag("h1").attr("id") mustBe "cant-pay-taxbill-transcript"
    }

    "have correct links" in {
      val doc = asDocument(createView())
      assertLinkById(
        doc,
        "gov-link",
        "GOV.UK",
        "https://www.gov.uk/")
    }

    "have correct content" in {
      val doc = asDocument(createView())

      val elements = doc.getElementsByTag("article").first().getElementsByTag("p").asScala.toList.map(_.text())

      val contentList = List(
        "If you’re having problems paying your tax bill, don’t worry. HMRC can help you get back on track.",
        "We’ll look at your financial circumstances and how we can help you.",
        "For example, if you can’t pay by the deadline, we may be able to agree an affordable payment plan.",
        "That means you can pay in instalments. And if your financial circumstances change again, we’ll" +
          " look at changing the arrangement so you can still afford to make payments.",
        "If it’s your health or other personal difficulties causing problems, we’ll work with you or" +
          " with someone acting on your behalf to find a solution you can afford.",
        "We also work with Money Helper. They can help you access advice that’s free and independent if you’re having difficulty paying any of your debts.",
        "Remember we’re here to help.",
        "You can find out more on GOV.UK and watch our other helpful videos on YouTube."
      )

      contentList.zipAll(elements, "", "").foreach {
        case (content, element) => element mustBe content
      }
    }
  }
}