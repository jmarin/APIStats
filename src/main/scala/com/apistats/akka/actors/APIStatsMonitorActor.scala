package com.apistats.akka.actors

import akka.actor.Actor._
import akka.actor.Actor

class APIStatsMonitorActor extends Actor {

  def receive = {
    case "test" => self reply ("Actor is working")
  }
  
}