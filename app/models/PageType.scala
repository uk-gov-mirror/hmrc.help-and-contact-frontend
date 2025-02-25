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

sealed trait PageType {
  def name: String
  def messageKey: String
  def category: HelpCategory
  def externalUrl: Option[String] = None
  def route: String
  def showOnPage: Boolean
}

object PageType {
  case object HelpWithBTA extends PageType {
    val name = "help-with-bta"
    val messageKey = "help_and_contact.help_with_business_tax_account"
    val category = HelpCategory.BTA
    val route = "help-with-your-business-tax-account"
    val showOnPage = true
  }

  case object ChangeContactAndAccountDetails extends PageType {
    val name = "change-contact-and-account-details"
    val messageKey = "help_and_contact.change_contact_and_account_details"
    val category = HelpCategory.BTA
    val route = "change-your-details"
    val showOnPage = true
  }

  case object HowToAddTax extends PageType {
    val name = "how-to-add-tax"
    val messageKey = "help_and_contact.how_to_add_tax"
    val category = HelpCategory.BTA
    val route = "how-to-add-tax"
    val showOnPage = true
  }

  case object RegisterOrDeregisterVAT extends PageType {
    val name = "register-or-deregister-vat"
    val messageKey = "help_and_contact.register_or_deregister"
    val category = HelpCategory.VAT
    val route = "vat/register-or-deregister"
    val showOnPage = true
  }

  case object HowToPayVatAndDeadlines extends PageType {
    val name = "how-to-pay-vat-and-deadlines"
    val messageKey = "vat.payments_and_deadlines"
    val category = HelpCategory.VAT
    val route = "vat/how-to-pay"
    val showOnPage = true
  }

  case object CorrectingErrorsOnReturns extends PageType {
    val name = "correcting-errors-on-returns"
    val messageKey = "vat.correcting_errors_on_returns"
    val category = HelpCategory.VAT
    val route = "correcting-errors-on-returns"
    val showOnPage = true
  }

  case object GetStarted extends PageType {
    val name = "get-started-paye"
    val messageKey = "help_and_contact.get_started"
    val category = HelpCategory.Epaye
    val route = "epaye"
    val showOnPage = true
  }

  case object ViewOrCorrectYourSubmissions extends PageType {
    val name = "view-or-correct-your-submissions"
    val messageKey = "help_and_contact.view_or_correct_submissions"
    val category = HelpCategory.Epaye
    val route = "epaye/check-submissions"
    val showOnPage = true
  }


  case object RegisterAddCT extends PageType {
    val name = "register-add-corporation-tax"
    val messageKey = "help_and_contact.register_add_corporation_tax"
    val category = HelpCategory.CorporationTax
    val route = "corporation-tax/register-or-tell-hmrc-you-are-no-longer-trading"
    val showOnPage = true
  }

  case object HowToPayCT extends PageType {
    val name = "how-to-pay"
    val messageKey = "help_and_contact.how_to_pay_corporation_tax"
    val category = HelpCategory.CorporationTax
    val route = "corporation-tax/how-to-pay"
    val showOnPage = true
  }

  case object ClosingLimitedCompanyCT extends PageType {
    val name = "closing-limited-company"
    val messageKey = "help_and_contact.closing_limited_company"
    val category = HelpCategory.CorporationTax
    val route = "closing-limited-company"
    val showOnPage = true
  }

  case object GetUtrCT extends PageType {
    val name = "ask-your-corporation-tax-utr"
    val messageKey = "help_and_contact.get_ct_utr"
    val category = HelpCategory.CorporationTax
    val route = "ask-for-copy-of-your-corporation-tax-utr"
    val showOnPage = true

  }

  case object ContactHMRC extends PageType {
    val name = "contact-hmrc"
    val messageKey = "help_and_contact.contact_hmrc.nav"
    val category = HelpCategory.GEN
    val route = "corporation-tax/contact-hmrc"
    val showOnPage = true
  }

  case object PayeStopEmployer extends PageType {
    val name = "stop-being-an-employer"
    val messageKey = "help_and_contact.paye_stop_being_an_employer"
    val category = HelpCategory.Epaye
    val route = "stop-being-an-employer"
    val showOnPage = true
  }

  case object PayeChangeCircumstance extends PageType {
    val name = "changes-in-employee-circumstances"
    val messageKey = "help_and_contact.paye_changes_employee_circumstances"
    val category = HelpCategory.Epaye
    val route = "change-employee-circumstances"
    val showOnPage = true
  }

  case object PayeCisRefunds extends PageType {
    val name = "paye-and-cis-refunds"
    val messageKey = "help_and_contact.paye_cis_refunds"
    val category = HelpCategory.Epaye
    val route = "refunds"
    val showOnPage = true
  }

  case object PaymentsAndPenalties extends PageType {
    val name = "payments-and-penalties"
    val messageKey = "help_and_contact.payments_and_penalties"
    val category = HelpCategory.SelfAssessment
    val route = "self-assessment/payment-and-penalties"
    val showOnPage = true
  }


  case object RegisteringOrStopping extends PageType {
    val name = "registering-or-stopping"
    val messageKey = "help_and_contact.registering_or_stopping"
    val category = HelpCategory.SelfAssessment
    val route = "self-assessment/register-or-stopping"
    val showOnPage = true
  }

  case object HelpWithSATaxReturn extends PageType {
    val name = "help-with-sa-tax-return"
    val messageKey = "help_and_contact.help_with_sa_tax_return"
    val category = HelpCategory.SelfAssessment
    val route = "self-assessment/help-with-return"
    val showOnPage = true
  }

  case object GetEvidenceOfIncome extends PageType {
    val name = "get-evidence-of-income"
    val messageKey = "help_and_contact.get_evidence_of_income"
    val category = HelpCategory.SelfAssessment
    val route = "self-assessment/evidence-of-income"
    val showOnPage = true
  }

  case object Expenses extends PageType {
    val name = "expenses"
    val messageKey = "help_and_contact.expenses"
    val category = HelpCategory.SelfAssessment
    val route = "self-assessment/expenses"
    val showOnPage = true
  }

  case object SignUpForMTD extends PageType {
    val name = "sign-up-for-mtd"
    val messageKey = "help_and_contact.sign_up_for_mtd_for_income_tax_new_tab"
    val category = HelpCategory.SelfAssessment
    val route = "https://www.gov.uk/guidance/sign-up-your-business-for-making-tax-digital-for-income-tax"
    val showOnPage = true

    override val externalUrl = Some("https://www.gov.uk/guidance/sign-up-your-business-for-making-tax-digital-for-income-tax")
  }

  case object HelpWithMakingTaxDigital extends PageType {
    val name = "help-with-making-tax-digital-for-income-tax"
    val messageKey = "ask-for-copy-of-your-corporation-tax-utr"
    val category = HelpCategory.SelfAssessment
    val route = "help-with-making-tax-digital-for-income-tax"
    val showOnPage = false

    override val externalUrl = Some("https://www.gov.uk/guidance/sign-up-your-business-for-making-tax-digital-for-income-tax")
  }

  // [IMPORTANT] Ensure rendering order.
  val values: Seq[PageType] = Seq(
    // Business Tax Account
    HelpWithBTA,
    ChangeContactAndAccountDetails,
    HowToAddTax,

    // Self Assessment
    PaymentsAndPenalties,
    RegisteringOrStopping,
    HelpWithSATaxReturn,
    GetEvidenceOfIncome,
    Expenses,
    SignUpForMTD,

    // VAT
    RegisterOrDeregisterVAT,
    HowToPayVatAndDeadlines,
    CorrectingErrorsOnReturns,

    // EPAYE
    GetStarted,
    ViewOrCorrectYourSubmissions,
    PayeCisRefunds,
    PayeChangeCircumstance,
    PayeStopEmployer,

    // CT
    RegisterAddCT,
    HowToPayCT,
    ClosingLimitedCompanyCT,
    GetUtrCT,

    // Contact HMRC
    ContactHMRC,

    //non navigation pages
    HelpWithMakingTaxDigital
  )

  def withName(name: String): Option[PageType] = values.find(_.name == name)
}