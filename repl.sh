#!/usr/bin/env bash
java -cp "$(./gradlew -q printRuntimeClasspath)" \
  dotty.tools.MainGenericRunner -usejavacp