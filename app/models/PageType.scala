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
  def id: String
}

object PageType {
  case object HelpWithBTA extends PageType {
    val name = "help-with-bta"
    val messageKey = "help_and_contact.help_with_business_tax_account"
    val category = HelpCategory.BTA
    val id = "help-with-bta"
  }

  case object ChangeContactAndAccountDetails extends PageType {
    val name = "change-contact-and-account-details"
    val messageKey = "help_and_contact.change_contact_and_account_details"
    val category = HelpCategory.BTA
    val id = "change-contact-and-account-details"
  }

  case object HowToAddTax extends PageType {
    val name = "how-to-add-tax"
    val messageKey = "help_and_contact.how_to_add_tax"
    val category = HelpCategory.BTA
    val id = "how-to-add-tax"
  }

  case object RegisterOrDeregisterVAT extends PageType {
    val name = "register-or-deregister-vat"
    val messageKey = "help_and_contact.register_or_deregister"
    val category = HelpCategory.VAT
    val id = "register-or-deregister-vat"
  }

  case object HowToPayVatAndDeadlines extends PageType {
    val name = "how_to_pay_vat_and_deadlines"
    val messageKey = "vat.payments_and_deadlines"
    val category = HelpCategory.VAT
    val id = "how_to_pay_vat_and_deadlines"
  }

  case object GetStarted extends PageType {
    val name = "get-started-PAYE"
    val messageKey = "help_and_contact.get_started"
    val category = HelpCategory.Epaye
    val id = "get-started-PAYE"
  }

  case object ViewOrCorrectYourSubmissions extends PageType {
    val name = "view-or-correct-your-submissions"
    val messageKey = "help_and_contact.view_or_correct_submissions"
    val category = HelpCategory.Epaye
    val id = "view-or-correct-your-submissions"
  }
  case object RegisterAddCT extends PageType {
    val name = "register-add-corporation-tax"
    val messageKey = "help_and_contact.register_add_corporation_tax"
    val category = HelpCategory.CorporationTax
    val id = "register-add-corporation-tax"
  }

  case object HowToPayCT extends PageType {
    val name = "how-to-pay"
    val messageKey = "help_and_contact.how_to_pay_corporation_tax"
    val category = HelpCategory.CorporationTax
    val id = "how-to-pay"
  }

  case object ClosingLimitedCompanyCT extends PageType {
    val name = "closing-limited-company"
    val messageKey = "help_and_contact.closing_limited_company"
    val category = HelpCategory.CorporationTax
    val id = "closing-limited-company"
  }

  case object GetUtrCT extends PageType {
    val name = "ask-your-corporation-tax-utr"
    val messageKey = "help_and_contact.get_ct_utr"
    val category = HelpCategory.CorporationTax
    val id = "ask-your-corporation-tax-utr"
  }

  case object ContactHMRC extends PageType {
    val name = "contact-hmrc"
    val messageKey = "help_and_contact.contact_hmrc.nav"
    val category = HelpCategory.GEN
    val id = "contact-hmrc"
  }

  case object PayeStopEmployer extends PageType {
    val name = "stop-being-an-employer"
    val messageKey = "help_and_contact.paye_stop_being_an_employer"
    val category = HelpCategory.Epaye
    val id = "stop-being-an-employer"
  }

  case object PayeChangeCircumstance extends PageType {
    val name = "changes-in-employee-circumstances"
    val messageKey = "help_and_contact.paye_changes_employee_circumstances"
    val category = HelpCategory.Epaye
    val id = "changes-in-employee-circumstances"
  }

  case object PayeCisRefunds extends PageType {
    val name = "paye-and-cis-refunds"
    val messageKey = "help_and_contact.paye_cis_refunds"
    val category = HelpCategory.Epaye
    val id = "paye-and-cis-refunds"
  }


  // [IMPORTANT] Ensure rendering order.
  val values: Seq[PageType] = Seq(
    HelpWithBTA,
    ChangeContactAndAccountDetails,
    HowToAddTax,
    RegisterOrDeregisterVAT,
    HowToPayVatAndDeadlines,
    GetStarted,
    ViewOrCorrectYourSubmissions,
    PayeCisRefunds,
    PayeChangeCircumstance,
    PayeStopEmployer,
    RegisterAddCT,
    HowToPayCT,
    ClosingLimitedCompanyCT,
    GetUtrCT,
    ContactHMRC
  )

  def withName(name: String): Option[PageType] = values.find(_.name == name)
}
