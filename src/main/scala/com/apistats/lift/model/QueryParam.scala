package com.apistats.lift.model
import net.liftweb.record.field.StringField

import net.liftweb.mongodb.record.BsonMetaRecord

import net.liftweb.mongodb.record.BsonRecord

import net.liftweb.mongodb.JsonObject

class QueryParam private () extends BsonRecord[QueryParam] {
  def meta = QueryParam

  object key extends StringField(this, 100)
  object value extends StringField(this, 100)
}
object QueryParam extends QueryParam with BsonMetaRecord[QueryParam]