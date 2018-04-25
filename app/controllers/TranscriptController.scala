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
      videoTitle match {
        case "viewing-your-self-assessment-calculation" => Ok(viewing_your_self_assessment_calculation(appConfig)(request.serviceInfoContent))
        case "paying-your-self-assessment-tax-bill" => Ok(paying_your_self_assessment_tax_bill(appConfig)(request.serviceInfoContent))
        case "budgeting-your-self-assessment-tax-bill" => Ok(budgeting_your_self_assessment_tax_bill(appConfig)(request.serviceInfoContent))
        case "self-assessment-penalties" => Ok(self_assessment_penalties(appConfig)(request.serviceInfoContent))
        case "why-sent-tax-return" => Ok(why_sent_tax_return(appConfig)(request.serviceInfoContent))
        case _ => NotFound(errorHandler.notFoundTemplate)
      }
  }
}
