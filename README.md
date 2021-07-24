# vozyBackendTest

Login,micro servicio con SpringBoot, protegido por jwt y Spring Security, mongodb, y codigo analizado por cobertura Maven Plugin.

##Sistema construido con:

1.- java 1.8
2.- Maven 4
3.- SpringBoot (para crear el micro servicio)
4.- SpringBoot Security
5.- JWT
4.- base de datos mongodb
5.- Docker
6.- Cobertura (% codigo cubierto por pruebas unitarias)

##Instrucciones de uso

1.- Bajar fuentes desde git.

2.- Ejecutar el comando Maven (compila y empaqueta):
		mvn clean package
		
3.- Construir el contenedor
		docker build -t srm/vozybackendtest .
			
4.- Publica el contenedor
		docker run --publish 8080:8080 srm/vozybackendtest

## Servicios publicados
la app escucha en el puerto 8080 

###GET
http://<IP-contenedor>:8080/login
	Body: json
	Ejemplo:
	{
    "username": "sebastian",
    "password": "123"
	}

###POST
http://<IP-contenedor>:8080/singin

	Body: json
		Ejemplo:
	{
	    "userName": "sebastian",
	    "password": "123",
	    "correo": "sebastian",
	    "activo": "true"
	}

###PUT (Protegido por contraseña)
http://<IP-contenedor>:8080/edituser
	Autorization:"Bearer Token"
	Body: json
		Ejemplo:
	{
	    "userName": "sebastian",
	    "password": "123",
	    "correo": "sebastian",
	    "activo": "true"
	}


###DELETE (Protegido por contraseña)
http://<IP-contenedor>:8080/deleteuser
Autorization:"Bearer Token"
	Body: json
		Ejemplo:
	{
	    "userName": "sebastian",
	    "password": "123",
	    "correo": "sebastian",
	    "activo": "true"
	}




