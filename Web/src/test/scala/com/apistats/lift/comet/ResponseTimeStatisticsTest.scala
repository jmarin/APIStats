package com.apistats.lift.comet
import org.scalatest.WordSpec
import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ResponseTimeStatisticsTest extends WordSpec with BeforeAndAfterAll with ShouldMatchers {

  val listResponseTimes = List(0L, 15L, 40L)
  val largeListResponseTimes:List[Long] = (0L to 250L).toList
  
  "Lift Actor" should {
    "calculate correct average response time " in {
      val avgTime = AvgResponseTimeLiftActor.calculateResponseTime(listResponseTimes)
      assert(avgTime === 18.333333333333332)
    }
    "calculate correct average for more than 100 messages" in {
      val avgTime = AvgResponseTimeLiftActor.calculateResponseTime(largeListResponseTimes)
      assert(avgTime === 125.0)
    }
    "calculate correct minimum response time" in {
      val minTime = MinResponseTimeLiftActor.calculateMinResponseTime(listResponseTimes)
      assert(minTime === 0L)
    }
    "calculate correct minimum for more than 100 messages" in {
      val minTime = MinResponseTimeLiftActor.calculateMinResponseTime(largeListResponseTimes)
      assert(minTime === 0L)
    }
    "calculate correct maximum response time" in {
      val maxTime = MaxResponseTimeLiftActor.calculateMaxResponseTime(listResponseTimes)
      assert(maxTime === 40L)
    }
    "calculate correct maximum for more than 100 messages" in {
      val maxTime = MaxResponseTimeLiftActor.calculateMaxResponseTime(largeListResponseTimes)
      assert(maxTime === 250L)
    }

  }

}