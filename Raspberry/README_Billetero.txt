README

1. Crear un archivo con el nombre "10-ruleta.rules" en la ruta "/etc/udev/rules.d/". Adicionar las siguientes lineas de texto, modificando las propiedades OWNER y GROUP según la máquina donde se instale el ejecutable. Para este caso, se asigna al OWNER y GROUP "pi".

	KERNEL=="ttyUSB*", SUBSYSTEMS=="usb", ATTRS{idVendor}=="1a86", ATTRS{idProduct}=="7523", SYMLINK+="sas/sas%n", OWNER="pi", GROUP="pi", MODE="0666"
	KERNEL=="ttyUSB*", SUBSYSTEMS=="usb", ATTRS{idVendor}=="067b", ATTRS{idProduct}=="2303", SYMLINK+="ies/ttyBill%n", OWNER="pi", GROUP="pi", MODE="0666"
	KERNEL=="ttyUSB*", SUBSYSTEMS=="usb", ATTRS{idVendor}=="1a86", ATTRS{idProduct}=="6364", SYMLINK+="ies/ttySAS%n", OWNER="pi", GROUP="pi", MODE="0666"
	KERNEL=="ttyUSB*", SUBSYSTEMS=="usb", ATTRS{idVendor}=="0403", ATTRS{idProduct}=="6001", SYMLINK+="ies/ttySAS%n", OWNER="pi", GROUP="pi", MODE="0666"


	Nota. 
		El OWNER debe ser diferente al ROOT. 
		El OWNER no necesariamente es igual al GROUP (como en el caso anterior). Estas propiedades dependen únicamente del sistema operativo.
		Para asignar permisos manualmente:	
			$ sudo chown owner.group puerto		
			Ejm:	
				$ sudo chown betancur343.betancur343 ttyUSB0		

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
