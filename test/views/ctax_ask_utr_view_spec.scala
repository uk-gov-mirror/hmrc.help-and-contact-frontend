/*
 * Copyright 2021 HM Revenue & Customs
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

import models.PageType
import play.twirl.api.{Html, HtmlFormat}
import views.behaviours.ViewBehaviours
import views.html.ct.ctax_ask_utr_corporation_tax

class CtaxAskUtrViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "ctax.getctutr"

  val serviceInfoContent: Option[Html] = Some(Html("<div>Service info content</div>"))

  def createView(): () => HtmlFormat.Appendable =
    () =>  ctax_ask_utr_corporation_tax.apply(PageType.GetUtrCT.name)(messages)


  "Ask for a copy of your Corporation Tax UTR view" must {

    "contain heading ID" in {
      val doc = asDocument(createView()())
      doc.getElementsByTag("h1").attr("id") mustBe "page-title-ask-your-corporation-tax-utr"
    }


    "have correct content" in {
      val doc = asDocument(createView()())
      doc.text() must include("You can use an online service to ask for a copy of your Corporation Tax Unique Taxpayer Reference (UTR).")
    }

    "have correct links" in {
      val doc = asDocument(createView()())
      assertLinkByLinkText(
        doc,
        "Ask for a copy of your Corporation Tax UTR (opens in a new tab)",
        "https://www.tax.service.gov.uk/ask-for-copy-of-your-corporation-tax-utr"
      )
    }
  }
}
