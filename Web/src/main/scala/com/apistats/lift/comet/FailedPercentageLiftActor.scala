package com.apistats.lift.comet
import net.liftweb.actor.LiftActor
import net.liftweb.http.ListenerManager
import com.apistats.lift.record.NumberUtils
import com.apistats.lift.record.APIStatsMessageDoc
import com.apistatsmodel.messages.APIStatsMessage

object FailedPercentageLiftActor extends LiftActor with ListenerManager with NumberUtils {

  private var totalMessages = APIStatsMessageDoc.numberOfTotalMessages()

  private var messagesFailed = APIStatsMessageDoc.messagesFailed()

  private var failedPercentage: Double = calculatePercentage

  def createUpdate = failedPercentage

  override def lowPriority = {
    case msg: APIStatsMessage => {
      totalMessages += 1
      if (msg.hasFailed) {
        messagesFailed += 1
      }
      failedPercentage = calculatePercentage
      updateListeners()
    }
  }

  private def calculatePercentage = {
    if (messagesFailed > 0) {
      val perc: Double = (messagesFailed.toDouble / totalMessages.toDouble) * 100
      formatPercentage(perc)
    } else {
      0.0
    }
  }

}