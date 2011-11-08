package com.apistats.lift.comet
import net.liftweb.actor.LiftActor
import net.liftweb.http.ListenerManager
import com.apistats.lift.record.NumberUtils
import com.apistats.lift.record.APIStatsMessageDoc
import com.apistatsmodel.messages.APIStatsMessage

object AvgResponseTimeLiftActor extends LiftActor with ListenerManager with NumberUtils {

  private var avgResponseTime: Double = 0.0

  private var responseTimes: List[Long] = Nil

  def createUpdate = avgResponseTime

  override def lowPriority = {
    case msg: APIStatsMessage => {
      if (responseTimes.length > 100){
        responseTimes = responseTimes.tail
      }
      responseTimes = msg.reponseTime :: responseTimes
      avgResponseTime = calculateResponseTime(responseTimes)
      updateListeners()
    }
  }

  def calculateResponseTime(responseTimes: List[Long]):Double = {
    if (responseTimes.length > 0) {
      val avgResponseTime:Double = responseTimes.foldLeft(0L)(_ + _) / responseTimes.length.toDouble
      formatDouble(avgResponseTime)
    } else {
      0.0
    }
  }

}