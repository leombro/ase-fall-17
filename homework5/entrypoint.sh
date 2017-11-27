#!/bin/bash

if [ $# -eq 0 ]; then
	/usr/games/fortune | /usr/games/cowthink
else
	/usr/games/cowthink "$@"
fi
	
