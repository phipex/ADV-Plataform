README: CodeLector

1. Instalar las siguientes librerías:

	$ sudo apt-get install libusb-1.0-0
	$ sudo apt-get install libusb-1.0-0-dev

2. Conectar el billetero e identificarlo con el comando:

	lsusb
	
	Puerto USB: Bus 001 Device 002: ID 0c2e:0be1 Metrologic Instruments

3. Extraer iManufacturer, idProduct, idVendor, EndpointAddress de puerto USB con el comando:

	lsusb -vd 0c2e:0be1

	idVendor           0x0c2e Metrologic Instruments
    idProduct          0x0be1 
	iManufacturer           1
	bEndpointAddress     0x84  EP 4 IN

4. Insertar la información en las macros definidas en bc.c y expuestas a continuación:

	#define IMANUFACTURER	1
	#define ID_PRODUCT		0x0be1
	#define ID_VENDOR		0x0c2e
	#define ENDPOINT_IN		0x84