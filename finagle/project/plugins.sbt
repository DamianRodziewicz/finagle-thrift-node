logLevel := Level.Warn

resolvers += "twitter" at "http://maven.twttr.com"

resolvers += "sonatype" at "https://oss.sonatype.org/content/groups/public"

addSbtPlugin("com.twitter" %% "scrooge-sbt-plugin" % "3.16.1")
