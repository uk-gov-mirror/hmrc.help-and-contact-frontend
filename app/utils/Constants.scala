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

package utils

object AffinityGroupValue {
  val INDIVIDUAL = "Individual"
  val ORGANISATION = "Organisation"
  val AGENT = "Agent"
}

object EnrolmentState {
  val ACTIVATED = "Activated"
  val NOT_YET_ACTIVATED = "NotYetActivated"
}

object CredentialRole {
  //Admin is also known as User. Admin is a platform name, User is the GG name.
  val USER = "User"
  val ASSISTANT = "Assistant"
}
