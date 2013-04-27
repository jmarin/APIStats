APIStats Data Model
===================

This document describes the information captured in APIStats, including raw request data as well as aggregated information for display in the application. 

## Request Data

The raw request data captured by APIStats is described below. All fields are considered optional, when they are missing in some documents no visualization will be available for the time range in which those documents are displayed and / or queried. It is recommended that the API captures as many of these fields as possible, to provide a better analytical capability to the application. Most of these fields can be obtained by commong programming practices, with all modern languages that are used today to develop web applications and APIs. 

* `name`: The API name
	ex. Magazines
* `version`: API version for this particular request. Usually takes the following form: v1, v2, etc.	 	
* `request_url`: The complete URL of the request, including path parameters and query paramaters
* `request_method`: HTTP method used for the call. Can take the following values: GET, POST, PUT, DELETE
* `host`: Host entry in Request Headers, host serving the API
* `referer`: URL of application issuing the request to the API
* `status_code`: The HTTP status code of the response, simplify to the following:
	* `200 - OK`: The API is responding normally, and sends data back to the client
	* `400 - Bad Request`: The client issued an incorrect request
	* `500 - Internal Server Error`: The server encountered an error. More information can be obtained in the `error_message` field
* `content_type`: MIME type for the response
* `date`: Timestamp when the request was completed in the server
* `error`: `true` or `false`. When there is an error, there will be an entry here with all the [error](#error) fields
* `response_time`: time it takes for the server to produce an API response (in ms)
* `response_size`: size of the response (in Kb)


## Error

An error occurs when the client issues a bad request, or when the server cannot produce a regular API response to the client. In both cases an error message is returned, an example of which follows (from the WHite House's [api-standards](https://github.com/WhiteHouse/api-standards/blob/master/README.md#error-handling)):

```
{
  "status" : "400",
  "developerMessage" : "Verbose, plain language description of the problem. Provide developers
   suggestions about how to solve their problems here",
  "userMessage" : "This is a message that can be passed along to end-users, if needed.",
  "errorCode" : "444444",
  "more info" : "http://www.example.gov/developer/path/to/help/for/444444,
   http://drupal.org/node/444444",
}
```

