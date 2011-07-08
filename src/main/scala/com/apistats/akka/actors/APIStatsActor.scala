package com.apistats.akka.actors

import akka.actor._
import akka.actor.Actor._
import com.apistatsmodel.messages.APIStatsMessage
import com.apistats.lift.comet._


object APIStatsActor {
  
  def getMessageURL(msg:APIStatsMessage) = {
    msg.toURL
  }
}


class APIStatsActor extends Actor {

  def receive = {
    case msg: APIStatsMessage => {
      APIStatsLiftActor ! msg
    }
    case _ => self reply ("Test OK")
  }
}