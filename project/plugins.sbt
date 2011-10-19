resolvers += Classpaths.typesafeResolver

resolvers += "Web plugin repo" at "http://siasia.github.com/maven2"

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse" % "1.4.0")

libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % (v+"-0.2.7"))


