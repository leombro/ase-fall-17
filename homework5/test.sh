#!/bin/bash

TAG=$((set -x; docker build . )| tee /dev/tty)
sleep 1
(set -x; docker tag ${TAG##* } oleombruni-cowthink)
sleep 1
docker run oleombruni-cowthink
sleep 1
docker run oleombruni-cowthink cogito ergo cow
