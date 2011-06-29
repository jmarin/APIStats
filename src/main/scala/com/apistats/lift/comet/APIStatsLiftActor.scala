package com.apistats.lift.comet
import net.liftweb.actor.LiftActor
import net.liftweb.http.ListenerManager

object APIStatsLiftActor extends LiftActor with ListenerManager {
    
  private var msgs = Vector("Welcome")
  
  def createUpdate = msgs
    
  override def lowPriority = {
    case s:String => println(s);msgs :+= s; updateListeners()
  }

}