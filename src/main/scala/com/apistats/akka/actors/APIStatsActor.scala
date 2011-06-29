package com.apistats.akka.actors

import akka.actor._
import akka.actor.Actor._
import com.apistatsmodel.messages.APIStatsMessage
import com.apistats.lift.comet._

class APIStatsActor extends Actor {

  def receive = {
    case msg: APIStatsMessage => {
      APIStatsLiftActor ! msg.toURL
      self reply (msg.toURL)
    }
    case _ => self reply ("Test OK")
  }
}