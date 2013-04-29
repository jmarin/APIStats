package models.processing

import play.api.libs.concurrent.Akka
import akka.actor._
import models.resource._

class RequestProcessor extends Actor {

  def receive = {
    case HttpConsumerType =>
      println("HTTP ConsumerType")

    case TcpConsumerType =>
      println("TCP ConsumerType")
  }

}