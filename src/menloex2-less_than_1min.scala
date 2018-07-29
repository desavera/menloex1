:load src/menloex2.scala
val transf2DF = transf1DF.withColumn("diffTime",catsDateDiffFunc)
transf2DF.groupBy("product").agg(sum($"diffTime") as "Total").show
transf2DF.groupBy("product").agg(sum($"diffTime") as "Total").where("Total < 60").show
transf2DF.groupBy("product").agg(sum($"diffTime") as "Total").where("Total < 60").count
