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

package models

import play.api.mvc.JavascriptLiteral
import utils.WithName

sealed trait HelpCategory
object HelpCategory {
  case object CorporationTax extends WithName("corporation-tax") with HelpCategory
  case object Epaye extends WithName("epaye") with HelpCategory
  case object SelfAssessment extends WithName("self-assessment") with HelpCategory
  case object VAT extends WithName("vat") with HelpCategory

  val values = Seq(VAT, SelfAssessment, CorporationTax)

  implicit val jsLiteral: JavascriptLiteral[HelpCategory] = new JavascriptLiteral[HelpCategory] {
    override def to(value: HelpCategory): String = value match {
      case CorporationTax => "CorporationTax"
      case Epaye => "Epaye"
      case SelfAssessment => "SelfAssessment"
      case VAT => "VAT"
    }
  }
}