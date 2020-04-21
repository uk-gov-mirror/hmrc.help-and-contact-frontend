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

package controllers.actions

import com.google.inject.ImplementedBy
import javax.inject.{Inject, Singleton}
import config.HelpAndContactHeaderCarrierForPartialsConverter
import connectors.ServiceInfoPartialConnector
import models.requests.{AuthenticatedRequest, ServiceInfoRequest}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ServiceInfoActionImpl @Inject()(
    serviceInfoPartialConnector: ServiceInfoPartialConnector,
    helpAndContactHeaderCarrierForPartialsConverter: HelpAndContactHeaderCarrierForPartialsConverter
)(
    implicit protected val executionContext: ExecutionContext
) extends ServiceInfoAction {

  import helpAndContactHeaderCarrierForPartialsConverter._

  override protected def transform[A](request: AuthenticatedRequest[A]): Future[ServiceInfoRequest[A]] = {
    implicit val r: Request[A] = request
    serviceInfoPartialConnector.getServiceInfoPartial().map { serviceInfoContent =>
      ServiceInfoRequest(request, serviceInfoContent)
    }
  }

}

@ImplementedBy(classOf[ServiceInfoActionImpl])
trait ServiceInfoAction extends ActionTransformer[AuthenticatedRequest, ServiceInfoRequest]
