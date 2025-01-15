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

import controllers.ControllerSpecBase
import controllers.actions.mocks.MockAuth
import models.requests.{AuthenticatedRequest, ServiceInfoRequest}
import org.scalatestplus.mockito.MockitoSugar
import play.api.i18n.Messages
import play.twirl.api.{Html, HtmlFormat}
import models.{PageType, SaUtr}
import org.scalatest.concurrent.ScalaFutures
import play.api.mvc.{AnyContent, AnyContentAsEmpty, Request}
import play.api.test.FakeRequest

class PageTypeResolverServiceSpec extends ControllerSpecBase with MockitoSugar with MockAuth with ScalaFutures{

  implicit val message: Messages = messages

  def fakeServiceInfoRequest(utr: Option[SaUtr] = None): ServiceInfoRequest[AnyContent] =
    ServiceInfoRequest[AnyContent](AuthenticatedRequest(FakeRequest("", ""), utr, Some("user@example.com")), Some(HtmlFormat.empty))


  "PageTypeResolverService" should {

    "return the correct HTML for HelpWithBTA PageType" in {
      val testUtr = Some(SaUtr("abcdefgh"))
      val service = new PageTypeResolverService(frontendAppConfig)
      val pageType = PageType.HelpWithBTA
      val result = service.resolve(pageType)(fakeServiceInfoRequest(testUtr), message)
      val expectedContent: HtmlFormat.Appendable = views.html.general.help_with_your_bta(pageType.name, frontendAppConfig)(messages)
      result.body mustBe expectedContent.body
    }
  }
}