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

package services

import config.FrontendAppConfig
import models.PageType
import models.requests.ServiceInfoRequest
import play.api.i18n.Messages
import play.api.mvc.AnyContent
import play.twirl.api.{Html, HtmlFormat}
import views.html._
import views.html.sa._
import views.html.general._
import views.html.vat._

import javax.inject.Inject

class PageTypeResolverService @Inject()(
                                         appConfig: FrontendAppConfig
                                       ){
  def resolve(pageType: PageType)(implicit request: ServiceInfoRequest[AnyContent], messages: Messages): Html = pageType match {
    case p@PageType.HelpWithBTA => help_with_your_bta(p.name, appConfig)
    case p@PageType.RegisteringOrStopping => registering_or_stopping(p.name, appConfig)(messages)
    case p@PageType.PaymentsAndPenalties => payments_and_penalties(p.name, appConfig, request.request.saUtr)(request.serviceInfoContent)
    case p@PageType.HelpWithSATaxReturn => help_with_sa_tax_return(p.name, request.request.saUtr, appConfig)
    case p@PageType.GetEvidenceOfIncome => get_evidence_of_income(p.name, appConfig, request.request.saUtr.isDefined)(request.serviceInfoContent)
    case p@PageType.Expenses => expenses(p.name)
    case PageType.SignUpForMTD => HtmlFormat.empty 
    case PageType.ChangeContactAndAccountDetails => change_contact_and_account_details()(messages)
    case PageType.HowToAddTax => how_to_add_tax()(messages)
    case PageType.RegisterOrDeregisterVAT => register_or_deregister_for_vat()(messages)
    case PageType.HowToPayVatAndDeadlines => how_to_pay_vat_and_deadlines()(messages)
    case p@PageType.CorrectingErrorsOnReturns => correcting_errors_on_returns(p.name)(messages)
    case PageType.GetStarted => epaye_get_started()(messages)
    case PageType.ViewOrCorrectYourSubmissions => epaye_view_or_correct_submissions()(messages)
    case PageType.PayeCisRefunds => paye_cis_refunds()(messages)
    case PageType.PayeChangeCircumstance => paye_change_circumstance()(messages)
    case PageType.PayeStopEmployer => paye_stop_employer()(messages)
    case PageType.RegisterAddCT => ctax_register_add()(messages)
    case PageType.HowToPayCT => ctax_how_to_pay()(messages)
    case PageType.ClosingLimitedCompanyCT => ctax_closing_limited_company()(messages)
    case PageType.GetUtrCT => ctax_ask_utr_corporation_tax()(messages)
    case PageType.ContactHMRC => contact_hmrc()(messages)
  }
}
