organization := "ch.jamesclonk"

name := "Sudoku"

version := "1.0.0"

scalaVersion := "2.9.2"

scalacOptions ++= Seq("-deprecation")

//libraryDependencies += "org.scalatest" %% "scalatest" % "1.8" % "test"

//libraryDependencies += "junit" % "junit" % "4.10" % "test"

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "1.8" % "test",
    "junit" % "junit" % "4.10" % "test",
    "ch.qos.logback" % "logback-classic" % "1.0.0" % "runtime"
    //"log4j" % "log4j" % "1.2.17",
    //"org.slf4j" % "slf4j-log4j12" % "1.6.6",
    //"org.squeryl" %% "squeryl" % "0.9.5-2",
    //"com.h2database" % "h2" % "1.2.127",
    //"com.jolbox" % "bonecp" % "0.7.1.RELEASE"
)

resolvers ++= Seq(
  "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/",
  //"Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
  "FuseSource Releases" at "http://repo.fusesource.com/nexus/content/repositories/releases",
  //"FuseSource Snapshots" at "http://repo.fusesource.com/nexus/content/repositories/snapshots",
  "Java.net Repository" at "http://download.java.net/maven/2"
)

