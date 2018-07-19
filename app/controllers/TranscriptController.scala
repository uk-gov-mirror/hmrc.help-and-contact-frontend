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

package controllers

import javax.inject.Inject

import config.FrontendAppConfig
import controllers.actions._
import handlers.ErrorHandler
import play.api.i18n.{I18nSupport, MessagesApi}
import uk.gov.hmrc.play.bootstrap.controller.FrontendController
import views.html.transcripts._

class TranscriptController @Inject()(appConfig: FrontendAppConfig,
                                     override val messagesApi: MessagesApi,
                                     authenticate: AuthAction,
                                     serviceInfo: ServiceInfoAction,
                                     errorHandler: ErrorHandler) extends FrontendController with I18nSupport {

  def onPageLoad(videoTitle: String) = (authenticate andThen serviceInfo) {
    implicit request =>

      val mapOfViews = Map(
        "viewing-your-self-assessment-calculation" -> viewing_your_self_assessment_calculation(appConfig)(request.serviceInfoContent),
        "paying-your-self-assessment-tax-bill" -> paying_your_self_assessment_tax_bill(appConfig)(request.serviceInfoContent),
        "budgeting-your-self-assessment-tax-bill" -> budgeting_your_self_assessment_tax_bill(appConfig)(request.serviceInfoContent),
        "self-assessment-penalties" -> self_assessment_penalties(appConfig)(request.serviceInfoContent),
        "why-sent-tax-return" -> why_sent_tax_return(appConfig)(request.serviceInfoContent),
        "your-first-tax-return" -> your_first_tax_return(appConfig)(request.serviceInfoContent),
        "tailor-your-tax-return" -> tailor_your_tax_return(appConfig)(request.serviceInfoContent),
        "your-self-employed-tax-return" -> your_self_employed_tax_return(appConfig)(request.serviceInfoContent),
        "class-2-national-insurance-changes" -> class_2_national_insurance_changes(appConfig)(request.serviceInfoContent),
        "your-income-from-property-tax-return" -> your_income_from_property_tax_return(appConfig)(request.serviceInfoContent),
        "expenses-if-you-are-self-employed" -> expenses_if_you_are_self_employed(appConfig)(request.serviceInfoContent),
        "calculating-motoring-expenses" -> calculating_motoring_expenses(appConfig)(request.serviceInfoContent)
      )

      mapOfViews.get(videoTitle).fold(NotFound(errorHandler.notFoundTemplate)) {
        view => Ok(view)
      }
  }
}
