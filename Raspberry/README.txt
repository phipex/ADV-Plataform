Notas:

- Para proceder con la costrucción del ejecutable, se deben instalar las siguientes librerias:

	sudo apt-get install libqt5webkit5
						 libqt5websockets5-dev
						 libqt5sql5
						 libqt5network5
						 qt5-default
						 qt5-qmake
						 libqt5widgets5

- Para compilar y crear ejecutable: 
	
	qmake qtwebsocketserver.pro
	make