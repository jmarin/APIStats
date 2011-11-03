package com.apistats.lift.comet
import net.liftweb.actor.LiftActor
import net.liftweb.http.ListenerManager
import com.apistats.lift.record.APIStatsMessageDoc
import com.apistatsmodel.messages.APIStatsMessage
import com.apistats.lift.record.NumberUtils

object APIGeospatialLiftActor extends LiftActor with ListenerManager with NumberUtils {

  private var totalMessages = APIStatsMessageDoc.numberOfTotalMessages()

  private var totalGeospatialMessages = APIStatsMessageDoc.numberOfTotalGeospatialMessages()

  private var apiMessagesGeospatial: Double = calculatePercentage

  def createUpdate = apiMessagesGeospatial

  override def lowPriority = {
    case msg: APIStatsMessage => {
      totalMessages += 1
      if (msg.isGeospatial){
        totalGeospatialMessages += 1
      }
      apiMessagesGeospatial = calculatePercentage
      updateListeners()
    }
  }
  
  private def calculatePercentage = {
    if (totalGeospatialMessages > 0){
      val perc = (totalGeospatialMessages / totalMessages) * 100
      println(perc)
      formatPercentage(perc)
    }else {
      0.0
    }
  }

}