package models.schema

import java.util.Date

case class Request(id: Int, name: String, version: String,
  request_url: String, request_method: String,
  host: String, referer: String, status_code: Int,
  content_type: String, date: Date, error: Boolean,
  response_time: Int, response_size: Int, remote_ip: String)