package com.apistats.lift.comet
import net.liftweb.http.CometActor
import com.apistatsmodel.messages.APIStatsMessage
import net.liftweb.http.js.JsCmds.SetHtml
import scala.xml.Text
import net.liftweb.http.CometListener
import net.liftweb.http.js.JsCmds.SetValById
import net.liftweb.http.js.JsCmd
import net.liftweb.json._
import net.liftweb.json.Serialization.{ read, write }

class MainMapCometActor extends CometActor with CometListener {

  private var message: APIStatsMessage = null

  def render = {
    //"#latitude *" #> <span><b>{if (message != null) message.queryParams("latitude")}</b></span>
    //"#longitude *" #> <span><b>{if (message != null) message.queryParams("longitude")}</b></span>
    <span></span>
  }

  def registerWith = MainMapLiftActor

  override def lowPriority = {
    case message: APIStatsMessage => {
      if (message.isGeospatial) {
        val queryParams = message.queryParams
        val latitude = queryParams("latitude")
        val longitude = queryParams("longitude")
        partialUpdate(callEventHandler(message))
      }
    }
    case _ => println("****Other type of message****")
  }

  def callEventHandler(message: APIStatsMessage): JsCmd = {
    new JsCmd {
      implicit val formats = Serialization.formats(NoTypeHints)
      val apiName = message.apiName
      val latitude = message.queryParams("latitude")
      val longitude = message.queryParams("longitude")
      val responseTime = message.reponseTime
      val msg = new JsonMessage(apiName, responseTime.toString, latitude, longitude)
      val jsonMessage = net.liftweb.json.Serialization.write(msg)
      def toJsCmd = "updateMap('" + jsonMessage + "')"
    }
  }

}