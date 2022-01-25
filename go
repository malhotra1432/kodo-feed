#!/usr/bin/env bash

set -eu -o pipefail

process_command_chain() {
  if [ $# -gt 0 ] ; then
    $0 "$@"
  fi
}

task_clean(){
./gradlew clean

process_command_chain "$@"
}

task_format() {
  ./gradlew goJF

  process_command_chain "$@"
}

task_verify_format() {
  ./gradlew verGJF

  process_command_chain "$@"
}

task_build(){
./gradlew build

process_command_chain "$@"
}

task_test() {
  ./gradlew test

  process_command_chain "$@"
}

task_integrationTest() {
  ./gradlew integrationTest

  process_command_chain "$@"
}

task_bootRun(){
./gradlew bootRun
}

task_usage(){
    cat <<EOF
Usage $0 COMMAND

commands are:
    clean           - Clean up build artifacts
    format          - Formats the code as per google format plugin
    verify_format   - Verify whether code is formatted correctly
    build           - Build the JAR
    bootRun         - Run application locally
    go              - List out commands
    test            - Run Unit and Integration Test
    integrationTest - Run Integration Test
EOF
exit 1
}

CMD=${1:-}
shift || true

case "${CMD}" in
    clean) task_clean "$@" ;;
    format) task_format "$@" ;;
    verify_format) task_verify_format "$@" ;;
    build) task_build "$@" ;;
    go) clean build "$@" ;;
    test) task_test "$@" ;;
    integrationTest) task_integrationTest "$@" ;;
    bootRun) task_bootRun "$@" ;;
    *) task_usage ;;
esac
