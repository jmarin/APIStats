import play.api._
import play.api.Play.current
import com.typesafe.config._
import java.io._
import play.api.libs.concurrent.Akka
import akka.actor.{ ActorSystem, Props }

import models.resource._

package object globals {
  lazy val confFile = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()
  lazy val httpEndpoint = confFile.getString("http-endpoint")
  lazy val isDemo = confFile.getString("isDemo")
}

object Global extends GlobalSettings {

  override def onStart(app: Application) {
    Logger.info("APIStats starting")
    Logger.debug("Application listening for messages at " + globals.httpEndpoint)

    val httpEndpoint = Akka.system.actorOf(Props[RequestHttpEndpoint], name = "httpEndpointActor")
  }

  override def onStop(app: Application) {
    Logger.info("APIStats stopping")
  }

}