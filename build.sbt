name := "Menlo Exercise"

version := "0.1"

scalaVersion := "2.11.8"

sparkVersion := "2.1.0"

sparkComponents ++= Seq("sql", "streaming")

libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka-0-8" % "2.1.0"
libraryDependencies += "io.netty" % "netty-all" % "4.0.42.Final"

assemblyJarName in assembly := s"${name.value.replace(' ','-')}-${version.value}.jar"

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)	

assemblyMergeStrategy in assembly := {

    case "META-INF/org/apache/logging/log4j/core/config/plugins/Log4j2Plugins.dat"  => MergeStrategy.first
    case "kryo*" => MergeStrategy.discard
    case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.first
    case PathList("org", "apache", xs @ _*) => MergeStrategy.first
    case PathList("com", "google", xs @ _*) => MergeStrategy.first
    case "overview.html" => MergeStrategy.first
    case "parquet.thrift" => MergeStrategy.first
    case "plugin.xml" => MergeStrategy.first
    case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}


/*
assemblyMergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf")          => MergeStrategy.discard
  case m if m.toLowerCase.matches("meta-inf.*\\.sf$")      => MergeStrategy.discard
  case "log4j.properties"                                  => MergeStrategy.discard
  case m if m.toLowerCase.startsWith("meta-inf/services/") => MergeStrategy.filterDistinctLines
  case "reference.conf"                                    => MergeStrategy.concat
  case _                                                   => MergeStrategy.first
}
*/
