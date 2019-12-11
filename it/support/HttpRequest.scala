package support

import akka.util.Timeout
import play.api.libs.ws.{WSClient, WSResponse}
import play.api.test.Helpers.{await, testServerPort}

object HttpRequest {

  def get(uri: String)(implicit wsClient: WSClient, timeout: Timeout): WSResponse =
    await(
      wsClient
        .url(s"http://localhost:${testServerPort}/business-account/help$uri")
        .withFollowRedirects(false)
        .execute("GET")
    )

}
