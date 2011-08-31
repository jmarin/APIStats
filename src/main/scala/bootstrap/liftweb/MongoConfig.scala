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
    val mongouser:String = (Props.get("mongouser") openOr (""))
    val mongopassword:String = (Props.get("mongopassword") openOr(""))
    val mongoOptions = new MongoOptions
    mongoOptions.socketTimeout = 30000

    MongoDB.defineDbAuth(DefaultMongoIdentifier, new Mongo(server, mongoOptions), "gisdb", mongouser, mongopassword)
    logger.info("MongoDB initialized on server " + mongoserver + " running on port " + mongoport)
  }

}