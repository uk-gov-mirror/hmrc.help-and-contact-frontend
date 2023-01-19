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

import play.api.i18n.{Lang, Messages, MessagesApi}
import play.twirl.api.HtmlFormat
import views.behaviours.ViewBehaviours
import views.html.transcripts.ct.how_to_pay_corporation_tax

import org.jsoup.nodes.Document

import scala.collection.JavaConverters._

import org.scalatestplus.play.PlaySpec

class HowToPayCorporationTaxSpec extends PlaySpec {

  val messageKeyPrefix: String = "ct.how.to.pay.transcript"

  trait EnglishTest extends ViewBehaviours {

    def createView: () => HtmlFormat.Appendable =
      () => inject[how_to_pay_corporation_tax].apply(frontendAppConfig)(Some(HtmlFormat.empty))(fakeRequest, messages)
  }

  "how to pay corporation tax for english speakers" must {

    "behave like a normal page" in new EnglishTest {

      behave like normalPage(createView, messageKeyPrefix)

    }

    "contain a heading identifier" in new EnglishTest {

      val doc: Document = asDocument(createView())

      doc.getElementsByTag("h1").attr("id") mustBe "how-to-pay-corporation-tax-heading"
    }

    "contain correct paragraph content" in new EnglishTest {

      val doc: Document = asDocument(createView())

      val elements: List[String] =
        doc.getElementsByTag("article").first().getElementsByTag("p").asScala.toList.map(_.text())

      val contentList: List[String] = List(
        "The deadline for sending us your Company tax return is usually twelve months after the end of the accounting period it covers.",
        "There’s a separate deadline to pay your Corporation Tax bill - and that’s nine months and one day after the end of the accounting period.",
        "For example, if your accounting period ends on the 30th of March, the Corporation Tax must be with HMRC within nine months and one day – so, in our example, by the 31st of December.",
        "If your payment is late, you may be charged interest and penalties.",
        "We recommend that you pay your Corporation Tax bill online using your business tax account. If you do so, your details will automatically be pre-populated for you. This reduces the risk of payments going astray.",
        "Once you’ve signed in to your business tax account, you’ll see your ‘welcome’ page. It includes an option to ‘Make a Corporation Tax payment’",
        "Select this link and then ‘choose a way to pay’. Choose your payment method and select ‘continue’.",
        "You can make a single, one-off Direct Debit payment for your Corporation Tax bill. This is not a recurring payment.",
        "If you’ve not paid by Direct Debit before, allow five days for this to be set up. It should then take three working days each time you pay, once you’ve already authorised a Direct Debit from HMRC.",
        "Or you can approve a payment direct from your bank account. Select ‘pay by bank account’ and follow the instructions.",
        "Get HMRC’s bank details to make your payment by:",
        "You can also pay by debit or corporate credit card online. Credit cards and corporate debit cards are charged a non-refundable fee. There’s no fee if you pay by personal debit card.",
        "You cannot pay with a personal credit card.",
        "If you’ve not yet registered for a business tax account, you can still pay online – search ‘Pay your Corporation Tax bill’ on GOV.UK for more information.",
        "Please remember that it’s important to use the correct payment reference for each accounting period. You’ll find the number on the payslip attached to the CT603 ‘notice to deliver a return’ – sometimes referred to as a ‘notice to file’.",
        "Once you’ve sent the payment you’ll receive an on-screen acknowledgement confirming that we’ve received it.",
        "Remember to make sure you pay by the deadline to avoid any interest or penalties.",
        "You can find more information on GOV.UK."
      )

      contentList.zipAll(elements, "", "").foreach {
        case (content, element) => element mustBe content
      }

    }

    "contain correct bullet point content" in new EnglishTest {

      val doc: Document = asDocument(createView())

      val elements: List[String] =
        doc.getElementsByTag("article").first().getElementsByTag("li").asScala.toList.map(_.text())

      val contentList: List[String] = List(
        "Faster Payments,",
        "Bacs",
        "or CHAPS"
      )

      contentList.zipAll(elements, "", "").foreach {
        case (content, element) => element mustBe content
      }

    }
  }

  trait WelshTest extends ViewBehaviours {

    override val messagesApi: MessagesApi = inject[MessagesApi]

    override def messages: Messages = messagesApi.preferred(Seq(Lang("cy")))

    def createView: () => HtmlFormat.Appendable =
      () => inject[how_to_pay_corporation_tax].apply(frontendAppConfig)(Some(HtmlFormat.empty))(fakeRequest, messages)
  }

  "how to pay corporation tax for welsh speakers" must {

    "contain correct paragraph content" in new WelshTest {

      val doc: Document = asDocument(createView())

      val elements: List[String] =
        doc.getElementsByTag("article").first().getElementsByTag("p").asScala.toList.map(_.text())

      val contentList: List[String] = List(
        "Y dyddiad cau ar gyfer anfon eich Ffurflen Dreth y Cwmni atom fel arfer yw deuddeg mis ar ôl i’r cyfnod cyfrifyddu ddod i ben.",
        "Mae dyddiad cau ar wahân ar gyfer talu eich bil Treth Gorfforaeth - sef naw mis ac un diwrnod ar ôl diwedd y cyfnod cyfrifyddu.",
        "Er enghraifft, os bydd eich cyfnod cyfrifyddu’n dod i ben ar 30 Mawrth, rhaid i’r Dreth Gorfforaeth gyrraedd CThEM cyn pen naw mis ac un diwrnod – felly, yn ein henghraifft ni, erbyn 31 Rhagfyr.",
        "Os yw eich taliad yn hwyr, gallech fod yn agored i dalu llog a chosb am dalu’n hwyr.",
        "Rydym yn argymell eich bod yn talu eich bil Treth Gorfforaeth ar-lein gan ddefnyddio eich cyfrif treth busnes. Os byddwch yn gwneud hynny, bydd eich manylion yn cael eu rhag-lenwi’n awtomatig ar eich rhan. Mae hyn yn lleihau’r risg y bydd taliadau’n mynd ar goll.",
        "Ar ôl i chi fewngofnodi i’ch cyfrif treth busnes, byddwch yn gweld eich tudalen groeso. Mae’n cynnwys opsiwn i wneud taliad Treth Gorfforaeth",
        "Cliciwch y ddolen hon ac yna dewiswch ddull o dalu. Dewiswch eich dull talu ac yna ewch yn eich blaen.",
        "Gallwch wneud taliad Debyd Uniongyrchol unigol ar gyfer eich bil Treth Gorfforaeth. Nid yw hwn yn daliad rheolaidd.",
        "Os nad ydych wedi talu drwy Ddebyd Uniongyrchol o’r blaen, caniatewch bum diwrnod i sefydlu hyn. Unwaith y byddwch eisoes wedi awdurdodi Debyd Uniongyrchol gan CThEM, dylai gymryd tri diwrnod gwaith bob tro y byddwch yn talu.",
        "Neu gallwch gymeradwyo taliad yn uniongyrchol o’ch cyfrif banc. Dewiswch yr opsiwn i dalu drwy gyfrif banc a dilyn y cyfarwyddiadau.",
        "Cael gafael ar fanylion banc CThEM i wneud trosglwyddiad banc drwy:",
        "Gallwch hefyd dalu ar-lein drwy gerdyn debyd neu gerdyn credyd corfforaethol. Codir ffi na ellir ei had-dalu ar gardiau credyd a chardiau debyd corfforaethol. Nid oes ffi yn cael ei chodi os ydych yn talu â cherdyn debyd personol.",
        "Ni allwch dalu â cherdyn credyd personol.",
        "Os nad ydych wedi cofrestru ar gyfer cyfrif treth busnes eto, gallwch dalu ar-lein o hyd – chwiliwch am ‘Pay your Corporation Tax bill’ ar GOV.UK i gael rhagor o wybodaeth.",
        "Cofiwch ei bod yn bwysig defnyddio’r cyfeirnod talu cywir ar gyfer pob cyfnod cyfrifyddu. Fe welwch y rhif ar y slip cyflog sydd ynghlwm wrth y ffurflen CT603 ‘hysbysiad i gyflwyno Ffurflen Dreth’ - a elwir weithiau’n ‘hysbysiad i gyflwyno’.",
        "Ar ôl i chi anfon y taliad, byddwch yn cael cydnabyddiaeth ar y sgrin yn cadarnhau ein bod wedi ein cyrraedd.",
        "Cofiwch wneud yn siŵr eich bod yn talu erbyn y dyddiad cau er mwyn osgoi unrhyw log neu gosbau.",
        "Gallwch ddod o hyd i ragor o wybodaeth ar GOV.UK."
      )

      contentList.zipAll(elements, "", "").foreach {
        case (content, element) => element mustBe content
      }
    }

    "contain correct bullet point content" in new WelshTest {

      val doc: Document = asDocument(createView())

      val elements: List[String] =
        doc.getElementsByTag("article").first().getElementsByTag("li").asScala.toList.map(_.text())

      val contentList: List[String] = List(
        "Taliadau Cyflymach,",
        "Bacs",
        "neu CHAPS"
      )

      contentList.zipAll(elements, "", "").foreach {
        case (content, element) => element mustBe content
      }

    }

  }

}
