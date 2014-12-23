Summary
=================
This is a sample project to demonstrate uses of testing, mvn and nullable annotations. It implements a simple problem that searches the shortest path from one city to another given a directed graph. It also calculates distance between cities and other useful information.

To install the project
------------ 

mvn install

Execute
------------

Following will return shortest path between A and B given the map "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7"  

mvn exec:java -Dexec.args="AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7 A B"

