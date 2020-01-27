import sbt.Tests.{Group, SubProcess}
import sbt._


private object AppDependencies {

  import play.core.PlayVersion
  import play.sbt.PlayImport._

  private val playHealthVersion = "3.14.0-play-25"
  private val logbackJsonLoggerVersion = "4.6.0"
  private val govukTemplateVersion = "5.28.0-play-25"
  private val playUiVersion = "8.7.0-play-25"
  private val scalaTestVersion = "3.0.5"
  private val scalaTestPlusPlayVersion = "2.0.1"
  private val pegdownVersion = "1.6.0"
  private val mockitoAllVersion = "1.10.19"
  private val httpCachingClientVersion = "9.0.0-play-25"
  private val playReactivemongoVersion = "6.7.0"
  private val playConditionalFormMappingVersion = "1.2.0-play-25"
  private val playLanguageVersion = "3.4.0"
  private val bootstrapVersion = "5.1.0"
  private val scalacheckVersion = "1.13.4"
  private val playPartialsVersion = "6.9.0-play-25"
  private val domainVersion = "5.6.0-play-25"
  private val wiremockVersion = "2.25.1"

  val compile: Seq[ModuleID] = Seq(
    ws,
    "uk.gov.hmrc" %% "play-reactivemongo" % playReactivemongoVersion,
    "uk.gov.hmrc" %% "logback-json-logger" % logbackJsonLoggerVersion,
    "uk.gov.hmrc" %% "govuk-template" % govukTemplateVersion,
    "uk.gov.hmrc" %% "play-health" % playHealthVersion,
    "uk.gov.hmrc" %% "play-ui" % playUiVersion,
    "uk.gov.hmrc" %% "http-caching-client" % httpCachingClientVersion,
    "uk.gov.hmrc" %% "play-conditional-form-mapping" % playConditionalFormMappingVersion,
    "uk.gov.hmrc" %% "bootstrap-play-25" % bootstrapVersion,
    "uk.gov.hmrc" %% "play-language" % playLanguageVersion,
    "uk.gov.hmrc" %% "play-partials" % playPartialsVersion,
    "uk.gov.hmrc" %% "domain" % domainVersion
  )

  def testCommon(): Seq[ModuleID] = {
    val scope: String = "test,it"
    Seq(
      "org.scalatest" %% "scalatest" % scalaTestVersion % scope,
      "org.scalatestplus.play" %% "scalatestplus-play" % scalaTestPlusPlayVersion % scope,
      "org.pegdown" % "pegdown" % pegdownVersion % scope,
      "org.jsoup" % "jsoup" % "1.10.3" % scope,
      "com.typesafe.play" %% "play-test" % PlayVersion.current % scope,
      "org.scalacheck" %% "scalacheck" % scalacheckVersion % scope
    )
  }

  def test(): Seq[ModuleID] = {
    val scope: String = "test"
    Seq(
      "org.mockito" % "mockito-all" % mockitoAllVersion % scope
    )
  }

  def integrationTest(): Seq[ModuleID] = {
    val scope: String = "it"
    Seq("com.github.tomakehurst" % "wiremock-jre8" % wiremockVersion % scope)
  }

  def apply(): Seq[ModuleID] = compile ++ testCommon() ++ test() ++ integrationTest()

}


object TestPhases {
  def oneForkedJvmPerTest(tests: Seq[TestDefinition]): Seq[Group] =
    tests map {
      test => new Group(test.name, Seq(test), SubProcess(ForkOptions(runJVMOptions = Seq("-Dtest.name=" + test.name))))
    }
}
