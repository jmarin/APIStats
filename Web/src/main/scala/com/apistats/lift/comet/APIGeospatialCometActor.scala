package com.apistats.lift.comet
import net.liftweb.http.CometActor
import net.liftweb.http.CometListener

class APIGeospatialCometActor extends CometActor with CometListener {

  private var apiMessagesGeospatial: Double = 0.0

  def registerWith = APIGeospatialLiftActor

  override def lowPriority = {
    case percentage: Double => {
      apiMessagesGeospatial = percentage
      reRender()
    }
  }

  def render = "#geospatialPercentage *" #> <span><strong>{ apiMessagesGeospatial }</strong></span>

}