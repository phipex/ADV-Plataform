#!/bin/bash
#
# Install in: /home/pi/Desktop

if [ -f /tmp/billAccepter.pid ]; then
    
    PID=`cat /tmp/billAccepter.pid`    
    killall -TERM qtwebsocketserver    
    rm /tmp/billAccepter.pid    

fi
