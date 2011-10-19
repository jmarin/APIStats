package com.apistats.model.messages
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.WordSpec
import com.apistatsmodel.messages.APIStatsMessage
import scala.collection.mutable.LinkedHashMap
import org.joda.time.DateTime
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class APIStatsMessageTest extends WordSpec with ShouldMatchers {

  "An APIStatsMessage" should {
    val message = new APIStatsMessage("Test", "broadbandmap", "www.broadbandmap.gov", LinkedHashMap("geographyType" -> "block"),
      LinkedHashMap("latitude" -> "42.456", "longitude" -> "-74.987", "format" -> "json"), new DateTime(), 23, true, "", false)
    "Produce the right URL" in {
      assert(message.resourceURL === "www.broadbandmap.gov")
    }
  }
}