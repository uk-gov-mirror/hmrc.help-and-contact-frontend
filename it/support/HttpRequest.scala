package support

import org.apache.pekko.util.Timeout
import play.api.libs.ws.{WSClient, WSResponse}
import play.api.test.Helpers.await

object HttpRequest {

  def get(uri: String, port: Int)(implicit wsClient: WSClient, timeout: Timeout): WSResponse =
    await(
      wsClient
        .url(s"http://localhost:${port}/business-account/help$uri")
        .withFollowRedirects(false)
        .execute("GET")
    )

}
