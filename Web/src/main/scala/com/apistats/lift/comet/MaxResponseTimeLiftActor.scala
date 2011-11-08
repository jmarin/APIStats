package com.apistats.lift.comet
import net.liftweb.actor.LiftActor
import net.liftweb.http.ListenerManager
import com.apistats.lift.record.NumberUtils
import com.apistats.lift.record.APIStatsMessageDoc
import com.apistatsmodel.messages.APIStatsMessage

object MaxResponseTimeLiftActor extends LiftActor with ListenerManager with NumberUtils {

  private var maxResponseTime: Double = 0.0

  private var responseTimes: List[Long] = Nil

  def createUpdate = maxResponseTime

  override def lowPriority = {
    case msg: APIStatsMessage => {
      if (responseTimes.length > 100) {
        responseTimes = responseTimes.tail
      }
      responseTimes = msg.reponseTime :: responseTimes
      maxResponseTime = calculateMaxResponseTime(responseTimes)
      updateListeners()
    }
  }

  def calculateMaxResponseTime(responseTimes: List[Long]) = {
    if (responseTimes.length > 0) {
      val maxResponseTime = responseTimes.reduceRight(_ max _)
      maxResponseTime
    } else {
      0.0
    }
  }
  
}