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

Design creates an aggregate of Maps which is accessed by MapAggregate class. The domain objects are stored under com.ichs.samples.trains.imp. They are fairly simple and do not store any domain logic. Factory classes are responsible to generate domain aggregates at different level. These also contain assumptions of the domain. Some of the assumptions are

* Town names are single capital letter selected from alphabet [A,B,C,D,E]
* Roads are directed between towns represented as two letters representing town names and a single digit number representing distance, e.g. AB5 represents a road 
* Routes are separated with a dash "-"
* Maps are defined as a list of roads separated by comma 

