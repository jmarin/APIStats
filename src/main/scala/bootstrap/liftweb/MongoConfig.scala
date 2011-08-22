package bootstrap.liftweb
import com.mongodb.MongoOptions

import net.liftweb.common.Loggable

import net.liftweb._
import mongodb._
import util.Props
import com.mongodb.{ Mongo, ServerAddress }


object MongoConfig extends Loggable {

  def init: Unit = {
    val mongoserver:String = Props.get("mongoserver") openOr("")
    val mongoport:Int = (Props.get("mongoport") openOr ("")).toInt
    val server = new ServerAddress(mongoserver, mongoport)
    val mongoOptions = new MongoOptions
    mongoOptions.socketTimeout = 10000

    MongoDB.defineDb(DefaultMongoIdentifier, new Mongo(server, mongoOptions), "gisdb")
    logger.info("MongoDB initialized on server " + mongoserver + " running on port " + mongoport)
  }

}