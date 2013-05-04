package models.processing

import play.api.Play.current
import play.api.libs.concurrent.Akka
import akka.actor._
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global

import reactivemongo.api._
import reactivemongo.bson._

import play.api.libs.json._
import play.api.libs.iteratee._
import play.modules.reactivemongo._
import reactivemongo.bson._
import reactivemongo.core.commands._

import models._

class MessageProcessor extends Actor {

  val db = globals.db
  def globalStats = db.collection("globalstats")

  var totalCount = 0

  val globalStatsProcessor = Akka.system.actorOf(Props[GlobalStatsProcessor], name = "globalStatsProcessor")

  def receive = {
    case message: Message => {
      val query = BSONDocument()
      val updateQuery = BSONDocument(
        "$inc" -> BSONDocument(
          "total_count" -> 1))

      globalStats.update(
        query,
        updateQuery,
        new GetLastError(false, None, false), true).map { lastError =>
          val cursor = globalStats.find(query).cursor[BSONDocument]
          cursor.enumerate.apply(Iteratee.foreach { doc =>
            println("Doc: " + BSONDocument.pretty(doc))
          })
        }.recover {
          case e =>
            println("Error occured")
        }


    }
    case _ => println
  }

}