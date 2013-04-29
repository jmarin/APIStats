package models.processing

import play.api._
import play.api.Play.current
import play.api.libs.concurrent.Akka
import akka.actor.{Actor, Props}
import models.persistence._
import akka.camel.CamelMessage

class RequestValidator extends Actor {

  val requestPersistence = Akka.system.actorOf(Props[RequestPersistence], name = "requestPersistence")

  def receive = {

    case msg:String =>
      Logger.debug("Received " + msg)
      requestPersistence ! msg

  }
}