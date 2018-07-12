name := "censored-raw-header"
organization := "org.mdedetrich"

val currentScalaVersion = "2.12.6"

scalaVersion := currentScalaVersion
crossScalaVersions := Seq("2.11.11", currentScalaVersion)

version := "0.3.0"

val akkaVersion = "2.5.12"

val flagsFor11 = Seq(
  "-Xlint:_",
  "-Yconst-opt",
  "-Ywarn-infer-any",
  "-Yclosure-elim",
  "-Ydead-code",
  "-optimize",
  "-Xsource:2.12" // required to build case class construction
)

val flagsFor12 = Seq(
  "-Xlint:_",
  "-Ywarn-infer-any",
  "-opt-inline-from:<sources>",
  "-opt:l:method"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http"   % "10.1.3",
  "com.typesafe.akka" %% "akka-actor"  % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion
)

scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",
      "-deprecation", // warning and location for usages of deprecated APIs
      "-feature", // warning and location for usages of features that should be imported explicitly
      "-unchecked", // additional warnings where generated code depends on assumptions
      "-Xlint", // recommended additional warnings
      "-Xcheckinit", // runtime error when a val is not initialized due to trait hierarchies (instead of NPE somewhere else)
      "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver
      "-Ywarn-value-discard", // Warn when non-Unit expression results are unused
      "-Ywarn-inaccessible",
      "-Ywarn-dead-code"
)
scalacOptions ++= {
   CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, n)) if n >= 12 =>
          flagsFor12
        case Some((2, n)) if n == 11 =>
          flagsFor11
  }
}

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
