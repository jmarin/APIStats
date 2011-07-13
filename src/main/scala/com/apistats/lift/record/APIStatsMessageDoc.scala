package com.apistats.lift.record //import net.liftweb.mongodb.record.field.MongoJsonObjectListField
import net.liftweb.record.field.LongField
import net.liftweb.mongodb.JsonObject
import net.liftweb.record.field._
import net.liftweb.mongodb.record.field._
import net.liftweb.mongodb.record.MongoMetaRecord
import net.liftweb.mongodb.record.MongoRecord
import com.mongodb.QueryBuilder
import com.apistatsmodel.messages.APIStatsMessage
import scala.collection.mutable.LinkedHashMap
import org.joda.time.DateTime
import java.util.Locale
import com.foursquare.rogue.LatLong
import net.liftweb.mongodb.record.MongoId;import com.foursquare.rogue.Rogue._
class APIStatsMessageDoc private () extends MongoRecord[APIStatsMessageDoc] with MongoId[APIStatsMessageDoc] {

  def meta = APIStatsMessageDoc

  object apiName extends StringField(this, 100)

  object baseURL extends StringField(this, 255)

  object contextPath extends StringField(this, 20)

  object apiResource extends StringField(this, 20)
  object pathParams extends BsonRecordListField[APIStatsMessageDoc, APIParam](this, APIParam)  
  object queryParams extends BsonRecordListField[APIStatsMessageDoc, APIParam](this, APIParam)

  object date extends DateField(this)

  object responseTime extends LongField(this);    object isGeospatialAPI extends BooleanField(this);    object geolatlng extends MongoCaseClassField[APIStatsMessageDoc, LatLong](this){override def name = "latlng"} 
};
object APIStatsMessageDoc extends APIStatsMessageDoc with MongoMetaRecord[APIStatsMessageDoc] {    def saveMessage(message: APIStatsMessage):Unit = {    val queryParams = message.queryParams.toMap;    val pathParams = message.pathParams.toMap;    val queryParamsLinkedHashMap = LinkedHashMap(queryParams.toSeq:_*);    val pathParamsLinkedHashMap = LinkedHashMap(pathParams.toSeq:_*);    var listQueryParam:List[APIParam] = List[APIParam]();    var listPathParam:List[APIParam] = List[APIParam]();    queryParamsLinkedHashMap.foreach(x => {      val queryParam = APIParam.createRecord.key(x._1.toString).value(x._2.toString);      listQueryParam ++= List(queryParam)    });    pathParamsLinkedHashMap.foreach(x => {      val pathParam = APIParam.createRecord.key(x._1.toString).value(x._2.toString);      listPathParam ++= List(pathParam)    });    val record = APIStatsMessageDoc.createRecord;    if (message.isGeospatial == true){      val latitudeQueryParam = listQueryParam.filter(x => x.key.toString == "latitude");      val longitudeQueryParam = listQueryParam.filter(x => x.key.toString == "longitude");      val latitude = latitudeQueryParam.iterator.next.value.toString.toDouble;      val longitude = longitudeQueryParam.iterator.next.value.toString.toDouble;      val latlng = LatLong(latitude, longitude);      record.geolatlng(latlng)    };    record.apiName(message.apiName)      .baseURL(message.baseURL)      .contextPath(message.contextPath)      .apiResource(message.apiResource)      .pathParams(listPathParam)      .queryParams(listQueryParam)      .date(message.date.toCalendar(new Locale("en-US")).getTime)      .responseTime(message.reponseTime)      .isGeospatialAPI(message.isGeospatial)      .save;  };    def numberOMessagesByAPIName(apiName:String):Long = {   val numOfMessages = APIStatsMessageDoc where (_.apiName eqs apiName) count();   numOfMessages.toString.toLong  }     }