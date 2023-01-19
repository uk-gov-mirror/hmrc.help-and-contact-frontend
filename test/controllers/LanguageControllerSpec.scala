/*
 * Copyright 2023 HM Revenue & Customs
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

import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.mvc.{Cookies, Result}
import play.api.test.FakeRequest
import play.api.test.Helpers.{contentAsString, cookies, defaultAwaitTimeout}

import scala.concurrent.Future

class LanguageControllerSpec extends ControllerSpecBase with MockitoSugar with GuiceOneAppPerSuite {

  override def fakeApplication(): Application =
    new GuiceApplicationBuilder()
      .build()

  val controller = app.injector.instanceOf[LanguageController]
  "Validating english/welsh switch" should {
    "display english when 'english' selected" in {
      val result: Future[Result] =
        controller.switchToEnglish().apply(FakeRequest().withHeaders("Referer" -> "/paperless/choose"))
      val bodyText: String = contentAsString(result)
      val cook: Cookies = cookies(result)
      cook.get("PLAY_LANG").get.value mustBe "en"
    }

    "display welsh when 'welsh' selected" in {
      val result: Future[Result] =
        controller.switchToWelsh().apply(FakeRequest().withHeaders("Referer" -> "/paperless/choose"))
      val bodyText: String = contentAsString(result)
      val cook: Cookies = cookies(result)
      cook.get("PLAY_LANG").get.value mustBe "cy"
    }

    "display english when 'english' selected and no referrer is present" in {
      val result: Future[Result] =
        controller.switchToEnglish().apply(FakeRequest())
      val cook: Cookies = cookies(result)
      cook.get("PLAY_LANG").get.value mustBe "en"
    }

    "display welsh when 'welsh' selected and no referrer is present" in {
      val result: Future[Result] =
        controller.switchToWelsh().apply(FakeRequest())
      val cook: Cookies = cookies(result)
      cook.get("PLAY_LANG").get.value mustBe "cy"
    }
  }
}
