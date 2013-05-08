import play.api._
import play.api.Play.current
import com.typesafe.config._
import java.io._
import play.api.libs.concurrent.Akka
import akka.actor.{ ActorSystem, Props }
import play.modules.reactivemongo._

import models.processing._

package object globals {
  lazy val confFile = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()
  lazy val httpEndpoint = confFile.getString("http-endpoint")
  lazy val isDemo = confFile.getString("isDemo")
  lazy val refreshRate = confFile.getInt("refresh-rate")
  lazy val mongoServers = confFile.getString("mongodb.servers")
  lazy val mongoDB = confFile.getString("mongodb.db")
  lazy val requestCollSize = confFile.getInt("request-collection.size")
  lazy val db = ReactiveMongoPlugin.db
  val actorSystem = ActorSystem("api-stats-system")
  val messageProcessor = actorSystem.actorOf(
    Props[MessageProcessor], name = "messageProcessor")
}

object Global extends GlobalSettings {

  override def onStart(app: Application) {
    Logger.info("APIStats starting")
    // val messageProcessor = globals.actorSystem.actorOf(
    //   Props[MessageProcessor], name = "messageProcessor")
  }

  override def onStop(app: Application) {
    Logger.info("APIStats stopping")
  }

}