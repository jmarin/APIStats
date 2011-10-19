package com.apistats.akka.actors

import akka.actor.Actor._
import akka.actor.Actor

class APIStatsMonitorActor extends Actor {

  def receive = {
    case "test" => self reply ("Actor is working")
    case _ => self reply ("Actor is working, a different message from 'test' was sent")
  }
  
}