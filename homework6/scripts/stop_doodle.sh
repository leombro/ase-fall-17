#!/bin/sh

PID=$(pidof java)
kill -9 $PID
