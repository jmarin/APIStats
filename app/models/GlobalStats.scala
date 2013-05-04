package models

import play.api.data._
import play.api.libs.json._
import play.api.libs.functional.syntax._

import reactivemongo.bson._

case class GlobalStats(
  total_count: Long,
  total_error_count: Long,
  unique_api_count: Int)

object GlobalStats {

  implicit val globalStatsFormat = (
    (__ \ 'total_count).format[Long] and
    (__ \ 'total_error_count).format[Long] and
    (__ \ 'unique_api_count).format[Int])(GlobalStats.apply, unlift(GlobalStats.unapply))

  implicit object GlobalStatsBDONReader extends BSONDocumentReader[GlobalStats] {
    def read(doc: BSONDocument) = {
      GlobalStats(
        doc.getAs[BSONLong]("total_count").get.value,
        doc.getAs[BSONLong]("total_error_count").get.value,
        doc.getAs[BSONInteger]("unique_api_count").get.value)
    }
  }

  implicit object GlobalStatsBSONWriter extends BSONDocumentWriter[GlobalStats] {
    def write(stats: GlobalStats): BSONDocument = {
      BSONDocument(
        "total_count" -> stats.total_count,
        "total_error_count" -> stats.total_error_count,
        "unique_api_count" -> stats.unique_api_count)
    }

  }

}

