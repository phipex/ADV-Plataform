README: jhipster-raspb_cab

Instalar Java 8

	Actualizar y descargar paquete:
		
		sudo apt-get update
		sudo apt-get install oracle-java8-installer -y

	Comprobar versión:
		
		java -version

Configurar variable del sistema JAVA-HOME:	

	Al ejecutar el siguiente comndo se despliega la ruta de Java: /usr/lib/jvm/java-8-oracle/jre/bin/java

		update-alternatives --config java	

	Edite el archivo /etc/environment, agregando la siguiente línea:

		JAVA_HOME="/usr/lib/jvm/java-8-oracle/jre"

	Guardar y salir. Luego, editar ~/.bashrc agregando las líneas:

		JAVA_HOME=/usr/lib/jvm/java-8-oracle/jre
		export JAVA_HOME
		PATH=$JAVA_HOME/bin:$PATH
		export PATH

	Guardar, salir y recargar archivo anterior, así:

		source ~/.bashrc

	Para validar la variable:

		echo $JAVA_HOME
	
	Se debe leer la ruta a Java.

Instalar Tomcat 8.5

	En la carpeta /opt, descargar Tomcat del sitio oficial https://tomcat.apache.org/download-80.cgi#8.5.16.

	Ir a la carpeta y descomprimir archivo,

		cd /opt 
		tar xzvf apache-tomcat-8.5.16.tar.gz

	Renombrar la carpeta descomprimida, “tomcat”:

		mv apache-tomcat-8.5.16 tomcat

	Cambiar propietario y hacer todos los archivos ejecutables. Ejecutar los siguientes comandos:

		chown -hR pi:pi tomcat
		chmod +x tomcat/bin/*

	Definir variable del sistema $CATALINA_HOME, que apunta a la ruta del Tomcat. En el archivo ~/.bashrc adicionar:

		export CATALINA_HOME=/opt/tomcat
	
	Y recargar archivo:

		source ~/.bashrc

Probar Tomcat

	En $CATALINA_HOME/bin el script startup.sh inicia el Tomcat. Ejecutandolo:

		$CATALINA_HOME/bin/startup.sh

	Se puede observar en consola la salida siguiente:

		Using CATALINA_BASE:   /opt/tomcat
		Using CATALINA_HOME:   /opt/tomcat
		Using CATALINA_TMPDIR: /opt/tomcat/temp
		Using JRE_HOME:        /usr/lib/jvm/java-8-oracle/jre
		Using CLASSPATH:       /opt/tomcat/bin/bootstrap.jar:/opt/tomcat/bin/tomcat-juli.jar
		Tomcat started.

	Asegurarse de que la última línea sea, “Tomcat started.”, lo que significa que está bien.

	Tomcat usa el puerto 8080, ingresar a la siguiente URL para validar su funcionamiento: localhost:8080

	Para desactivarlo, ejecutar el script shutdown.sh,

		$CATALINA_HOME/bin/shutdown.sh

Crear un systemd service

	Con el servidor apagado, en la carpeta systemd system, crear un nuevo archivo:

		sudo nano /etc/systemd/system/tomcat.service

	Adicionar la siguiente configuración:

		[Unit]
		Description=Apache Tomcat 8 Servlet Container
		After=syslog.target network.target

		[Service]
		User=root
		Group=root
		Type=forking
		Environment=CATALINA_PID=/opt/tomcat/tomcat.pid
		Environment=CATALINA_HOME=/opt/tomcat
		Environment=CATALINA_BASE=/opt/tomcat
		ExecStart=/opt/tomcat/bin/startup.sh
		ExecStop=/opt/tomcat/bin/shutdown.sh
		Restart=on-failure

		[Install]
		WantedBy=multi-user.target

	Guardar y salir. Ahora, usar systemd para iniciare el Tomcat service:

		systemctl daemon-reload

		systemctl start tomcat
		systemctl enable tomcat

	Ahora Tomcat está corriendo en el puerto 8080.

Configurar usuarios

	El siguiente paso es necesario para acceder a la consola de administración de Tomcat. Abrimos el siguiente archivo:

		sudo nano /opt/tomcat/conf/tomcat-users.xml

	Bajo la línea 43, pegar el siguiente contenido:

		<role rolename="manager-gui"/>
		<user username="admin" password="password" roles="manager-gui,admin-gui"/>

	Editar el archivo context.xml:

		sudo nano /opt/tomcat/webapps/manager/META-INF/context.xml
	
	Here, comment the following lines:

		<Context antiResourceLocking="false" privileged="true" >
		<!--  <Valve className="org.apache.catalina.valves.RemoteAddrValve"
		         allow="127\.\d+\.\d+\.\d+|::1|0:0:0:0:0:0:0:1" /> -->
		</Context>
	
	Guardar, salir y luego, reiniciar Tomcat:

		systemctl restart tomcat		

MySQL 5.5

	Instalar MySQL:

		sudo apt-get install mysql-server

	Instalar el entorno gráfico MySQL Workbench:	

		sudo apt-get install mysql-workbench


Nota. 
	Se debe crear base de datos en MySQL.
	Se debe configurar user/password en el proyecto JHipster antes de su ejecución.