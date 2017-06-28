README

1. Configurar permisos del puerto al cual está conectado el billetero para usuario de linux.
	
	Comando:
		$ sudo chown owner.group puerto
	Ejm:
		$ sudo chown betancur343.betancur343 ttyUSB0	

	Nota. Cada vez que se apaga y/o reinicia el pc, este tipo de permiso por defecto se asigna al root, por lo cual y para evitar relizarlo repetidas veces es necesrio:

	- Crear un archivo con el nombre "10-ruleta.rules" en la ruta "/etc/udev/rules.d/". Adicionar las siguientes lineas. Luego, configurar las propiedades OWNER y GROUP según sea el caso (en esta ocasión, se asigna "pi" con la intención de correr el ejecutable en la raspberry pi):

	KERNEL=="ttyUSB*", SUBSYSTEMS=="usb", ATTRS{idVendor}=="1a86", ATTRS{idProduct}=="7523", SYMLINK+="sas/sas%n", OWNER="pi", GROUP="pi", MODE="0666"
	KERNEL=="ttyUSB*", SUBSYSTEMS=="usb", ATTRS{idVendor}=="067b", ATTRS{idProduct}=="2303", SYMLINK+="ies/ttyBill%n"
	KERNEL=="ttyUSB*", SUBSYSTEMS=="usb", ATTRS{idVendor}=="1a86", ATTRS{idProduct}=="6364", SYMLINK+="ies/ttySAS%n"
	KERNEL=="ttyUSB*", SUBSYSTEMS=="usb", ATTRS{idVendor}=="0403", ATTRS{idProduct}=="6001", SYMLINK+="ies/ttySAS%n"

2. Instalar las siguientes librerias: 

	libqt5webkit5
	libqt5websockets5-dev
	libqt5sql5
	libqt5network5
	qt5-default
	qt5-qmake
	libqt5widgets5
	
	Comando:
		$ sudo apt-get install libreria

	Ejm:
		$ sudo apt-get install libqt5webkit5		
