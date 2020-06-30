import sbt.Tests.{Group, SubProcess}
import sbt._

private object AppDependencies {

  import play.core.PlayVersion
  import play.sbt.PlayImport._

  private val govukTemplateVersion = "5.55.0-play-26"
  private val playUiVersion = "8.11.0-play-26"
  private val scalaTestPlusPlayVersion = "3.1.3"
  private val pegdownVersion = "1.6.0"
  private val mockitoAllVersion = "1.10.19"
  private val httpCachingClientVersion = "9.1.0-play-26"
  private val playReactivemongoVersion = "7.29.0-play-26"
  private val playConditionalFormMappingVersion = "1.2.0-play-26"
  private val playLanguageVersion = "4.3.0-play-26"
  private val bootstrapVersion = "1.10.0"
  private val scalacheckVersion = "1.14.3"
  private val playPartialsVersion = "6.11.0-play-26"
  private val domainVersion = "5.9.0-play-26"
  private val wiremockVersion = "2.26.3"

  val compile: Seq[ModuleID] = Seq(
    ws,
    "uk.gov.hmrc" %% "simple-reactivemongo" % playReactivemongoVersion,
    "uk.gov.hmrc" %% "govuk-template" % govukTemplateVersion,
    "uk.gov.hmrc" %% "play-ui" % playUiVersion,
    "uk.gov.hmrc" %% "http-caching-client" % httpCachingClientVersion,
    "uk.gov.hmrc" %% "play-conditional-form-mapping" % playConditionalFormMappingVersion,
    "uk.gov.hmrc" %% "bootstrap-play-26" % bootstrapVersion,
    "uk.gov.hmrc" %% "play-language" % playLanguageVersion,
    "uk.gov.hmrc" %% "play-partials" % playPartialsVersion,
    "uk.gov.hmrc" %% "domain" % domainVersion
  )

  def testCommon(): Seq[ModuleID] = {
    val scope: String = "test,it"
    Seq(
      "org.scalatestplus.play" %% "scalatestplus-play" % scalaTestPlusPlayVersion % scope,
      "org.pegdown" % "pegdown" % pegdownVersion % scope,
      "org.jsoup" % "jsoup" % "1.13.1" % scope,
      "com.typesafe.play" %% "play-test" % PlayVersion.current % scope,
      "org.scalacheck" %% "scalacheck" % scalacheckVersion % scope
    )
  }

  def test(): Seq[ModuleID] = {
    val scope: String = "test"
    Seq("org.mockito" % "mockito-all" % mockitoAllVersion % scope)
  }

  def integrationTest(): Seq[ModuleID] = {
    val scope: String = "it"
    Seq("com.github.tomakehurst" % "wiremock-jre8" % wiremockVersion % scope)
  }

  def apply(): Seq[ModuleID] =
    compile ++ testCommon() ++ test() ++ integrationTest()

}

object TestPhases {
  def oneForkedJvmPerTest(tests: Seq[TestDefinition]) =
    tests map { test =>
      new Group(
        test.name,
        Seq(test),
        SubProcess(ForkOptions().withRunJVMOptions(Vector("-Dtest.name=" + test.name)))
      )
    }
}
