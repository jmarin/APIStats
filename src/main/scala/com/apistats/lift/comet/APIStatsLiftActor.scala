package com.apistats.lift.comet
import net.liftweb.actor.LiftActor
import net.liftweb.http.ListenerManager
import com.apistatsmodel.messages.APIStatsMessage
import com.apistats.lift.record.APIStatsMessageDoc

object APIStatsLiftActor extends LiftActor with ListenerManager {

  private var apiMessagesCount: Long = 0

  def createUpdate = apiMessagesCount

  override def lowPriority = {
    case msg: APIStatsMessage => {
      APIStatsMessageDoc.saveMessage(msg)
      apiMessagesCount = APIStatsMessageDoc.numberOfTotalMessages
      updateListeners()
    }
  }

}