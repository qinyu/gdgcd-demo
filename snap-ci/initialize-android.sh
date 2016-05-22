#!/bin/bash

# raise an error if any command fails!
set -e

# existance of this file indicates that all dependencies were previously installed, and any changes to this file will use a different filename.
INITIALIZATION_FILE="$ANDROID_HOME/.initialized-dependencies-$(git log -n 1 --format=%h -- $0)"

if [ ! -e ${INITIALIZATION_FILE} ]; then
  # fetch and initialize $ANDROID_HOME
  download-android
  # Use the latest android sdk tools
  - echo y | android update sdk --no-ui --all -s --filter tools-preview
  - echo y | android update sdk --no-ui --all -s --filter platform-tools-preview,build-tools-24.0.0-preview,android-N
  - echo y | android update sdk --no-ui --all -s --filter extra-android-support,extra-android-m2repository

  # Specify at least one system image if you want to run emulator tests
  # echo y | android update sdk --no-ui --filter sys-img-armeabi-v7a-android-21 --all > /dev/null

  touch ${INITIALIZATION_FILE}
fi
