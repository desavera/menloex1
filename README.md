# menloex1
Apple on boarding exercise 1

## Motivation

Exercise Apache Spark Streaming and SQL basic windowing functions.

## Description

Domain: say we have an ecommerce site with products divided into categories like toys, electronics etc. We receive events like product was seen (impression), product page was opened, product was purchased etc. 

###Task #1: 
Enrich incoming data with sessions. Definition of a session: it contains consecutive events that belong to a single category and are not more than 5 minutes away from each other. Output should look like this (session columns are in bold):
eventTime, eventType, category, userId, …, sessionId, sessionStartTime, sessionEndTime  
Implement it using 1) sql window functions and 2) Spark aggregator.

####Solution:

src/menloex1.scala

basically a script that assumes the input file exactly in the format given and stored in hdfs at /user/root/data directory after parsed with some csv to json like the one provided in the util directory (i.e. csv2json). 

TODO : using Aggregator (2)

###Task #2:
Compute the following statistics:
For each category find median session duration

TODO : use UDAF

For each category find # of unique users spending less than 1 min, 1 to 5 mins and more than 5 mins

####Solution:

src/menloex2[].scala set of scripts which basically create a UDF for subtraction and a series of Window functions to work with comparing the aggregated time differences (sessions) with each desired time window.

For each category find top 10 products ranked by time spent by users on product pages - this may require different type of sessions. For this particular task, session lasts until the user is looking at particular product. When particular user switches to another product the new session starts.

####Solution:

src/menloex2-ranking.scala basically aggregates by product the DataFrame created on the previous exercise and uses sum for ranking.

## General notes:
Ideally tasks should be implemented using pure SQL on top of Spark DataFrame API.
Spark version 2.2 or higher
README file in the project can be a plus 
Example of the input file

## References

https://jaceklaskowski.gitbooks.io/mastering-spark-sql/spark-sql-functions-windows.html
