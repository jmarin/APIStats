package com.apistats.lift.record

import bootstrap.liftweb._
import com.apistatsmodel.messages.APIStatsMessage
import com.foursquare.rogue.Rogue._
import com.mongodb.QueryBuilder
import org.joda.time.DateTime
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{ BeforeAndAfterAll, WordSpec }
import scala.collection.JavaConversions._
import scala.collection.mutable.LinkedHashMap

@RunWith( classOf[JUnitRunner] )
class APIStatsMessageDocTest extends WordSpec with BeforeAndAfterAll with ShouldMatchers {

  val initLift = new Boot

  override def beforeAll() : Unit = {
    initLift.boot
    val message1 = new APIStatsMessage( "foo", "test1", "", LinkedHashMap( "geographyType" -> "block" ), LinkedHashMap( "latitude" -> "42.456", "longitude" -> "-74.987", "format" -> "json" ), new DateTime, 0, false, "", false )
    val message2 = new APIStatsMessage( "foo", "test2", "", LinkedHashMap( "geographyType" -> "block" ), LinkedHashMap( "latitude" -> "42.456", "longitude" -> "-74.987", "format" -> "json" ), new DateTime, 0, false, "", false )
    APIStatsMessageDoc.saveMessage( message1 )
    APIStatsMessageDoc.saveMessage( message2 )
  }

  override def afterAll() : Unit = {
    val messages = APIStatsMessageDoc where ( _.apiName eqs "foo" )
    messages.foreach( message => {
      message.delete_!
    } )

  }

  "An new APIStatsMessageDoc" should {
    "be saved in MongoDB" in {
      val message = new APIStatsMessage( "Test", "broadbandmap", "www.broadbandmap.gov", LinkedHashMap( "geographyType" -> "block" ),
        LinkedHashMap( "latitude" -> "42.456", "longitude" -> "-74.987", "format" -> "json" ), new DateTime(), 23, true, "", false )
      APIStatsMessageDoc.saveMessage( message )
    }

    "be retrieved from MongoDB" in {
      val query = QueryBuilder.start( "apiName" ).is( "Test" ).get
      val messageDocIterator = APIStatsMessageDoc.find( query )
      val messageDoc = messageDocIterator.elements.next
      assert( messageDoc.apiName.toString === "Test" )
      assert( messageDoc.resourceURL.toString === "www.broadbandmap.gov" )
      assert( messageDoc.contextPath.toString === "broadbandmap" )
      assert( messageDoc.responseTime.toString === "23" )

      val queryParams = messageDoc.queryParams._1
      val origQueryParams = LinkedHashMap[String, String]()
      queryParams.foreach( x => {
        origQueryParams ++= LinkedHashMap( x.key.toString -> x.value.toString )
      } )

    }
    "count the number of Test messages that are geospatial" in {
      assert( ( APIStatsMessageDoc where ( _.isGeospatialAPI eqs true ) and ( _.apiName eqs "Test" ) count () ) === 1 )
    }
    "count the number of total foo messages" in {
      assert( APIStatsMessageDoc.numberOMessagesByAPIName( "foo" ) === 2 )
    }
    "be deleted from MongoDB" in {
      val message = new APIStatsMessage( "Test", "broadbandmap", "www.broadbandmap.gov", LinkedHashMap( "geographyType" -> "block" ),
        LinkedHashMap( "latitude" -> "42.456", "longitude" -> "-74.987", "format" -> "json" ), new DateTime(), 23, true, "", false )

      val query = QueryBuilder.start( "apiName" ).is( "Test" ).get
      val messageDocIterator = APIStatsMessageDoc.find( query )
      val messageDoc = messageDocIterator.elements.next
      assert( messageDoc.delete_! === true )
      assert( ( APIStatsMessageDoc where ( _.isGeospatialAPI eqs true ) and ( _.apiName eqs "Test" ) count () ) === 0 )
    }
  }
}