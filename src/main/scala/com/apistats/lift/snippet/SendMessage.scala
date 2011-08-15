package com.apistats.lift.snippet
import akka.actor.Actor._
import akka.actor._
import com.apistatsmodel.messages.APIStatsMessage
import net.liftweb.http.js.JE._
import net.liftweb.http.js.JsCmds._
import net.liftweb.http.js._
import net.liftweb.http._
import net.liftweb._
import org.joda.time.DateTime
import scala.collection.mutable.LinkedHashMap

/**
 * A snippet transforms input to output... it transforms
 * templates to dynamic content.  Lift's templates can invoke
 * snippets and the snippets are resolved in many different
 * ways including "by convention".  The snippet package
 * has named snippets and those snippets can be classes
 * that are instantiated when invoked or they can be
 * objects, singletons.  Singletons are useful if there's
 * no explicit state managed in the snippet.
 */
object SendMessage {

  /**
   * The render method in this case returns a function
   * that transforms NodeSeq => NodeSeq.  In this case,
   * the function transforms a form input element by attaching
   * behavior to the input.  The behavior is to send a message
   * to the ChatServer and then returns JavaScript which
   * clears the input.
   */
  def render = SHtml.onSubmit( s => {

    val message = new APIStatsMessage( "Test", "broadbandmap", "www.broadbandmap.gov/broadbandmap/broadband/spring2011/wireline?latitude=42.456&longitude=-74.987&format=json", LinkedHashMap( "geographyType" -> "block" ),
      LinkedHashMap( "latitude" -> "42.456", "longitude" -> "-74.987", "format" -> "json" ), new DateTime(), 3, true )

    val statsActor = remote.actorFor( "apistats-actor", "localhost", 2552 )
    statsActor ! message
    //APIStatsLiftActor ! s
    SetValById( "chat_in", "" )
  } )

}
