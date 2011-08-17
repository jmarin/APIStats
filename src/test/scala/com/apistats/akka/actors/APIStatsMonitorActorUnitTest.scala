package com.apistats.akka.actors

import akka.testkit.TestActorRef
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.WordSpec
import akka.testkit.TestKit
import akka.util.duration._
import akka.actor.Actor._
import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.ShouldMatchers

@RunWith(classOf[JUnitRunner])
class APIStatsMonitorActorUnitTest extends WordSpec with BeforeAndAfterAll with ShouldMatchers with TestKit {

  val statsMonitorRef = actorOf(new APIStatsMonitorActor).start
  
  override protected def afterAll(): Unit = {
    stopTestActor
    statsMonitorRef.stop
  }
  
  "An APIStatsMonitorActor" should {
    "respond that it's working when a 'test' message is sent " in {
      within(500 millis){
        statsMonitorRef ! "test"
        expectMsg("Actor is working")
      }
    }
    "respond that it's working and acknowledge that random message was sent " in {
      within(500 millis){
        statsMonitorRef ! "xxx"
        expectMsg("Actor is working, a different message from 'test' was sent")
      }
    }
  }

}