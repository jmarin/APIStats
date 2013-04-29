import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "APIStats"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    "com.typesafe.akka" %% "akka-camel" % "2.1.2",
    "org.apache.camel" % "camel-jetty" % "2.10.4",
    "org.reactivemongo" %% "play2-reactivemongo" % "0.9"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    //resolvers += "Apache releases" at "https://repository.apache.org/content/repositories/releases/"  
  )

}
