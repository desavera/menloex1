import org.apache.spark.sql.expressions.Window

val initialDF = spark.read.json("data/input.json")
val windowDF = initialDF.groupBy(window(initialDF.col("eventTime"),"5 minutes")).agg(count("*") as "counter")
val renames = Seq ("sessionId","sessionStartTime","sessionEndTime")
val sessionsDF  = windowDF.withColumn("sessionId",rand).sort("window.start").drop("counter").select("sessionId","window.start","window.end").toDF(renames: _*)
val finalDF = initialDF.as('i).join(sessionsDF.as('f),$"i.eventTime" >= $"f.sessionStartTime" && $"i.eventTime" <= $"f.sessionEndTime")
finalDF.show(truncate=false)
