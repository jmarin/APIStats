package models.persistence

import reactivemongo.api._
import reactivemongo.bson._

import play.modules.reactivemongo._

import play.api._
import play.api.libs.json
import play.api.Play.current

import akka.actor.Actor

class RequestPersistence extends Actor {

  def receive = {

    case msg:String =>
      Logger.debug("Message persisted: " + msg)
  }
}