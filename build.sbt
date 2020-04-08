name := "censored-raw-header"
organization := "org.mdedetrich"

val currentScalaVersion = "2.12.11"

scalaVersion := currentScalaVersion
crossScalaVersions := Seq(currentScalaVersion, "2.13.1")

version := "0.5.0"

val akkaVersion = "2.6.3"

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
  "com.typesafe.akka" %% "akka-http"   % "10.1.11",
  "com.typesafe.akka" %% "akka-actor"  % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion
)

scalacOptions ++= {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, n)) if n == 13 =>
      flagsFor13
    case Some((2, n)) if n == 12 =>
      flagsFor12
  }
}

homepage := Some(url("https://github.com/mdedetrich/censored-raw-header"))

scmInfo := Some(
  ScmInfo(url("https://github.com/mdedetrich/censored-raw-header"),
          "git@github.com:mdedetrich/censored-raw-header.git"))

developers := List(
  Developer("mdedetrich", "Matthew de Detrich", "mdedetrich@gmail.com", url("https://github.com/mdedetrich"))
)

licenses += ("BSD 3 Clause", url("https://opensource.org/licenses/BSD-3-Clause"))

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := (_ => false)
