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
import net.liftweb.mongodb.record.MongoId;
class APIStatsMessageDoc private () extends MongoRecord[APIStatsMessageDoc] with MongoId[APIStatsMessageDoc] {

  def meta = APIStatsMessageDoc

  object apiName extends StringField(this, 100)

  object baseURL extends StringField(this, 255)

  object contextPath extends StringField(this, 20)

  object apiResource extends StringField(this, 20)

  object queryParams extends BsonRecordListField[APIStatsMessageDoc, APIParam](this, APIParam)

  object date extends DateField(this)

  object responseTime extends LongField(this);
};
object APIStatsMessageDoc extends APIStatsMessageDoc with MongoMetaRecord[APIStatsMessageDoc] {