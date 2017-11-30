#!/bin/sh

unzip $INPUT_CODE # unzips the artifact passed in as an input (to be left here)
cd doodle
mvn package
