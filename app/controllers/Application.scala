package controllers

import java.util.Date

import play.api._
import play.api.mvc._
import play.api.Play.current
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._
import play.api.libs.iteratee._
import scala.concurrent._
import ExecutionContext.Implicits.global

import play.api.libs.concurrent.Akka
import akka.actor._

import reactivemongo.api._
import reactivemongo.bson._
import reactivemongo.bson.utils.Converters
import play.modules.reactivemongo._
import play.modules.reactivemongo.json._
import play.modules.reactivemongo.json.collection.JSONCollection

import models._
import models.processing._
import globals._

object Application extends Controller with MongoController {
  override val db = globals.db
  def messages = db.collection("messages")
  
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
    try {
      val message = Json.fromJson[Message](request.body).get
      val futureInsert = messages.insert(message)
      Async {
        futureInsert.map { _ =>
          messageProcessor ! message
          Ok
        }.recover {
          case e =>
            println("Error occured")
            InternalServerError(e.getMessage)
        }
      }
    } catch {
      case e: Exception =>
        InternalServerError(e.getMessage)
    }

  }

  



}