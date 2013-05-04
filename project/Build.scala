import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "APIStats"
  val appVersion      = "1.0-SNAPSHOT"
  val akkaVersion     = "2.1.2"


  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-remote" % akkaVersion,
    "org.reactivemongo" %% "play2-reactivemongo" % "0.9",
    "joda-time" % "joda-time" % "2.2"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    //resolvers += "Apache releases" at "https://repository.apache.org/content/repositories/releases/"  
  )

}
