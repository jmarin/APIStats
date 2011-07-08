package bootstrap.liftweb
import com.mongodb.MongoOptions

import net.liftweb.common.Loggable

import net.liftweb._
import mongodb._
import util.Props
import com.mongodb.{ Mongo, ServerAddress }


object MongoConfig extends Loggable {

  def init: Unit = {
    val server = new ServerAddress("127.0.0.1", 27017)
    val mongoOptions = new MongoOptions
    mongoOptions.socketTimeout = 100

    MongoDB.defineDb(DefaultMongoIdentifier, new Mongo(server, mongoOptions), "gisdb")
    logger.info("MongoDB initialized")
  }

}