import sbt.Tests.{Group, SubProcess}
import sbt._

private object AppDependencies {

  import play.core.PlayVersion
  import play.sbt.PlayImport._

  val compile: Seq[ModuleID] = Seq(
    ws,
    "uk.gov.hmrc" %% "http-caching-client" % "9.3.0-play-28",
    "uk.gov.hmrc" %% "bootstrap-frontend-play-28" % "5.20.0",
    "uk.gov.hmrc" %% "play-language" % "5.0.0-play-28",
    "uk.gov.hmrc" %% "tax-year" % "1.3.0",
    "uk.gov.hmrc" %% "play-frontend-hmrc" % "2.0.0-play-28"
  )

  def testCommon(): Seq[ModuleID] = {
    val scope: String = "test,it"
    Seq(
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0"             % scope,
      "org.jsoup"              % "jsoup"               % "1.14.3"            % scope,
      "uk.gov.hmrc" %% "bootstrap-test-play-28" % "5.20.0"  % scope,
      "com.typesafe.play"      %% "play-test"          % PlayVersion.current % scope,
      "org.scalacheck"         %% "scalacheck"         % "1.15.4"            % scope,
      "org.scalatestplus" %% "mockito-3-12" % "3.2.10.0" % scope
    )
  }

  def test(): Seq[ModuleID] = {
    val scope: String = "test"
    Seq("org.mockito" % "mockito-core" % "3.10.0" % scope)
  }

  def integrationTest(): Seq[ModuleID] = {
    val scope: String = "it"
    Seq("com.github.tomakehurst" % "wiremock-jre8" % "2.27.2" % scope)
  }

  def apply(): Seq[ModuleID] =
    compile ++ testCommon() ++ test() ++ integrationTest()

}

object TestPhases {

  def oneForkedJvmPerTest(tests: Seq[TestDefinition]): Seq[Group] =
    tests map { test =>
      new Group(
        test.name,
        Seq(test),
        SubProcess(ForkOptions().withRunJVMOptions(Vector("-Dtest.name=" + test.name)))
      )
    }
}
