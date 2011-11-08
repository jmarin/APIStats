package com.apistats.lift.comet
import net.liftweb.http.CometActor
import net.liftweb.http.CometListener

class AvgResponseTimeCometActor extends CometActor with CometListener {

  private var avgResponseTime: Double = 0.0
  
  def registerWith = AvgResponseTimeLiftActor
  
  override def lowPriority = {
    case responseTime: Double => {
      avgResponseTime = responseTime
      reRender()
    }
  }

  def render = "#avgResponseTime" #> <span><strong>{ avgResponseTime }</strong></span>

}