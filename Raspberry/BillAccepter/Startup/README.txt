README: Startup billetero

Ubicar los siguientes script en el escritorio de la raspberry: /home/pi/Desktop

	"startBill.sh"
	"stopBill.sh"

Para iniciar el billetero con el sistema, instalar el archivo "billAccepter" en la ruta: /etc/init.d/ y asignar permisos de ejecución.
	
	Comando: 	sudo cp billAccepter /etc/init.d/
				sudo chmod +x billAccepter

Realizar prueba del script:
	
	Comando:	sudo ./billAccepter start -> Verificar salida: $Startting ...
				sudo ./billAccepter stop -> Verificar salida: $Shutting down ...

Luego, crear un enlace simbólico en la carpeta correspondiente al runlevel del arranque del sistema. En primera instancia se realizó para el runlevel 5, en la ruta /etc/rc5.d (revisar y asignar runlevel optimo)

	Comando:	sudo ln -s  /etc/init.d/billAccepter /etc/rc5.d/S98billAccepter