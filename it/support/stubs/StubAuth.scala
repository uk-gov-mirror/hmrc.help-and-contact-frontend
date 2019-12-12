package support.stubs

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.{aResponse, post, urlEqualTo}
import play.api.test.Helpers.UNAUTHORIZED
import uk.gov.hmrc.auth.core.AuthenticateHeaderParser

object StubAuth {

  def mockAuthorised()(implicit wiremock: WireMockServer): Unit =
    wiremock.stubFor(
      post(urlEqualTo("/auth/authorise"))
        .willReturn(aResponse()
          .withHeader("Content-Type", "application/json")
          .withBody(
            """
              |{
              | "allEnrolments" : [],
              | "email":"test@test.com"
              |}
              |""".stripMargin
          ))
    )

  def mockUnauthorised()(implicit wiremock: WireMockServer): Unit =
    wiremock.stubFor(
      post(urlEqualTo("/auth/authorise"))
        .willReturn(aResponse()
          .withStatus(UNAUTHORIZED)
          .withHeader(AuthenticateHeaderParser.WWW_AUTHENTICATE, """MDTP detail="MissingBearerToken"""")
        )
    )

}
