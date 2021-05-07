#!/bin/bash -e

if [[ $# -gt 0 ]]; then
    echo "Usage: $0"
    exit 1
fi

mvn clean compile assembly:single
java -jar target/dataindexer.jar
