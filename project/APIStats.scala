import sbt._

object APIStats extends Build {

  lazy val root = Project("root", file(".")) aggregate (apistats, apistatsmodel)

  lazy val apistats = Project("apistats", file("Web")) dependsOn (apistatsmodel)

  lazy val apistatsmodel = Project("apistatsmodel", file("APIStatsModel"))
  
  
  

}



