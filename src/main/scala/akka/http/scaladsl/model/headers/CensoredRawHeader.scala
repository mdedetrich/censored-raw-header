package akka.http
package scaladsl.model.headers

import akka.http.impl.util.{Rendering, _}
import akka.http.javadsl.{model => jm}
import akka.http.scaladsl.model.HttpHeader

/** A special type of RawHeader which allows you to censor the output when [[toString]] is called, useful
  * for headers which express Tokens which you don't want to log for security concerns
  * @param name The name of the header
  * @param value The actual value of the header
  * @param censorValue The censored value to show when doing [[toString]]
  */
final case class CensoredRawHeader(name: String, value: String, censorValue: String) extends jm.headers.RawHeader {
  def renderInRequests                     = true
  def renderInResponses                    = true
  val lowercaseName: String                = name.toRootLowerCase
  def render[R <: Rendering](r: R): r.type = r ~~ name ~~ ':' ~~ ' ' ~~ value
  private def censoredRender[R <: Rendering](r: R): r.type =
    r ~~ name ~~ ':' ~~ ' ' ~~ censorValue
  override def toString(): String = censoredRender(new StringRendering).get
}

object CensoredRawHeader {
  def unapply[H <: HttpHeader](customHeader: H): Option[(String, String)] =
    Some(customHeader.name -> customHeader.value)
}
