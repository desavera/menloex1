:load src/menloex2.scala
transf2DF.groupBy("product").agg(sum($"diffTime") as "Total").orderBy($"Total").show(10)
