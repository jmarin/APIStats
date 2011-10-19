package com.apistatsmodel.messages
import org.joda.time.DateTime
import scala.collection.mutable.Map

case class APIStatsMessage( apiName : String, contextPath : String, resourceURL : String, pathParams : Map[String, String],
                            queryParams : Map[String, String], date : DateTime, reponseTime : Long, isGeospatial : Boolean,
                            exceptionMessage : String, hasFailed : Boolean ) {
}