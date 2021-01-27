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

package config

import uk.gov.hmrc.time.TaxYear

final class TaxYearCode private (private[config] val startYear: Int, private[config] val finishYear: Int) {

  override def toString: String = f"${startYear % 100}%02d${finishYear % 100}%02d"

  override def equals(obj: Any): Boolean = obj match {
    case other: TaxYearCode => other.startYear == this.startYear && other.finishYear == this.finishYear
    case _                  => false
  }

  override def hashCode(): Int = {
    val prime = 41
    prime * (prime + startYear) + finishYear
  }

}

object TaxYearCode {

  def apply(finishYear: Int): TaxYearCode  = new TaxYearCode(finishYear - 1, finishYear)
  def apply(taxYear: TaxYear): TaxYearCode = new TaxYearCode(taxYear.startYear, taxYear.finishYear)
  def previousTaxYear: TaxYearCode         = TaxYearCode(TaxYear.current.previous)

}