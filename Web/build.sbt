name := "APIStatsWeb"

version := "1.2	"

scalaVersion := "2.9.1"

scalacOptions += "-deprecation"

resolvers += ScalaToolsReleases

resolvers += "download.java.net maven2" at "http://download.java.net/maven/2" 

resolvers += "GuicyFruit Release Repository" at "http://guiceyfruit.googlecode.com/svn/repo/releases"

resolvers += "Akka Repository" at "http://akka.io/repository"


checksums := Nil

//If running with JRebel, avoid unnecessary redeploys

scanDirectories in Compile := Nil

//Lift dependencies

seq(webSettings: _*)

libraryDependencies ++= {
	val liftVersion = "2.4-M4"
	Seq(
    	"net.liftweb" %% "lift-webkit" % liftVersion % "compile->default",
    	"net.liftweb" %% "lift-mapper" % liftVersion % "compile->default",
    	"net.liftweb" %% "lift-wizard" % liftVersion % "compile->default", 
    	"net.liftweb" %% "lift-mongodb-record" % liftVersion % "compile->default"
    )
}

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.8.1" % "test->default",
  "org.eclipse.jetty" % "jetty-webapp" % "7.5.3.v20111011" % "container",
  "javax.servlet" % "servlet-api" % "2.5" % "provided->default",
  "com.h2database" % "h2" % "1.2.138",
  "ch.qos.logback" % "logback-classic" % "0.9.26" % "compile->default"
)


//Akka dependencies

libraryDependencies ++= {
	val akkaVersion = "1.2"
	Seq(
    	"se.scalablesolutions.akka" % "akka-actor" % akkaVersion % "compile->default",
    	"se.scalablesolutions.akka" % "akka-remote" % akkaVersion % "compile->default",
    	"se.scalablesolutions.akka" % "akka-testkit" % akkaVersion % "test"
    )
}
 

//APIStatsModel dependency (needs to be in local repository first)

libraryDependencies += "org.scalatest" % "scalatest_2.9.0" % "1.6.1" % "test"

libraryDependencies += "joda-time" % "joda-time" % "1.6.2"

libraryDependencies += "com.foursquare" %% "rogue" % "1.0.24"


