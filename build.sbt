import com.typesafe.sbt.digest.Import._
import com.typesafe.sbt.uglify.Import._
import com.typesafe.sbt.web.Import._
import net.ground5hark.sbt.concat.Import._
import play.sbt.routes.RoutesKeys
import sbt.Keys._
import sbt._
import scoverage.ScoverageKeys
import uk.gov.hmrc.DefaultBuildSettings._
import uk.gov.hmrc.sbtdistributables.SbtDistributablesPlugin
import uk.gov.hmrc.sbtdistributables.SbtDistributablesPlugin._
import uk.gov.hmrc.versioning.SbtGitVersioning
import uk.gov.hmrc.versioning.SbtGitVersioning.autoImport.majorVersion

val appName = "help-and-contact-frontend"

val appDependencies: Seq[ModuleID] = AppDependencies()
val appOverrides: Set[ModuleID] = Set.empty
val plugins: Seq[Plugins] = Seq.empty
val playSettings: Seq[Setting[_]] = Seq.empty

lazy val microservice = Project(appName, file("."))
  .enablePlugins(
    Seq(
      play.sbt.PlayScala,
      SbtAutoBuildPlugin,
      SbtGitVersioning,
      SbtDistributablesPlugin,
      SbtArtifactory
    ) ++ plugins: _*
  )
  .settings(playSettings: _*)
  .settings(RoutesKeys.routesImport ++= Seq("models._"))
  .settings(
    ScoverageKeys.coverageExcludedFiles := "<empty>;Reverse.*;.*filters.*;.*handlers.*;.*components.*;.*models.*;.*repositories.*;" +
      ".*BuildInfo.*;.*javascript.*;.*FrontendAuditConnector.*;.*Routes.*;.*GuiceInjector;.*DataCacheConnector;" +
      ".*ControllerConfiguration;.*LanguageSwitchController",
    ScoverageKeys.coverageMinimum := 95.28,
    ScoverageKeys.coverageFailOnMinimum := true,
    ScoverageKeys.coverageHighlighting := true,
    parallelExecution in Test := false
  )
  .settings(scalaSettings: _*)
  .settings(publishingSettings: _*)
  .settings(defaultSettings(): _*)
  .settings(
    scalacOptions ++= Seq("-Xfatal-warnings", "-feature"),
    libraryDependencies ++= appDependencies,
    PlayKeys.playDefaultPort := 9733,
    retrieveManaged := true,
    evictionWarningOptions in update := EvictionWarningOptions.default
      .withWarnScalaVersionEviction(false),
    scalaVersion := "2.11.12"
  )
  .configs(IntegrationTest)
  .settings(inConfig(IntegrationTest)(Defaults.itSettings): _*)
  .settings(
    fork in IntegrationTest := true,
    unmanagedSourceDirectories in IntegrationTest := ((baseDirectory in IntegrationTest)(
      base => Seq(base / "it")
    )).value,
    addTestReportOption(IntegrationTest, "int-test-reports"),
    testGrouping in IntegrationTest := TestPhases.oneForkedJvmPerTest(
      (definedTests in IntegrationTest).value
    ),
    parallelExecution in IntegrationTest := false
  )
  .settings(
    resolvers ++= Seq(
      Resolver.bintrayRepo("hmrc", "releases"),
      Resolver.jcenterRepo,
      Resolver.bintrayRepo("emueller", "maven")
    )
  )
  .settings(
    // concatenate js
    Concat.groups := Seq(
      "javascripts/helpandcontactfrontend-app.js" -> group(
        Seq(
          "javascripts/show-hide-content.js",
          "javascripts/helpandcontactfrontend.js"
        )
      )
    ),
    // prevent removal of unused code which generates warning errors due to use of third-party libs
    uglifyCompressOptions := Seq("unused=false", "dead_code=false"),
    pipelineStages := Seq(digest),
    // below line required to force asset pipeline to operate in dev rather than only prod
    pipelineStages in Assets := Seq(concat, uglify),
    // only compress files generated by concat
    includeFilter in uglify := GlobFilter("helpandcontactfrontend-*.js")
  )
  .settings(majorVersion := 0)
