/*
 * Copyright 2023 HM Revenue & Customs
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
import views.html.transcripts.how_to_find_sa_penalties

import scala.collection.JavaConverters._

class HowToFindSaPenaltiesViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "howToFindSaPenaltiesTranscript"

  def createView = () => inject[how_to_find_sa_penalties].apply(frontendAppConfig)(Some(HtmlFormat.empty))(fakeRequest, messages)

  "HowToFindSaPenalties view" must {
    behave like normalPage(createView, messageKeyPrefix)

    "contain heading ID" in {
      val doc = asDocument(createView())
      doc.getElementsByTag("h1").attr("id") mustBe "how-to-find-sa-penalties-transcript"
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
        "You may have heard there are penalties if you’re late sending and paying your online tax return to HMRC.",
        "Remember you should send and pay your online tax return by 31st January, if you don’t you’ll usually be charged a penalty.",
        "You can check if you need to send a tax return by using our Self Assessment checker on GOV.UK.",
        "If you do need to send one and we receive your return late you’ll get a penalty of one hundred pounds [£100] " +
          "if your tax return is up to three months late. These penalties won’t be reduced, even if you have nothing to pay on your return.",
        "If it’s more than three months late you’ll be charged an additional ten pounds a day for up to ninety days.",
        "After this you’ll be charged further penalties until we receive your tax return and you’ve paid your tax bill. " +
          "You’ll also be charged interest on late payments.",
        "If you don’t have to send a tax return you can ask to be removed from Self Assessment through your personal" +
          " tax account. If you don’t tell us you’ve stopped being self-employed, or are no longer part of Self Assessment, you may also be charged a penalty.",
        "The easiest way to avoid penalties is to send your return and pay any tax due by 31st January. You’ll find more help and information " +
          "about Self assessment penalties, how to appeal them and the personal tax account on GOV.UK."
      )

      contentList.zipAll(elements, "", "").foreach {
        case (content, element) => element mustBe content
      }

    }
  }
}