package com.apistats.akka.actors

import akka.actor.Actor._
import akka.actor._
import com.apistats.lift.comet._
import com.apistatsmodel.messages.APIStatsMessage


object APIStatsActor {
  
  def getMessageURL(msg:APIStatsMessage) = {
    msg.resourceURL
  }
}

class APIStatsActor extends Actor {

  def receive = {
    case msg: APIStatsMessage => {
      APICountLiftActor ! msg
      APIGeospatialLiftActor ! msg
      MainMapLiftActor ! msg
      FailedPercentageLiftActor ! msg
      AvgResponseTimeLiftActor ! msg
      MaxResponseTimeLiftActor ! msg
      MinResponseTimeLiftActor ! msg
    }
    case _ => self reply ("Test OK")
  }
}