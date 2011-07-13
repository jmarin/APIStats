package com.apistats.lift.record

import scala.collection.JavaConversions._
import com.mongodb.QueryBuilder
import java.util.Locale
import org.joda.time.DateTime
import scala.collection.mutable.LinkedHashMap
import com.apistatsmodel.messages.APIStatsMessage
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.BeforeAndAfterAll
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import bootstrap.liftweb._
import org.scalatest.WordSpec
import net.liftweb.json._
import net.liftweb.json.JsonParser

@RunWith(classOf[JUnitRunner])
class APIStatsMessageDocTest extends WordSpec with BeforeAndAfterAll with ShouldMatchers {

  val initLift = new Boot

  override def beforeAll(): Unit = {
    initLift.boot
  }

  override def afterAll(): Unit = {

  }

  "An new APIStatsMessageDoc" should {
    "be saved in MongoDB" in {
      val message = new APIStatsMessage("Test", "www.broadbandmap.gov", "broadbandmap", "census", LinkedHashMap("geographyType" -> "block"),
        LinkedHashMap("latitude" -> "42.456", "longitude" -> "-74.987", "format" -> "json"), new DateTime(), 23, true)
      APIStatsMessageDoc.saveMessage(message)

    }

    "and be retrieved from MongoDB" in {
      val query = QueryBuilder.start("apiName").is("Test").get
      val messageDocIterator = APIStatsMessageDoc.find(query)
      val messageDoc = messageDocIterator.elements.next
      assert(messageDoc.apiName.toString === "Test")
      assert(messageDoc.baseURL.toString === "www.broadbandmap.gov")
      assert(messageDoc.contextPath.toString === "broadbandmap")
      assert(messageDoc.apiResource.toString === "census")
      assert(messageDoc.responseTime.toString === "23")

      val queryParams = messageDoc.queryParams._1
      val origQueryParams = LinkedHashMap[String, String]()
      queryParams.foreach(x => {
        origQueryParams ++= LinkedHashMap(x.key.toString -> x.value.toString)
      })

      //origQueryParams.foreach(x => println(x._1 + "=" + x._2))

      /*val queryParams = messageDoc.queryParams.asDBObject
      val queryParamsList = queryParams.toMap().toList
      val queryParamsListReversed = queryParamsList.reverse
      val queryParamsLinkedHashMap = queryParamsListReversed.toMap
      
      queryParamsLinkedHashMap.foreach(x => println(x))*/

      println("")
      println("")
      println("")
      //println(queryParams.companion.toString)
      //queryParams.foreach(println(_))

    }
    "and be deleted from MongoDB" in {
      val message = new APIStatsMessage("Test", "www.broadbandmap.gov", "broadbandmap", "census", LinkedHashMap("geographyType" -> "block"),
        LinkedHashMap("latitude" -> "42.456", "longitude" -> "-74.987", "format" -> "json"), new DateTime(), 23, true)
            
      val query = QueryBuilder.start("apiName").is("Test").get
      val messageDocIterator = APIStatsMessageDoc.find(query)
      val messageDoc = messageDocIterator.elements.next
      assert(messageDoc.delete_! === true)

    }
  }
}