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

package views.transcripts

import org.jsoup.nodes.Document
import org.scalatestplus.play.PlaySpec
import play.twirl.api.HtmlFormat
import views.behaviours.ViewBehaviours
import views.html.transcripts.when_and_how_to_pay_epaye

class WhenAndHowToPayEpayeSpec extends PlaySpec {

  val messageKeyPrefix: String = "when.how.pay.epaye"

  trait EnglishTest extends ViewBehaviours {

    def createView: () => HtmlFormat.Appendable =
      () => inject[when_and_how_to_pay_epaye].apply(frontendAppConfig)(Some(HtmlFormat.empty))(fakeRequest, messages)
  }

  "when and how to pay epaye for english speakers" must {

    "behave like a normal page" in new EnglishTest {

      behave like normalPage(createView, messageKeyPrefix)

    }

    "contain a heading identifier" in new EnglishTest {

      val doc: Document = asDocument(createView())

      doc.getElementsByTag("h1").attr("id") mustBe "when-how-pay-epaye-transcript"
    }

    "contain gov uk link" in new EnglishTest {
      val doc: Document = asDocument(createView())
      doc.getElementById("gov-link").attr("href") mustBe "https://www.gov.uk/"
    }
  }

}
