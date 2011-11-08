package com.apistats.lift.comet
import net.liftweb.http.CometActor
import net.liftweb.http.CometListener

class MinResponseTimeCometActor extends CometActor with CometListener{

  private var minResponseTime: Double = 0.0
  
  def registerWith = MinResponseTimeLiftActor
  
  override def lowPriority = {
    case responseTime: Double => {
      minResponseTime = responseTime
      reRender()
    }
  }

  def render = "#minResponseTime" #> <span><strong>{ minResponseTime }</strong></span>

}