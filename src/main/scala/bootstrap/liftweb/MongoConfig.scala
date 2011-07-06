package bootstrap.liftweb
import net.liftweb.common.Loggable


import net.liftweb._
import mongodb._
import util.Props
import com.mongodb.{Mongo,ServerAddress}

object AdminDB extends MongoIdentifier {
  def jndiName = "admin"
}

object MongoConfig extends Loggable {

  def init:Unit = {
    val mainMongoHost = new Mongo
    MongoDB.defineDb(DefaultMongoIdentifier, mainMongoHost, "test")
    MongoDB.defineDb(AdminDB, mainMongoHost, "admin")
    logger.info("MongoDB initialized")
  }
  
}