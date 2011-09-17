package com.apistats.lift.comet
import net.liftweb.http.CometActor
import com.apistatsmodel.messages.APIStatsMessage
import net.liftweb.http.js.JsCmds.SetHtml
import scala.xml.Text
import net.liftweb.http.CometListener

class MainMapCometActor extends CometActor with CometListener {

  private var lat:Double = 0.0
  
  private var lon:Double = 0.0
  
  private var message:APIStatsMessage = null
  
  def render = {
    "#latitude *" #> <span><b>{if (message != null) message.queryParams("latitude")}</b></span>
    "#longitude *" #> <span><b>{if (message != null) message.queryParams("longitude")}</b></span>
  }
  
  def registerWith = MainMapLiftActor

  override def lowPriority = {
    case message: APIStatsMessage => {
      println("*****APIStatsMainMapCometActor called with message type APIStatsMessage*****")
      val queryParams = message.queryParams
      val latitude = queryParams("latitude")
      val longitude = queryParams("longitude")
      lat = latitude.toDouble
      lon = longitude.toDouble
      println(longitude, latitude)
      partialUpdate(SetHtml("latitude", Text(latitude)))
      partialUpdate(SetHtml("longitude", Text(longitude)))
    }
    case latitude:Double =>{
      println("*****APIStatsMainMapCometActor called with message type double*****")
      lat = latitude
    }
    case _ => println("****Other type of message****")
  }

}