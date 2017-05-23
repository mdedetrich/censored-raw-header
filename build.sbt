name := "censored-raw-header"
organization := "org.mdedetrich"

val currentScalaVersion = "2.12.2"

scalaVersion := currentScalaVersion
crossScalaVersions := Seq("2.11.11", currentScalaVersion)

version := "0.1.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.0.6"
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

pomIncludeRepository := (_ => false)
