name := "APIStats"

version := "1.0"

scalaVersion := "2.9.0-1"

scalacOptions += "-deprecation"

//resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases"

resolvers += ScalaToolsSnapshots

resolvers += "download.java.net maven2" at "http://download.java.net/maven/2" 

checksums := Nil

//Lift dependencies

seq(WebPlugin.webSettings: _*)

libraryDependencies ++= {
	val liftVersion = "2.4-M3"
	Seq(
    	"net.liftweb" %% "lift-webkit" % liftVersion % "compile->default",
    	"net.liftweb" %% "lift-mapper" % liftVersion % "compile->default",
    	"net.liftweb" %% "lift-wizard" % liftVersion % "compile->default", 
    	"net.liftweb" %% "lift-mongodb-record" % liftVersion % "compile->default"
    )
}

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.8.1" % "test->default",
  "org.mortbay.jetty" % "jetty" % "6.1.22" % "jetty",
  "javax.servlet" % "servlet-api" % "2.5" % "provided->default",
  "com.h2database" % "h2" % "1.2.138",
  "ch.qos.logback" % "logback-classic" % "0.9.26" % "compile->default"
)


//Akka dependencies

libraryDependencies ++= {
	val akkaVersion = "1.1.2"
	Seq(
    	"se.scalablesolutions.akka" % "akka-actor" % akkaVersion % "compile->default",
    	"se.scalablesolutions.akka" % "akka-remote" % akkaVersion % "compile->default",
    	"se.scalablesolutions.akka" % "akka-testkit" % akkaVersion % "test"
    )
}
 

//APIStatsModel dependency (needs to be in local repository first)

libraryDependencies += "apistatsmodel" % "apistatsmodel_2.9.0-1" % "1.0"


libraryDependencies += "org.scalatest" % "scalatest_2.9.0" % "1.6.1" % "test"

libraryDependencies += "joda-time" % "joda-time" % "1.6.2"

libraryDependencies += "com.foursquare" %% "rogue" % "1.0.15"


