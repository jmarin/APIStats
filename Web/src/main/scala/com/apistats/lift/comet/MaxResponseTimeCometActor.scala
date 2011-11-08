package com.apistats.lift.comet
import net.liftweb.http.CometActor
import net.liftweb.http.CometListener

class MaxResponseTimeCometActor extends CometActor with CometListener {

  private var maxResponseTime: Double = 0.0
  
  def registerWith = MaxResponseTimeLiftActor
  
  override def lowPriority = {
    case responseTime: Double => {
      maxResponseTime = responseTime
      reRender()
    }
  }

  def render = "#maxResponseTime" #> <span><strong>{ maxResponseTime }</strong></span>

  
}