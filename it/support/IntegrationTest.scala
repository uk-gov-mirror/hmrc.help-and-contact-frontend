package support

import com.github.tomakehurst.wiremock.WireMockServer
import org.scalatest.concurrent.{Eventually, IntegrationPatience}
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, TestSuite}
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.ws.WSClient
import IntegrationTest._

trait IntegrationTest extends GuiceOneServerPerSuite
  with Injecting
  with BeforeAndAfterAll
  with BeforeAndAfterEach
  with Eventually
  with IntegrationPatience {
  this: TestSuite =>

  final implicit val wiremock: WireMockServer = new WireMockServer(wiremockPort)

  final override implicit lazy val app: Application =
    new GuiceApplicationBuilder()
      .configure(essentialConfigs ++ configOverrides)
      .build()

  final implicit def wsClient: WSClient = inject[WSClient]

  final private def essentialConfigs: Map[String, Any] =
    Set[String](
      "auth",
      "business-tax-account"
    ).map(service => (s"microservice.services.$service.port", wiremockPort)).toMap

  def configOverrides: Map[String, Any] = Map.empty

  override def beforeAll(): Unit = {
    super.beforeAll()
    wiremock.start()
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    wiremock.resetAll()
  }

  override def afterAll(): Unit = {
    super.afterAll()
    wiremock.shutdown()
  }

}

object IntegrationTest {
  val wiremockPort = 6002
}
