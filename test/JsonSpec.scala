package test

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._
import models._

class JsonSpec extends Specification {

  "JSON Model" should {
    "Message: Serialize and deserialize" in {
      val jsonMessage = Json.obj(
        "id" -> 1,
        "name" -> "API Name",
        "version" -> "v1",
        "request_url" -> "http://localhost/apiName/v1/list.json",
        "request_method" -> "GET",
        "host" -> "localhost",
        "referer" -> "http://localhost/webapp",
        "status_code" -> 200,
        "content_type" -> "application/json",
        "date" -> 1351504814,
        "error" -> false,
        "response_time" -> 120,
        "response_size" -> 52,
        "remote_ip" -> "127.0.0.1")

      val message = Message(
        1,
        "API Name",
        "v1",
        "http://localhost/apiName/v1/list.json",
        "GET",
        "localhost",
        "http://localhost/webapp",
        200,
        "application/json",
        1351504814,
        false,
        120,
        52,
        "127.0.0.1")

      Json.toJson(message) must beEqualTo(jsonMessage)

      Json.fromJson[Message](jsonMessage).get must beEqualTo(message)
    }

    "GlobalStats: Serialize and deserialize" in {
      val jsonGlobalStats = Json.obj(
        "total_count" -> 100,
        "total_error_count" -> 2,
        "unique_api_count" -> 5)

      val globalStats = GlobalStats(100, 2, 5)

      Json.toJson(globalStats) must beEqualTo(jsonGlobalStats)
      Json.fromJson[GlobalStats](jsonGlobalStats).get must beEqualTo(globalStats)
    }

  }
}