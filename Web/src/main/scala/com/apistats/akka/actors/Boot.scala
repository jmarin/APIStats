package com.apistats.akka.actors

import akka.actor._
import akka.actor.SupervisorFactory
import akka.config.Supervision._
import akka.config.Supervision.SupervisorConfig
import akka.util._
import akka.actor.Actor._

class Boot {

  val factory = SupervisorFactory(SupervisorConfig(OneForOneStrategy(List(classOf[Exception]), 3, 100),
    Supervise(Actor.actorOf[APIStatsMonitorActor], Permanent) ::
      Supervise(Actor.actorOf[APIStatsActor], Permanent) :: Nil))

  factory.newInstance.start

  remote.start(getClass().getClassLoader())
  remote.register("apistats-monitor", actorOf[APIStatsMonitorActor])
  remote.register("apistats-actor", actorOf[APIStatsActor])
}