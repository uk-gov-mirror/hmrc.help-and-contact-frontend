/*
 * Copyright 2018 HM Revenue & Customs
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
import views.html.transcripts._

import scala.collection.JavaConverters._

class RegisteringForSelfAssessmentViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "registering.for.self.assessment.transcript"

  def createView = () => registering_for_self_assessment(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "RegisteringForSelfAssessment view" must {
    behave like normalPage(createView, messageKeyPrefix)

    "have correct content" in {
      val doc = asDocument(createView())
      val elements: List[String] = doc.getElementsByTag("article").first().getElementsByTag("p").asScala.toList.map(_.text())
      val contentList: List[String] = List(
        "One of a series of videos about online Self Assessment tax returns.",
        "If you need to complete a tax return, you’ll need to register with HMRC.",
        "If you’re self-employed you need to register for Self Assessment and Class 2 National Insurance if any of these apply.",
        "You need to do this as soon as possible once you know you need to fill in a tax return, even if you’ve completed tax returns before, but no later than 5 October after the end of the tax year.",
        "If you need to complete a tax return for another reason, you must register by 5 October following the end of the tax year.",
        "If you don’t register on time, you could be fined.",
        "How you register depends on why you need to complete a tax return.",
        "If you’ve sent tax returns before, but didn’t have to send one last year, you still need to register.",
        "You’ll need your 10-digit Unique Taxpayer Reference or UTR from before and once you’re registered, you’ll also be able to use an online account you previously set up to file your tax return.",
        "To register if you’re not self-employed select register now.",
        "You use form SA1. There are two versions available.",
        "The first and quickest is designed to be filled in and sent online, and the second which is designed to be completed online, printed and posted to HMRC.",
        "If you’ve not sent a return before, you’ll receive a letter within 14 days with your Unique Taxpayer reference (UTR).",
        "You’ll then need to create a new account. Follow the guidance and enrol for Self Assessment online services using your UTR.",
        "During the process you’ll create a password and a User ID is given on-screen. Keep these safe as you’ll need them whenever you sign in online.",
        "Lastly you’ll need to activate the service using the code sent in the post within a further 2 weeks. You won’t need to do this again.",
        "When you’re self-employed the process is slightly different. If you’ve sent a return before you register online using form CWF1.",
        "You need your UTR from before, complete your personal details and details of the business. Once you’ve checked the details on the next page you can submit online.",
        "If you haven’t sent a return before, you still register online but you’ll create your government gateway account and be enrolled for the online service at the same time.",
        "You’ll receive a letter with your UTR and your activation code within 2 weeks so you can activate your online service when you first log in.",
        "To register as a partner in a partnership you use form SA401. There are two versions one to submit online and the other to fill in, print and post.",
        "You’ll need the UTR for the Partnership as well as your own UTR if you already have one.",
        "If it’s a new partnership the ‘nominated partner’ will also have to register the partnership. Just follow the online guidance.",
        "You’ll find more help and support on GOV.UK.",
        "Webinars and other videos about Self Assessment are available from HMRC.",
        "Thanks for watching."
      )

              contentList.zip(elements).foreach {
              case (content, element) => element mustBe content
            }
    }
  }
}
