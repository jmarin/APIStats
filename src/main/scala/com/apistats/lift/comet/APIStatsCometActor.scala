package com.apistats.lift.comet

import net.liftweb._
import http._
import util._
import Helpers._

class APIStatsCometActor extends CometActor with CometListener {

  private var msgs: Vector[String] = Vector()

  def registerWith = APIStatsLiftActor

  override def lowPriority = {
    case v: Vector[String] => msgs = v; reRender()
  }

  def render = "li *" #> msgs & ClearClearable

}