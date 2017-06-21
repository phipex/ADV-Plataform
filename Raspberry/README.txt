README

- Se debe configurar permisos del puerto, al cual está conectado el billetero, para el usuario en linux.
	
	Comando:
		$ sudo chown user.user ttyUSB0

- Para proceder con la construcción del ejecutable, se deben instalar las librerias: 
	libqt5webkit5
	libqt5websockets5-dev
	libqt5sql5
	libqt5network5
	qt5-default
	qt5-qmake
	ibqt5widgets5
	
	Comando:
		$ sudo apt-get install libqt5webkit5

- Crear archivo 10-ruleta.rules (en /etc/udev/rules.d) que contenga las siguientes lineas: 

KERNEL=="ttyUSB*", SUBSYSTEMS=="usb", ATTRS{idVendor}=="1a86", ATTRS{idProduct}=="7523", SYMLINK+="sas/sas%n", OWNER="pi", GROUP="pi", MODE="0666"
KERNEL=="ttyUSB*", SUBSYSTEMS=="usb", ATTRS{idVendor}=="067b", ATTRS{idProduct}=="2303", SYMLINK+="ies/ttyBill%n"
KERNEL=="ttyUSB*", SUBSYSTEMS=="usb", ATTRS{idVendor}=="1a86", ATTRS{idProduct}=="6364", SYMLINK+="ies/ttySAS%n"
KERNEL=="ttyUSB*", SUBSYSTEMS=="usb", ATTRS{idVendor}=="0403", ATTRS{idProduct}=="6001", SYMLINK+="ies/ttySAS%n"				
