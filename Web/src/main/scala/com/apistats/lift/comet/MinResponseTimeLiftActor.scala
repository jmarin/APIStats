package com.apistats.lift.comet
import net.liftweb.actor.LiftActor
import net.liftweb.actor.LiftActor
import net.liftweb.http.ListenerManager
import com.apistats.lift.record.NumberUtils
import com.apistats.lift.record.APIStatsMessageDoc
import com.apistatsmodel.messages.APIStatsMessage

object MinResponseTimeLiftActor extends LiftActor with ListenerManager with NumberUtils {

  private var minResponseTime: Double = 0.0

  private var responseTimes: List[Long] = Nil

  def createUpdate = minResponseTime

  override def lowPriority = {
    case msg: APIStatsMessage => {
      if (responseTimes.length > 100) {
        responseTimes = responseTimes.tail
      }
      responseTimes = msg.reponseTime :: responseTimes
      minResponseTime = calculateMinResponseTime(responseTimes)
      updateListeners()
    }
  }

  def calculateMinResponseTime(responseTimes: List[Long]) = {
    if (responseTimes.length > 0) {
      val minResponseTime = responseTimes.reduceRight(_ min _)
      minResponseTime
    } else {
      0.0
    }
  }

}