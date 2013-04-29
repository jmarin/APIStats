package models.resource

import play.api.Play.current
import play.api.libs.concurrent.Akka
import akka.actor.{ Actor, Props }
import akka.camel.{ Consumer, CamelMessage }
import models.processing._

class RequestHttpEndpoint extends Consumer with ConsumerType {

  val requestValidator = Akka.system.actorOf(Props[RequestValidator], name = "requestValidator")

  def endpointUri = globals.httpEndpoint

  def receive = {
    case msg: CamelMessage => {
      requestValidator ! msg.bodyAs[String]
      sender ! ("OK")
    }
    case _ =>
      println("RECEIVED!!")
      sender ! "OK"
  }
}