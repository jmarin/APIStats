package com.apistats.lift.comet

import net.liftweb.http.CometActor
import net.liftweb.http.CometListener

class FailedPercentageCometActor extends CometActor with CometListener {

  private var percentageFailed: Double = 0.0

  def registerWith = FailedPercentageLiftActor

  override def lowPriority = {
    case percentage: Double => {
      percentageFailed = percentage
      reRender()
    }
  }

  def render = "#failedPercentage *" #> <span><strong>{ percentageFailed }</strong></span>

}