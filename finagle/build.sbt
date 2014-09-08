name := "finagle"

version := "1.0"

scalaVersion := "2.10.3"

resolvers ++= Seq(
  "twitter" at "http://maven.twttr.com",
  "sonatype" at "https://oss.sonatype.org/content/groups/public"
)


libraryDependencies ++= Seq(
  "org.apache.thrift" % "libthrift" % "0.9.1",
  "com.twitter" %% "scrooge-core" % "3.16.1",
  "com.twitter" %% "finagle-core" % "6.18.0",
  "com.twitter" %% "finagle-http" % "6.18.0",
  "com.twitter" %% "finagle-thrift" % "6.18.0",
  "com.twitter" %% "finagle-serversets" % "6.18.0",
  "com.twitter" %% "util-core" % "6.18.0"
)
