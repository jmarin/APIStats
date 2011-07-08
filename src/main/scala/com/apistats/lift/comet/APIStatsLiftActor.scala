package com.apistats.lift.comet
import net.liftweb.actor.LiftActor
import net.liftweb.http.ListenerManager; import com.apistatsmodel.messages.APIStatsMessage

object APIStatsLiftActor extends LiftActor with ListenerManager {

  private var msgs = Vector("Welcome")

  def createUpdate = msgs

  override def lowPriority = {
    case msg: APIStatsMessage => {
            
      msgs :+= msg.toURL; updateListeners()
    }
  }

}