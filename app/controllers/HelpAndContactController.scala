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

package controllers

import config.FrontendAppConfig
import controllers.actions._
import handlers.ErrorHandler

import javax.inject.Inject
import models.HelpCategory
import models.HelpCategory._
import models.requests.ServiceInfoRequest
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{AnyContent, MessagesControllerComponents, Result}
import services.ThresholdService
import uk.gov.hmrc.play.bootstrap.frontend.controller.FrontendController
import utils.LoggingUtil
import views.html.ct._
import views.html.epaye._
import views.html.general._
import views.html.help_and_contact
import views.html.sa._
import views.html.vat._

import scala.concurrent.ExecutionContext

class HelpAndContactController @Inject()(
                                          appConfig: FrontendAppConfig,
                                          contact_hmrc_about_ct: contact_hmrc_about_ct,
                                          register_or_deregister_corporation_tax: register_or_deregister_corporation_tax,
                                          paye_and_cis_refunds: paye_and_cis_refunds,
                                          view_check_correct_submissions: view_check_correct_submissions,
                                          help_and_contact: help_and_contact,
                                          sa_evidence: sa_evidence,
                                          expenses: expenses,
                                          helpWithMTDIT: help_with_making_tax_digital_for_income_tax,
                                          help_with_your_self_assessment_tax_return: help_with_your_self_assessment_tax_return,
                                          register_or_stopping: register_or_stopping,
                                          payments_and_deadlines: payments_and_deadlines,
                                          register_or_deregister: register_or_deregister,
                                          payment_and_penalties: payment_and_penalties,
                                          help_with_your_bta: help_with_your_bta,
                                          change_your_details: change_your_details,
                                          override val messagesApi: MessagesApi,
                                          authenticate: AuthAction,
                                          serviceInfo: ServiceInfoAction,
                                          override val controllerComponents: MessagesControllerComponents,
                                          errorHandler: ErrorHandler,
                                          thresholdService: ThresholdService
                                        ) extends FrontendController(controllerComponents)
  with I18nSupport with LoggingUtil {
  implicit val ec: ExecutionContext = controllerComponents.executionContext

  val youtubeFeatureSwitch = appConfig.youtubeLinksEnabled

  def mainPage = (authenticate andThen serviceInfo) { implicit request =>
    Ok(help_and_contact(appConfig)(request.serviceInfoContent))
  }

  def onPageLoad(category: HelpCategory, page: String) = (authenticate andThen serviceInfo) { implicit request =>
    category match {
      case CorporationTax => corporationTax(page)
      case Epaye => ePaye(page)
      case SelfAssessment => selfAssessment(page)
      case VAT => vat(page)
      case GEN => gen(page)
    }
  }

  private def corporationTax(page: String)(implicit request: ServiceInfoRequest[AnyContent]) =
    page match {
      case "contact-hmrc" => Ok(contact_hmrc_about_ct(appConfig)(request.serviceInfoContent))
      case "how-to-pay" => MovedPermanently("https://www.gov.uk/pay-corporation-tax")
      case "register-or-tell-hmrc-you-are-no-longer-trading" =>
        Ok(register_or_deregister_corporation_tax(appConfig)(request.serviceInfoContent))
      case _ =>
        warnLog(s"[HelpAndContactController][corporationTax] - Page not found: $page")
        NotFound(errorHandler.notFoundTemplate)
    }

  private def ePaye(page: String)(implicit request: ServiceInfoRequest[AnyContent]) =
    page match {
      case "get-started" => MovedPermanently("/business-account/epaye/get-started")
      case "remove" => MovedPermanently("/business-account/epaye/remove")
      case "refunds" => Ok(paye_and_cis_refunds(appConfig)(request.serviceInfoContent))
      case "view-check-correct-submissions" => {
        val monthlyRTIDateBy10thFeatureSwitch = appConfig.monthlyRTIDateBy10thFeatureSwitch
        Ok(view_check_correct_submissions(appConfig, request.request.email, monthlyRTIDateBy10thFeatureSwitch)(request.serviceInfoContent))
      }
      case "change-employee-circumstances" => MovedPermanently("/business-account/epaye/change-employee-circumstances")
      case "check-submissions" => MovedPermanently("/business-account/help/epaye/view-check-correct-submissions")
      case "latency" => MovedPermanently("/business-account/help/epaye/view-check-correct-submissions")
      case "paye-refund" => MovedPermanently("/business-account/help/epaye/refunds")
      case "" => MovedPermanently("/business-account/help")
      case _ =>
        warnLog(s"[HelpAndContactController][ePaye] - Page not found: $page")
        NotFound(errorHandler.notFoundTemplate)
    }

  private def selfAssessment(page: String)(implicit request: ServiceInfoRequest[AnyContent]) =
    page match {
      case "evidence-of-income" => {
        Ok(
          sa_evidence(appConfig,
            request.request.saUtr.isDefined)(
            request.serviceInfoContent
          )
        )
      }
      case "expenses" => {
        Ok(expenses(appConfig)(request.serviceInfoContent))
      }
      case "help-with-return" => {
        Ok(help_with_your_self_assessment_tax_return(appConfig, request.request.saUtr)(request.serviceInfoContent))
      }
      case "payment-and-penalties" => {
        Ok(payment_and_penalties(appConfig, request.request.saUtr)(request.serviceInfoContent))
      }
      case "how-to-pay" => {
        Redirect(controllers.routes.HelpAndContactController.onPageLoad(SelfAssessment, "payment-and-penalties"))
      }
      case "register-or-stopping" => Ok(register_or_stopping(appConfig)(request.serviceInfoContent))
      case "help-with-making-tax-digital-for-income-tax" => Ok(helpWithMTDIT(appConfig)(request.serviceInfoContent))
      case _ =>
        warnLog(s"[HelpAndContactController][selfAssessment] - Page not found: $page")
        NotFound(errorHandler.notFoundTemplate)
    }

  private def vat(page: String)(implicit request: ServiceInfoRequest[AnyContent]): Result = {
    page match {
      case "how-to-pay" => Ok(payments_and_deadlines(appConfig)(request.serviceInfoContent))
      case "register-or-deregister" => Ok(register_or_deregister(appConfig, thresholdService.formattedVatThreshold())(request.serviceInfoContent))
      case _ =>
        warnLog(s"[HelpAndContactController][vat] - Page not found: $page")
        NotFound(errorHandler.notFoundTemplate)
    }
  }

  private def gen(page: String)(implicit request: ServiceInfoRequest[AnyContent]) =
    page match {
      case "help-with-your-business-tax-account" => Ok(help_with_your_bta(appConfig)(request.serviceInfoContent))
      case "change-your-details" => Ok(change_your_details(appConfig)(request.serviceInfoContent))
      case _ =>
        warnLog(s"[HelpAndContactController][gen] - Page not found: $page")
        NotFound(errorHandler.notFoundTemplate)
    }

}
