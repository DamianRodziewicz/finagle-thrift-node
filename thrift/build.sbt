import com.twitter.scrooge.ScroogeSBT

name := "pl.binary.thrift"

version := "0.2.0"

scalaVersion := "2.10.3"

resolvers ++= Seq(
  "twitter" at "http://maven.twttr.com",
  "sonatype" at "https://oss.sonatype.org/content/groups/public"
)

libraryDependencies ++= Seq(
  "org.apache.thrift" % "libthrift" % "0.9.1",
  "com.twitter" %% "scrooge-core" % "3.16.1",
  "com.twitter" %% "finagle-thrift" % "6.18.0"
)


ScroogeSBT.newSettings

ScroogeSBT.scroogeThriftSourceFolder in Compile <<= baseDirectory

ScroogeSBT.scroogeThriftSourceFolder in Test <<= baseDirectory
