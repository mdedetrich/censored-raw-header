## Censored Raw Header

Censored raw header is a simple implementation of `RawHeader` which allows you to provide
a custom `.toString` method. This is useful when it comes to censoring log output of headers.

## Usage

Put this into your `build.sbt`

```scala
libraryDependencies ++= Seq(
  "org.mdedetrich" %% "censored-raw-header" % "0.1.0"
)
```

Then simply created your own custom `CensoredRawHeader`, i.e. if you want to store an OAuth2
token with implicit flow you might do the following

```scala
import akka.http.scaladsl.model.headers.CensoredRawHeader

val token: String = ??? // Some Token  

CensoredRawHeader("Authorization",
                        s"Bearer $token",
                        s"Bearer ${token.take(3)}...${token.takeRight(3)}")
```

This means that when someone accesses the raw value from within the `CensoredRawHeader` they will
get the actual value, however if they call `.toString` on the `CensoredRawHeader` (or any data
structure containing the `CensoredRawHeader`) it will print the first 3 characters of the token, then `...` and then
the last 3 values of the token
