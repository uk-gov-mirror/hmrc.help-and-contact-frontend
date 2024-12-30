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
}

object PageType {
  case object HelpWithBTA extends PageType {
    val name = "help-with-bta"
    val messageKey = "help_and_contact.help_with_business_tax_account"
    val category = HelpCategory.BTA
  }

  case object RegisterAddCT extends PageType {
    val name = "register-add-corporation-tax"
    val messageKey = "help_and_contact.register_add_corporation_tax"
    val category = HelpCategory.CorporationTax
  }

  case object HowToPayCT extends PageType {
    val name = "how-to-pay"
    val messageKey = "help_and_contact.how_to_pay_corporation_tax"
    val category = HelpCategory.CorporationTax
  }

  // [IMPORTANT] Ensure rendering order.
  val values: Seq[PageType] = Seq(
    HelpWithBTA,
    RegisterAddCT,
    HowToPayCT
  )

  def withName(name: String): Option[PageType] = values.find(_.name == name)
}