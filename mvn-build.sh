#!/bin/bash

echo 'please be patient while Maven downloads dependencies (~45 seconds)'
mvn -q clean package -Dmaven.test.skip &
./loading.sh $!