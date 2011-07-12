package com.apistats.lift.record
import net.liftweb.record.field.StringField

import net.liftweb.mongodb.record.BsonMetaRecord

import net.liftweb.mongodb.record.BsonRecord

import net.liftweb.mongodb.JsonObject

class APIParam private () extends BsonRecord[APIParam] {
  def meta = APIParam

  object key extends StringField(this, 100)
  object value extends StringField(this, 100)
}
object APIParam extends APIParam with BsonMetaRecord[APIParam]