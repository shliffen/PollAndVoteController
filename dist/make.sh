#!/usr/bin/env bash

mvn -f ../pom.xml clean install

cp ../target/QuickPoll-1.0.0-SNAPSHOT.jar ../dist/QuickPoll.jar

sleep 10
