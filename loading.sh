#!/bin/bash

chars="/-\|"

PID=$1

while [ -d /proc/$PID ]
do
  for (( i=0; i<${#chars}; i++ )); do
    sleep 0.25
    echo -en "${chars:$i:1}" "\r"
  done
done