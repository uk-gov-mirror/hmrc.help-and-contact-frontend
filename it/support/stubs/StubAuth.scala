package support.stubs

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.{aResponse, post, urlEqualTo}

object StubAuth {

  def mockAuthorised()(implicit wiremock: WireMockServer): Unit =
    wiremock.stubFor(post(urlEqualTo("/auth/authorise"))
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

}
