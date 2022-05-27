#!/bin/bash

./mvnw compile

./mvnw clean install

# Set basic jvm parameters
JAVA_OPTS="-Xms256m -Xmx512m"


#
# Start with:
# $ DEBUG=true ./compileAndRunToDoApp.sh
#
# to be able to attach an IDE debugger
#
if [ -n "$DEBUG" ]; then
    JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
    echo "Starting with debug enabled on port 5005"
fi

_RUNJAVA="$JAVA_HOME"/bin/java

$_RUNJAVA $JAVA_OPTS  -jar target/todo-app-manjush.jar --server.port=8080  "$@"
