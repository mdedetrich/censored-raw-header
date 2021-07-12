name                     := "censored-raw-header"
ThisBuild / organization := "org.mdedetrich"

val currentScalaVersion = "2.12.14"

ThisBuild / scalaVersion       := currentScalaVersion
ThisBuild / crossScalaVersions := Seq(currentScalaVersion, "2.13.6")

val akkaVersion = "2.6.15"

val flagsFor12 = Seq(
  "-Xlint:_",
  "-Ywarn-infer-any",
  "-opt-inline-from:<sources>",
  "-opt:l:method"
)

val flagsFor13 = Seq(
  "-Xlint:_",
  "-opt-inline-from:<sources>"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http"   % "10.2.4",
  "com.typesafe.akka" %% "akka-actor"  % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion
)

ThisBuild / scalacOptions ++= {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, n)) if n == 13 =>
      flagsFor13
    case Some((2, n)) if n == 12 =>
      flagsFor12
  }
}

ThisBuild / homepage := Some(url("https://github.com/mdedetrich/censored-raw-header"))

ThisBuild / scmInfo := Some(
  ScmInfo(url("https://github.com/mdedetrich/censored-raw-header"), "git@github.com:mdedetrich/censored-raw-header.git")
)

ThisBuild / developers := List(
  Developer("mdedetrich", "Matthew de Detrich", "mdedetrich@gmail.com", url("https://github.com/mdedetrich"))
)

ThisBuild / licenses += ("BSD 3 Clause", url("https://opensource.org/licenses/BSD-3-Clause"))

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

Test / publishArtifact := false

pomIncludeRepository := (_ => false)
