En este proyecto se usan 5 módulos:

-Servidor Eureka (eurekaserver) 
-API Gateway (gateway)
-Microservicios de Usuarios (usuarios) (URL:http://localhost:8080/usuarios)
-Microservicios de Reservas (reservas)
-Microservicios de Comentarios (comentarios)

Se opta a la puntuación máxima (4 ptos)

Ejemplos de consultas:
MICROSERVICIOS USUARIOS: (En caso de que haya nombres y contraseñas iguales se coge el que primero se encuentra)
-crearUsuario: URL:http://localhost:8080/usuarios/registrar (POST)
	       RequestBody:
		{
    		"nombre":"Paula Sotelo",
    		"correo_electronico":"correo@gmail.com",
    		"direccion":"Cangas n38",
    		"contrasena":"1234"
		}
-actualizarUsuario: URL:http://localhost:8080/usuarios/registrar (PUT)
		RequestBody:
		{
    		"usuarioId":4,
    		"nombre":"Paula Sotelo",
    		"correo_electronico":"correo1@gmail.com",
    		"direccion":"Cangas n39",
    		"contrasena":"1234"
		}
-eliminarUsuario: URL:http://localhost:8080/usuarios (DELETE)
		RequestBody:
		{
    		"nombre":"Paula Sotelo",
    		"contrasena":"1234"
		}
-validarUsuario: URL:http://localhost:8080/usuarios/validar (POST)
		RequestBody:
		{
    		"nombre":"Paula Sotelo",
    		"contrasena":"1234"
		}
-obtenerUsuarioPorId: URL:http://localhost:8080/usuarios/info/id/8 (GET)
-obtenerInfoUsuarioPorNombre: URL:http://localhost:8080/usuarios/info/nombre/Juan Pérez (GET)
-checkIfExist: URL:http://localhost:8080/usuarios/checkIfExist/1 (GET)

MICROSERVICIOS RESERVAS:
HABITACIONES:
-crearHabitacion: URL:http://localhost:8080/reservas/habitacion/Juan Perez-clave123 (POST)
		RequestBody:
		{
    		"numeroHabitacion":121,
    		"tipo":"Doble",
    		"precio":20.34,
    		"idHotel":20
		}
-actualizarHabitacion: URL:http://localhost:8080/reservas/habitacion/Juan Perez-clave123 (PATCH)
		RequestBody:
		{
    		"id":7,
    		"numeroHabitacion":121,
    		"tipo":"Doble",
    		"precio":20.34,
    		"idHotel":2,
    		"disponible":false
		}
-eliminarHabitacion : URL:http://localhost:8080/reservas/habitacion/7:Juan Perez-clave123 (DELETE)

HOTELES:
-crearHotel: URL: http://localhost:8080/reservas/hotel/Juan Perez-clave123 (POST)
		RequestBody:
		{

  		 "nombre":"Hotel F",
    		"direccion":"Cangas"

		}

-actualizarHotel:URL: http://localhost:8080/reservas/hotel/Juan Perez-clave123 (PATCH)
		RequestBody:
		{

    		"id":5,
    		"nombre":"Hotel B",
    		"direccion":"Cangas do Morrazo"

		}
-eliminarHotel: URL:http://localhost:8080/reservas/hotel/5:Juan Perez-clave123 (DELETE)
-obtenerIdApartirNombre: URL:http://localhost:8080/reservas/hotel/id/Hotel B:Juan Perez-clave123 (POST) 
		(En caso de que haya mas de un hotel con el mismo nombre devuelve el primero que encuentre)
-obtenerNombreAPartirId: URL:http://localhost:8080/reservas/hotel/nombre/6:Juan Perez-clave123 (POST)

RESERVAS:
-crearReserva URL:http://localhost:8080/reservas/Juan Perez-clave123 (POST)
		RequestBody:
		{

    		"fechaInicio":"2024-07-08",
    		"fechaFin":"2024-07-15",
    		"habitacionId":1
		}
-cambiarEstado: URL:http://localhost:8080/reservas/Juan Perez-clave123 (PATCH)
		RequestBody:
		{
    		"reservaId":8,
    		"estado":"Pendiente"

		}
-listarReservasUsuarios:URL:http://localhost:8080/reservas/Juan Perez-clave123 (GET)
-listarReservasSegunEstado: URL:http://localhost:8080/reservas/Confirmada/Juan Perez-clave123 (GET)
-checkReserva: URL:http://localhost:8080/reservas/check/1-1-1 (GET)

MICROSERVICIOS COMENTARIOS: el introducir localhost:8080/graphiql se ejecuta en el path comentarios http://localhost:8703/graphiql?path=/comentarios
-crearComentario: 
 mutation{
  crearComentario(comentario:{
    nombreHotel:"Hotel A",
    reservaId:1,
    comentario:"Mala experiencia",
    puntuacion:2.5
  },username:"Juan Perez",
  password:"clave123"){
    nombreHotel
    puntuacion
    comentario
    reservaId
  }
 }

-eliminarComentarios:
 mutation{
  eliminarComentarios
 }
-eliminarComentarioDeUsuario
 mutation{
 eliminarComentarioDeUsuario(idComentario:"6649d926ed95cd4d9233722f",
  username:"Juan Perez",
	password:"clave123")
 }

-listarComentariosHotel
 query{
  listarComentariosHotel(nombreHotel:"Hotel A",
  username:"Juan Perez",
  password:"clave123"){
    nombreHotel
    puntuacion
    reservaId
    comentario
  }
 }
-listarComentariosUsuario
 query{
  listarComentariosUsuario(username:"Juan Perez",
  password:"clave123"){
    comentario
    nombreHotel
    reservaId
    puntuacion
  }
 }
-mostrarComentarioUsuarioReserva:
 query{
  mostrarComentarioUsuarioReserva(idReserva:4,
  username:"Juan Perez",
  password:"clave123"){
    nombreHotel
    puntuacion
    comentario
    reservaId
  }
  
}
-puntuacionMediaHotel: 
 query{
  puntuacionMediaHotel(nombreHotel:"Hotel A",
  username:"Juan Perez",
  password:"clave123")
  
 }
-puntuacionesMediasUsuario:
 query{
  puntuacionesMediasUsuario(username:"Juan Perez",
  password:"clave123")
  
 }
