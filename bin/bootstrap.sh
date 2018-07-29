#!/bin/bash

rm -f /tmp/*.pid

echo "=== STARTING HADOOP ==="
$HADOOP_HOME/etc/hadoop/start-all.sh

hdfs dfs -mkdir /user
hdfs dfs -mkdir /user/impala
hdfs dfs -mkdir /user/hive
hdfs dfs -chmod -R 777 /user
hdfs dfs -mkdir /tmp
hdfs dfs -chmod 777 /tmp
#sudo -iu hdfs hdfs dfs -chown impala:impala /user/impala
#sudo -iu hdfs hdfs dfs -chown impala:impala /user/hive

echo "=== VERIFICANDO EXISTENCIA DOS METADADOS ==="
SCHEMA=`$HIVE_HOME/bin/schematool -dbType derby -info | grep "schemaTool completed"`

# Verifica se o schema já está inicializa, caso negativo o inicializa
if [[ ! $SCHEMA ]]; then
  echo "=== CRIANDO METADADOS ==="
  $HIVE_HOME/bin/schematool -dbType derby -initSchema
else
  echo "=== METADADOS JÁ EXISTENTES ==="
fi

echo "=== STARTING HIVE ==="
service hive-server2 start

echo "=== STARTING SSH ==="
service sshd start

echo "=== STARTING ZOOKEEPER ==="
cd $KAFKA_HOME
bin/zookeeper-server-start.sh config/zookeeper.properties &

echo "=== STARTING KAFKA ==="
bin/kafka-server-start.sh config/server.properties &

if [[ $1 == "-bash" ]]; then
  /bin/bash
fi

while true; do sleep 1000; done
