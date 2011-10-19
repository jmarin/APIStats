name := "APIStatsModel"

version := "1.2"

scalaVersion := "2.9.1"
  
scalacOptions += "-deprecation"

libraryDependencies += "junit" % "junit" % "4.8" % "test"

libraryDependencies += "org.scalatest" % "scalatest_2.9.0" % "1.6.1" % "test"

libraryDependencies += "joda-time" % "joda-time" % "1.6.2"

publishTo := Some(Resolver.file("Local Maven Repository", new java.io.File(Path.userHome.absolutePath + "/.m2/repository")))
