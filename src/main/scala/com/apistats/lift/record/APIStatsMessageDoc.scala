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
class APIStatsMessageDoc private () extends MongoRecord[APIStatsMessageDoc] with ObjectIdPk[APIStatsMessageDoc] {

  def meta = APIStatsMessageDoc

  object apiName extends StringField(this, 100)

  object baseURL extends StringField(this, 255)

  object contextPath extends StringField(this, 20)

  object apiResource extends StringField(this, 20)

  //object pathParams extends MongoMapField[APIStatsMessageDoc, String](this)
  //object queryParams extends MongoMapField[APIStatsMessageDoc, String](this)
  object queryParams extends BsonRecordListField[APIStatsMessageDoc, APIParam](this, APIParam)

  object date extends DateField(this)

  object responseTime extends LongField(this);        /*def findMessagesByAPIName(apiName: String) = {     val query = QueryBuilder.start("apiName").is(apiName).get;    val messageDocIterator = APIStatsMessageDoc.find(query);    val messageDoc = messageDocIterator.elements.next;        val queryParams = messageDoc.queryParams._1;            val message = new APIStatsMessage(messageDoc.apiName.toString, messageDoc.baseURL.toString,        messageDoc.contextPath.toString, messageDoc.apiResource.toString,        LinkedHashMap("geographyType" -> "block"), LinkedHashMap("latitude" -> "42.456", "longitude" -> "-74.987", "format" -> "json"),        new DateTime(), messageDoc.responseTime.toString.toInt)        }*/
};
object APIStatsMessageDoc extends APIStatsMessageDoc with MongoMetaRecord[APIStatsMessageDoc] {    def saveMessage(message: APIStatsMessage) = {        val queryParams = message.queryParams.toMap;    val pathParams = message.pathParams.toMap;    val queryParamsLinkedHashMap = LinkedHashMap(queryParams.toSeq:_*);    val pathParamsLinkedHashMap = LinkedHashMap(pathParams.toSeq:_*);    var listQueryParam:List[APIParam] = List[APIParam]();    var listPathParam:List[APIParam] = List[APIParam]();    queryParamsLinkedHashMap.foreach(x => {      val queryParam = APIParam.createRecord.key(x._1.toString).value(x._2.toString);      listQueryParam ++= List(queryParam)    });            val record = APIStatsMessageDoc.createRecord       .apiName(message.apiName)       .baseURL(message.baseURL)       .contextPath(message.contextPath)       .apiResource(message.apiResource)       //.pathParams(message.pathParams.toMap)       .queryParams(listQueryParam)       .date(message.date.toCalendar(new Locale("en-US")).getTime)       .responseTime(message.reponseTime)       .save      }    }