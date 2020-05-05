/*
 * Copyright 2020 HM Revenue & Customs
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

import javax.inject.{Inject, Singleton}
import controllers.routes
import play.api.i18n.Lang
import play.api.mvc.{Call, Request}
import uk.gov.hmrc.play.bootstrap.config.ServicesConfig
import uk.gov.hmrc.play.language.LanguageUtils
import uk.gov.hmrc.domain.SaUtr
import utils.PortalUrlBuilder

@Singleton
class FrontendAppConfig @Inject()(servicesConfig: ServicesConfig,
                                  override val languageUtils: LanguageUtils)
    extends PortalUrlBuilder {

  import servicesConfig._

  private def loadConfig(key: String): String = servicesConfig.getString(key)

  private lazy val contactHost: String = servicesConfig.getString("contact-frontend.host")

  lazy val analyticsToken: String           = loadConfig(s"google-analytics.token")
  lazy val analyticsHost: String            = loadConfig(s"google-analytics.host")
  lazy val btaUrl: String                   = servicesConfig.baseUrl("business-tax-account")
  lazy val loginUrl: String                 = loadConfig("urls.login")
  lazy val loginContinueUrl: String         = loadConfig("urls.loginContinue")
  lazy val requestCorporationTaxUTR: String = loadConfig("urls.requestCorporationTaxUTR")
  lazy val googleTagManagerId: String       = loadConfig(s"google-tag-manager.id")

  def getGovUrl(key: String): String = loadConfig(s"urls.external.govuk.$key")

  def getYoutubeVideoId(key: String): String = loadConfig(s"urls.external.youtube.$key")

  private lazy val businessAccountHost: String = servicesConfig.getString("urls.business-account.host")

  def getBusinessAccountUrl(key: String): String = businessAccountHost + loadConfig(s"urls.business-account.$key")

  lazy val languageTranslationEnabled: Boolean = servicesConfig.getBoolean("microservice.services.features.welsh-translation")

  def languageMap: Map[String, Lang] = Map("english" -> Lang("en"), "cymraeg" -> Lang("cy"))

  def routeToSwitchLanguage(lang: String): Call = routes.LanguageSwitchController.switchToLanguage(lang)

  private lazy val portalHost: String = loadConfig("portal.host")

  def getPortalUrl(key: String, saUtr: Option[SaUtr] = None)(implicit request: Request[_]): String =
    buildPortalUrl(portalHost + loadConfig(s"urls.portal.$key"))(saUtr)

  def sessionTimeoutInSeconds: Long  = 900
  def sessionCountdownInSeconds: Int = 60
}
