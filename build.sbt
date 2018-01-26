name := "censored-raw-header"
organization := "org.mdedetrich"

val currentScalaVersion = "2.12.4"

scalaVersion := currentScalaVersion
crossScalaVersions := Seq("2.11.11", currentScalaVersion)

version := "0.2.0"

val akkaVersion = "2.5.9"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http"   % "10.0.11",
  "com.typesafe.akka" %% "akka-actor"  % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion
)

homepage := Some(url("https://github.com/mdedetrich/censored-raw-header"))

scmInfo := Some(
  ScmInfo(url("https://github.com/mdedetrich/censored-raw-header"),
          "git@github.com:mdedetrich/censored-raw-header.git"))

developers := List(
  Developer("mdedetrich",
            "Matthew de Detrich",
            "mdedetrich@gmail.com",
            url("https://github.com/mdedetrich"))
)

licenses += ("BSD 3 Clause", url(
  "https://opensource.org/licenses/BSD-3-Clause"))

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
