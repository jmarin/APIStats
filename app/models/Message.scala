package models

import java.util.Date
import org.joda.time.DateTime
import play.api.data._
import play.api.libs.json._
import play.api.libs.functional.syntax._

import reactivemongo.bson._

case class Message(
  //id: Option[BSONObjectID],
  id: Long,
  name: String,
  version: String,
  request_url: String,
  request_method: String,
  host: String,
  referer: String,
  status_code: Int,
  content_type: String,
  date: Long,
  error: Boolean,
  response_time: Int,
  response_size: Int,
  remote_ip: String)

object Message {

  implicit val MessageFormat = (
    (__ \ "id").format[Long] and
    (__ \ "name").format[String] and
    (__ \ "version").format[String] and
    (__ \ "request_url").format[String] and
    (__ \ "request_method").format[String] and
    (__ \ "host").format[String] and
    (__ \ "referer").format[String] and
    (__ \ "status_code").format[Int] and
    (__ \ "content_type").format[String] and
    (__ \ "date").format[Long] and
    (__ \ "error").format[Boolean] and
    (__ \ "response_time").format[Int] and
    (__ \ "response_size").format[Int] and
    (__ \ "remote_ip").format[String])(Message.apply, unlift(Message.unapply))

  implicit object MessageBSONReader extends BSONDocumentReader[Message] {
    def read(doc: BSONDocument): Message = {
      Message(
        //doc.getAs[BSONObjectID]("_id"),
        doc.getAs[BSONLong]("id").get.value,
        doc.getAs[BSONString]("name").get.value,
        doc.getAs[BSONString]("version").get.value,
        doc.getAs[BSONString]("request_url").get.value,
        doc.getAs[BSONString]("request_method").get.value,
        doc.getAs[BSONString]("host").get.value,
        doc.getAs[BSONString]("referer").get.value,
        doc.getAs[BSONInteger]("status_code").get.value,
        doc.getAs[BSONString]("content_type").get.value,
        doc.getAs[BSONLong]("date").get.value,
        doc.getAs[BSONBoolean]("error").get.value,
        doc.getAs[BSONInteger]("response_time").get.value,
        doc.getAs[BSONInteger]("response_size").get.value,
        doc.getAs[BSONString]("remote_ip").get.value)

    }
  }

  implicit object MessageBSONWriter extends BSONDocumentWriter[Message] {
    def write(Message: Message): BSONDocument = {
      BSONDocument(
        //"_id" -> Message.id.getOrElse(BSONObjectID.generate),
        "id" -> Message.id,
        "name" -> Message.name,
        "version" -> Message.version,
        "request_url" -> Message.request_url,
        "request_method" -> Message.request_method,
        "host" -> Message.host,
        "referer" -> Message.referer,
        "status_code" -> Message.status_code,
        "content_type" -> Message.content_type,
        "date" -> Message.date,
        "error" -> Message.error,
        "response_time" -> Message.response_time,
        "response_size" -> Message.response_size,
        "remote_ip" -> Message.remote_ip)
    }
  }

}