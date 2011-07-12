package com.apistats.lift.record//import net.liftweb.mongodb.record.field.MongoJsonObjectListField
import net.liftweb.record.field.LongField;import net.liftweb.mongodb.JsonObject;import net.liftweb.record.field._
import net.liftweb.mongodb.record.field._

import net.liftweb.mongodb.record.MongoMetaRecord

import net.liftweb.mongodb.record.MongoRecord;
class APIStatsMessageDoc private () extends MongoRecord[APIStatsMessageDoc] with ObjectIdPk[APIStatsMessageDoc] {
 
  def meta = APIStatsMessageDoc

  object apiName extends StringField(this, 100)

  object baseURL extends StringField(this, 255)

  object contextPath extends StringField(this, 20)

  object apiResource extends StringField(this, 20)

  //object pathParams extends MongoMapField[APIStatsMessageDoc, String](this)    
  //object queryParams extends MongoMapField[APIStatsMessageDoc, String](this)    object queryParams extends BsonRecordListField[APIStatsMessageDoc, QueryParam](this,QueryParam)

  object date extends DateField(this)

  object responseTime extends LongField(this)

};
object APIStatsMessageDoc extends APIStatsMessageDoc with MongoMetaRecord[APIStatsMessageDoc] 