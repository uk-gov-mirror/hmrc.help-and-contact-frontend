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

package models

import models.HelpCategory.VAT

sealed trait PageType {
  def name: String
  def messageKey: String
  def category: HelpCategory
}

object PageType {
  case object HelpWithBTA extends PageType {
    val name = "help-with-bta"
    val messageKey = "help_and_contact.help_with_business_tax_account"
    val category = HelpCategory.BTA
  }

  case object ChangeContactAndAccountDetails extends PageType {
    val name = "change-contact-and-account-details"
    val messageKey = "help_and_contact.change_contact_and_account_details"
    val category = HelpCategory.BTA
  }

  case object HowToAddTax extends PageType {
    val name = "how-to-add-tax"
    val messageKey = "help_and_contact.how_to_add_tax"
    val category = HelpCategory.BTA
  }

  case object RegisterOrDeregisterVAT extends PageType {
    val name = "register-or-deregister-vat"
    val messageKey = "help_and_contact.register_or_deregister"
    val category = HelpCategory.VAT
  }

  case object ViewOrCorrectYourSubmissions extends PageType {
    val name = "view-or-correct-your-submissions"
    val messageKey = "help_and_contact.view_or_correct_submissions"
    val category = HelpCategory.Epaye
  }

  // [IMPORTANT] Ensure rendering order.
  val values: Seq[PageType] = Seq(
    HelpWithBTA,
    ChangeContactAndAccountDetails,
    HowToAddTax,
    RegisterOrDeregisterVAT,
    ViewOrCorrectYourSubmissions
  )

  def withName(name: String): Option[PageType] = values.find(_.name == name)
}
