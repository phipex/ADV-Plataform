#!/bin/bash
#
# Install in Raspberry:	/home/pi/Desktop
# Installed locally in:	/home/betancur343/Escritorio

if [ ! -f /tmp/billAccepter.pid ]; then

    /home/betancur343/Escritorio/BilleteroWebsocket/qtwebsocketserver &> /dev/null & 
    #/home/pi/Desktop/BilleteroWebsocket/qtwebsocketserver &> /dev/null &

    echo $! > /tmp/billAccepter.pid

fi
