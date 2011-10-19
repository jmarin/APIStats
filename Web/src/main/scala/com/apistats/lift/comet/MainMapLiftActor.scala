package com.apistats.lift.comet
import net.liftweb.http.ListenerManager
import net.liftweb.actor.LiftActor
import com.apistatsmodel.messages.APIStatsMessage

object MainMapLiftActor extends LiftActor with ListenerManager {
  
  private var message:APIStatsMessage = null
  
  def createUpdate = message
  
  override def lowPriority = {
    case msg:APIStatsMessage => {
      message = msg
      updateListeners()
    }
    case _ => println("Other type of message received at " + this.toString())
  }
  
}