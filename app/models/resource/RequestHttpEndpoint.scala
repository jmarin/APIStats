package models.resource

import akka.actor.{Actor, ActorRef, Props, ActorSystem}
import akka.camel.{Consumer, CamelMessage}

import reactivemongo.api._
import reactivemongo.bson._

import play.modules.reactivemongo._


import play.api.libs.json
import play.api.Play.current

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