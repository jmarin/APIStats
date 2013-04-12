package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def login = Action {
    Ok(views.html.login("APIStats - Log In"))
  }

  def index = Action {
    Redirect(routes.Application.home())
  }

  def home = Action {
    Ok(views.html.home())
  }

  def alerts = Action {
    Ok(views.html.alerts())
  }

  def settings = Action {
    Ok(views.html.settings())
  }

}