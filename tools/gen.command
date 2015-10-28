#!/bin/bash
cd $(dirname $0)
java -cp jflex/JFlex.jar JFlex.Main -d ../src/main/java/miw/lexical ../src/main/java/miw/lexical/lexical.flex

./byaccj/yacc.macosx -J -v -Jpackage=miw.syntactic -Jsemantic=Object "../src/main/java/miw/syntactic/syntactic.y"
mv Parser.java ../src/main/java/miw/syntactic/Parser.java
mv y.output ../src/main/java/miw/syntactic/y.output
