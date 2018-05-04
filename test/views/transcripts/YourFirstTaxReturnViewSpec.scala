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

class YourFirstTaxReturnViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "yourFirstTaxReturnTranscript"

  def createView = () => your_first_tax_return(frontendAppConfig)(HtmlFormat.empty)(fakeRequest, messages)

  "YourFirstTaxReturn view" must {
    behave like normalPage(createView, messageKeyPrefix)

    "have correct content" in {
      val doc = asDocument(createView())

      val elements = doc.getElementsByTag("article").first().getElementsByTag("p").asScala.toList.map(_.text())

      val contentList = List(
        "This is one of a series of videos about online Self Assessment.",
        "The tax return due by 31 January 2018 is for the tax year 6 April 2016 to 5 April 2017.",
        "Self Assessment isn’t just for self-employed people. Everyone who has to complete a tax return must do so, even if there " +
          "is nothing to pay. You may be charged a penalty if you don’t.",
        "There’s a useful tool on GOV.UK to help you decide if you need to complete a tax return, and you don’t need to be " +
          "registered to use it.",
        "You’ll need to register for Self Assessment by 5 October following the end of the tax year or you may be charged a penalty.",
        "There’s a short video in this series on how to register.",
        "Once you’ve registered and activated the online service you’re ready to complete your first Self Assessment tax return. " +
          "All income has to be included when you complete your tax return, so you’ll need your records.",
        "These are some of the things you might need. There’s more about record keeping on the GOV.UK website. Once you have all " +
          "your information, go to GOV.UK and sign in with the user ID and password you got when you signed up to Self Assessment " +
          "online.",
        "There are links to help if you have any problems.",
        "The ‘welcome’ page gives you some information about who can and who can’t use the online service. Most people can.",
        "Before you fill in your tax return, you’ll be asked to ‘Tell us about you’. You must make an entry at every box with a red " +
          "star beside it.",
        "Many people are surprised to find the Self Assessment online screens are very user friendly and help you through the process.",
        "This screen is letting you know the date of birth has been entered incorrectly. It is now correct 30/01/1960. You must show " +
          "four digits for the year, just ‘60 is not accepted.",
        "In the next section, you can ‘Tailor your return’. You answer a series of Yes/No questions to ensure that you only fill in " +
          "what you need.",
        "If you are unsure about a question, select the question mark at the side. You’ll then find some guidance notes about what " +
          "you need to do. Every time you use the ‘next’ button, what you have entered has been saved.",
        "You don’t have to complete it all in one go. You can leave the return at any time and come back to it later. This is helpful " +
          "if you don’t have everything to hand. You can use the ‘Save’ button to do this.",
        "The ‘Check your progress’ button helps you to see how much you have completed and still have to do. The bar at the top of " +
          "the screen moves along as you fill out the return.",
        "When you have filled in your return, you’ll be asked to check that everything is correct. This shows a summary of what you " +
          "have filled in and confirms what you’ve said you don’t need to fill in.",
        "At this stage you can go back and make changes. When you’re sure everything is correct, you can ‘view your calculation’. " +
          "The online tax return works out how much you are due to pay.",
        "You can then save a copy of your return for your own records. Remember to send the return to HMRC. Do this in the " +
          "‘submit return’ section. You’ll need your user ID and password to do this.",
        "You’ll receive a message online, to confirm that HMRC has received your return, when you submit it.",
        "You’ll find more help and support on GOV.UK. Webinars and other videos about Self Assessment are available from HMRC.",
        "Thanks for watching."
      )

      contentList.zipAll(elements, "", "").foreach {
        case (content, element) => element mustBe content
      }
    }
  }
}