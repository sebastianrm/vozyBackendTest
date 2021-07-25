# vozyBackendTest

* Login
* micro servicio con SpringBoot
* Protegido por jwt y Spring Security
* mongodb
* codigo analizado por cobertura Maven Plugin.
* cada usuario almacenado en la base dedatos, tiene su contraseña codificada con **bcryptEncoder**




## Sistema construido con:

1. java 1.8
2. Maven 4
3. SpringBoot (para crear el micro servicio)
4. SpringBoot Security
5. JWT
4. base de datos mongodb
5. Docker
6. Cobertura (% codigo cubierto por pruebas unitarias)

## Instrucciones de uso

1. Bajar fuentes desde git.

2. Ejecutar el comando Maven (compila y empaqueta):

		mvn clean package
		
3. Construir el contenedor

		docker build -t srm/vozybackendtest .
			
4. Publica el contenedor

		docker run --publish 8080:8080 srm/vozybackendtest

## Servicios publicados
la app escucha en el puerto 8080 

### GET - login
http://< IP-contenedor >:8080/login

	Body: json
	Ejemplo:
	{
    "username": "sebastian",
    "password": "123"
	}

### POST - singin o registrar
http://< IP-contenedor >:8080/singin

	Body: json
		Ejemplo:
	{
	    "userName": "sebastian",
	    "password": "123",
	    "correo": "sebastian",
	    "activo": "true"
	}

### PUT - edituser (Protegido por contraseña)
http://< IP-contenedor >:8080/edituser

	Autorization:"Bearer Token"
	Body: json
		Ejemplo:
	{
	    "userName": "sebastian",
	    "password": "123",
	    "correo": "sebastian",
	    "activo": "true"
	}


### DELETE - deleteuser (Protegido por contraseña)
http://< IP-contenedor >:8080/deleteuser

	Autorization:"Bearer Token"
	Body: json
		Ejemplo:
	{
	    "userName": "sebastian",
	    "password": "123",
	    "correo": "sebastian",
	    "activo": "true"
	}