package models.resource

import akka.actor.Actor
import akka.camel.{ Consumer, CamelMessage }

class RequestHttpEndpoint extends Consumer with ConsumerType {

  def endpointUri = globals.httpEndpoint

  def receive = {
    case CamelMessage => {
      sender ! ("OK", self)
    }
    case _ =>
      println("RECEIVED!!")
      sender ! "OK"
  }
}