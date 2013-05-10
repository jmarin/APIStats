package controllers

import java.util.Date

import scala.util.{ Success, Failure }
import play.api._
import play.api.mvc._
import play.api.templates.Html
import play.api.Play.current
import play.api.libs.json._
import play.api.libs.Comet
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._
import play.api.libs.iteratee._
import scala.concurrent._
import ExecutionContext.Implicits.global
import play.api.libs.concurrent._

import play.api.libs.concurrent.Akka
import akka.actor._
import scala.concurrent.duration._

import reactivemongo.api._
import reactivemongo.bson._
import reactivemongo.bson.utils.Converters
import play.modules.reactivemongo._
import play.modules.reactivemongo.json._
import reactivemongo.core.commands._
import play.modules.reactivemongo.json.collection.JSONCollection

import models._
import models.processing._
import globals._

object Application extends Controller with MongoController {
  override val db = globals.db
  val messages = db.collection("messages")
  val globalStats = db.collection("globalstats")

  def login = Action {
    Ok(views.html.login("APIStats - Log In"))
  }
  def index = Action {
    Redirect(routes.Application.home())
  }

  def home = Action {
    Ok(views.html.home())
  }

  def alerts = Action {
    Ok(views.html.alerts())
  }

  def settings = Action {
    Ok(views.html.settings())
  }

  def insertRequest = Action(parse.json) { request =>
    Async {
      val message = Json.fromJson[Message](request.body).get
      val apiName = message.name
      val responseTime = message.response_time
      val responseSize = message.response_size
      val futureInsert = messages.insert(message)
      val error_count = if (message.error == true) 1 else 0
      val query = BSONDocument()
      val updateQuery = BSONDocument(
        "$inc" -> BSONDocument(
          "total_count" -> 1,
          "error_count" -> error_count,
          "apis." + apiName + ".count" -> 1,
          "apis." + apiName + ".error" -> error_count,
          "apis." + apiName + ".response_time" -> responseTime,
          "apis." + apiName + ".response_size" -> responseSize))

      val futureUpdate = globalStats.update(
        query,
        updateQuery,
        new GetLastError(false, None, false), true)
      futureUpdate.map { x =>
        Ok("OK")
      }
    }

  }

  def countRequest = Action { request =>

    def count: String = {
      val query = BSONDocument()
      val foundQuery = globalStats.find(query).one
      def updateCount = for {
        found <- foundQuery
        //val total = found.get.getAs[BSONInteger]("total_count").get.value
        //val error = found.get.getAs[BSONInteger]("error_count").get.value
      } yield found.get.getAs[BSONInteger]("total_count").get.value.toString + "," +
        found.get.getAs[BSONInteger]("error_count").get.value.toString
      Await.result(updateCount, 200 milliseconds)
    }

    lazy val enumerator: Enumerator[String] = {
      import play.api.libs.concurrent._
      Enumerator.generateM {
        Promise.timeout(Some(count), globals.refreshRate milliseconds)
      }
    }
    Ok.stream(enumerator >>> Enumerator.eof &> Comet(callback = "parent.updateCounter"))

  }
}
