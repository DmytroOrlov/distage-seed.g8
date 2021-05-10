ThisBuild / organization := "com.example"
ThisBuild / version := "1.0-SNAPSHOT"
ThisBuild / scalacOptions ++= Seq(
  "-Ymacro-annotations",
)

val V = new {
  val zioInteropCats = "2.4.1.0"
  val zio = "1.0.8"
  val zioMacros = "1.0.8+7-a1116389-SNAPSHOT"
  val distage = "1.0.6"
  val tapir = "0.17.19"
  val sttp = "3.3.1"
  val elastic4s = "7.12.1"

  val scalacheck = "1.15.4"

  val betterMonadicFor = "0.3.1"
  val kindProjector = "0.11.3"

  val silencer = "1.7.3"
}

val Deps = new {
  val zioInteropCats = "dev.zio" %% "zio-interop-cats" % V.zioInteropCats
  val zio = "dev.zio" %% "zio" % V.zio
  val zioMacros = "dev.zio" %% "zio-macros" % V.zio
  val distageFramework = "io.7mind.izumi" %% "distage-framework" % V.distage
  val distageFrameworkDocker = "io.7mind.izumi" %% "distage-framework-docker" % V.distage
  val distageTestkitScalatest = "io.7mind.izumi" %% "distage-testkit-scalatest" % V.distage
  val logstageAdapterSlf4J = "io.7mind.izumi" %% "logstage-adapter-slf4j" % V.distage

  val tapirJsonCirce = "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % V.tapir
  val tapirHttp4sServer = "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % V.tapir
  val tapirOpenapiCirceYaml = "com.softwaremill.sttp.tapir" %% "tapir-openapi-circe-yaml" % V.tapir
  val tapirOpenapiDocs = "com.softwaremill.sttp.tapir" %% "tapir-openapi-docs" % V.tapir
  val tapirSwaggerUiHttp4s = "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-http4s" % V.tapir

  val sttpClientCirce = "com.softwaremill.sttp.client3" %% "circe" % V.sttp
  val asyncHttpClientBackendZio = "com.softwaremill.sttp.client3" %% "async-http-client-backend-zio" % V.sttp

  val elastic4sClientSttp = "com.sksamuel.elastic4s" %% "elastic4s-client-sttp" % V.elastic4s
  val elastic4sEffectZio = "com.sksamuel.elastic4s" %% "elastic4s-effect-zio" % V.elastic4s

  val scalacheck = "org.scalacheck" %% "scalacheck" % V.scalacheck

  val betterMonadicFor = "com.olegpy" %% "better-monadic-for" % V.betterMonadicFor
  val kindProjector = "org.typelevel" %% "kind-projector" % V.kindProjector cross CrossVersion.full
}

val commonSettings = Seq(
  scalaVersion := "2.13.5",
)

lazy val `$name$` = (project in file("."))
  .enablePlugins(BuildInfoPlugin)
  .settings(commonSettings)
  .settings(
    resolvers += "sonatype" at "https://oss.sonatype.org/content/repositories/snapshots",
    libraryDependencies ++= Seq(
      Deps.zio,
      Deps.zioMacros,
      Deps.zioInteropCats,
      Deps.logstageAdapterSlf4J,
      Deps.distageFramework,

      Deps.distageFrameworkDocker % Test,
      Deps.distageTestkitScalatest % Test,
      Deps.scalacheck % Test,

      Deps.sttpClientCirce,
      Deps.asyncHttpClientBackendZio,

      Deps.tapirJsonCirce,
      Deps.tapirHttp4sServer,
      Deps.tapirOpenapiCirceYaml,
      Deps.tapirOpenapiDocs,
      Deps.tapirSwaggerUiHttp4s,

      Deps.elastic4sClientSttp,
      Deps.elastic4sEffectZio,
    ),
    addCompilerPlugin(Deps.betterMonadicFor),
    addCompilerPlugin(Deps.kindProjector),
  )
