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
import views.html.transcripts.{cant_access_sa_online, new_tailor_tax_return}

import scala.collection.JavaConverters._

class CantAccessSaOnlineSpec extends ViewBehaviours {

  val messageKeyPrefix = "cant.access.sa.online"

  def createView = () => inject[cant_access_sa_online].apply(frontendAppConfig)(Some(HtmlFormat.empty))(fakeRequest, messages)

  "Can't access SA online view" must {
    behave like normalPage(createView, messageKeyPrefix)

    "contain heading ID" in {
      val doc = asDocument(createView())
      doc.getElementsByTag("h1").attr("id") mustBe "cant-access-sa-online-transcript"
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
        "If you sign in to your business tax account but can’t find the Self Assessment service, it’s likely to be for one of two reasons.",
        "You may not have used Self Assessment or sent a tax return online before, so you probably haven’t added Self Assessment to your account.",
        "To add this, choose ‘add a tax, duty or scheme’ to get started.",
        "Then choose the service you want to add – in this case: ‘Self Assessment’.",
        "Or, if you have used Self Assessment or sent a tax return online before, you may be using a different account this time.",
        "To find out if you have another account where you’ve added Self Assessment, choose ‘Check if your Self Assessment is in another account’.",
        "We’ll ask for your Unique Taxpayer Reference, or UTR.",
        "You can find your UTR on your tax return, statement of account or other Self Assessment calculations.",
        "We’ll check if the UTR has been used for another account.",
        "If it hasn’t, we’ll ask you to add the Self Assessment service to this account.",
        "If it has, you can recover your user ID and password for that account, online. You can then sign in to access and send your Self Assessment.",
        "You can find out more about Self Assessment on GOV.UK and in our other helpful videos on YouTube.",
      )

      contentList.zipAll(elements, "", "").foreach {
        case (content, element) => element mustBe content
      }
    }
  }
}


