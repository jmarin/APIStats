//package com.apistats.stress.test
//
//import akka.actor.Actor._
//import akka.actor._
//import bootstrap.liftweb._
//import com.foursquare.rogue.Rogue._
//import com.apistatsmodel.messages.APIStatsMessage
//import com.apistats.lift.record.APIStatsMessageDoc
//import org.joda.time.DateTime
//import scala.collection.mutable.LinkedHashMap
//import org.scalatest.{ BeforeAndAfterAll, WordSpec }
//import org.junit.runner.RunWith
//import org.scalatest.junit.JUnitRunner
//
////@RunWith(classOf[JUnitRunner])
//class StressTest extends WordSpec with BeforeAndAfterAll {
//
//  val initLift = new Boot
//  
//  override def afterAll(): Unit = {
//    val messages = APIStatsMessageDoc where (_.apiName eqs "stress")
//    messages.foreach(message => {
//      message.delete_!
//    })
//
//  }
//  
//  "The application" should {
//    "be fine when load tested " in {
//      for (i <- 1 to 10000) {
//        val rnd = new scala.util.Random
//        val longitudeRange = -125 to -70
//        val latitudeRange = 24 to 47
//        val longitude = longitudeRange(rnd.nextInt(longitudeRange length))
//        val latitude = latitudeRange(rnd.nextInt(latitudeRange length))
//        //println("Coordinates: " + longitude + ", " + latitude)
//        val message = new APIStatsMessage("stress", "broadbandmap", "www.broadbandmap.gov/broadbandmap/broadband/spring2011/wireline?latitude=" + latitude + "&longitude= " + longitude + "&format=json", LinkedHashMap("geographyType" -> "block"),
//          LinkedHashMap("latitude" -> latitude.toString, "longitude" -> longitude.toString, "format" -> "json"), new DateTime(), 3, true, "", false)
//        //println(message)
//        val statsActor = remote.actorFor("apistats-actor", "localhost", 2552)
//        println("Sending message #" + i)
//        Thread.sleep(500)
//        statsActor ! message
//      }
//    }
//  }
//
//}