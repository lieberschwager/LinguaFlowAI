#!/usr/bin/env sh

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Set default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS=""

APP_NAME="Gradle"
APP_BASE_NAME=$(basename "$0")

# Add default JVM options
JAVA_OPTS="${JAVA_OPTS:-$DEFAULT_JVM_OPTS}"

CLASSPATH=$(find "$APP_HOME/lib" -name '*.jar' | tr '\n' ':')

exec java $JAVA_OPTS -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"