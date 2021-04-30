@echo off

mvn -f ../pom.xml clean install

copy ..\target\QuickPoll-1.0.0-SNAPSHOT.jar ..\dist\QuickPoll.jar
