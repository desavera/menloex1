:load src/menloex1.scala
val transf1DF = initialDF.withColumn("eventTimestamp",unix_timestamp($"eventTime"))
val catsWindowSpec = Window.partitionBy($"userId",$"product").orderBy("eventTimestamp")
val mysub = udf{(a1:Long,a2:Long) => a1 - a2}
val catsDateDiffFunc = coalesce(mysub($"eventTimestamp",lag("eventTimestamp",1).over(catsWindowSpec)),lit(0))
val transf2DF = transf1DF.withColumn("diffTime",catsDateDiffFunc)
