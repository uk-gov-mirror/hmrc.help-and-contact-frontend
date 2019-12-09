package support

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import play.api.libs.ws.WSResponse

object JsoupHelper {

  implicit class WSResponseUtil(val response: WSResponse) extends AnyVal {
    def bodyAsDom: Document = Jsoup.parse(response.body)
  }

}
