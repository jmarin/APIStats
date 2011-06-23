package com.apistats.akka.actors

import akka.actor._
import akka.actor.Actor._
import com.apistatsmodel.messages.APIStatsMessage

class APIStatsActor extends Actor {

  def receive = {
    case msg: APIStatsMessage => {
      self reply (msg.toURL)
    }
    case _ => self reply ("Test OK")
  }
}