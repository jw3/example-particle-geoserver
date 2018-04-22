name := "example-particle-geoserver"

version := "0.1"
organization := "com.github.jw3"
scalaVersion := "2.12.5"

resolvers += Resolver.bintrayRepo("jw3", "maven")

libraryDependencies ++= {
  Seq(
    // misc
    "com.iheart" %% "ficus" % "1.4.3",
    "com.github.jw3" %% "geotrellis-vector" % "12.2.0.0",
    "com.github.jw3" %% "geotrellis-slick" % "12.2.0.0",
    // akka
    "com.typesafe.akka" %% "akka-actor" % "2.5.12",
    "com.typesafe.akka" %% "akka-stream" % "2.5.12",
    "com.typesafe.akka" %% "akka-http" % "10.1.1",
    "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.1",
    // logging
    "org.slf4j" % "slf4j-nop" % "1.6.4",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    // slick
    "com.typesafe.slick" %% "slick" % "3.1.0",
    "org.postgresql" % "postgresql" % "42.2.2",
    // testing
    "com.typesafe.akka" %% "akka-testkit" % "2.5.12" % Test,
    "com.typesafe.akka" %% "akka-http-testkit" % "10.1.1" % Test
  )
}

enablePlugins(JavaServerAppPackaging, DockerPlugin)
