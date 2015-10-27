#!/bin/bash
cd $(dirname $0)
java -cp JFlex.jar JFlex.Main -d ../../src/main/java/miw/lexical ../../src/main/java/miw/lexical/lexical.flex