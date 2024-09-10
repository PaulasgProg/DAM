CREATE SCHEMA objetos;
CREATE TYPE objetos.Persona AS(
	nombre VARCHAR(255),
	edad INT
);
CREATE TYPE objetos.Jugador AS(
	dorsal INT,
	posicion VARCHAR(255),
	altura DECIMAL(3,2)
);
CREATE TYPE objetos.Equipo AS(
	nombre VARCHAR(255),
	ciudad VARCHAR(255),
	entrenador objetos.Persona
);


CREATE TABLE objetos.Equipos (
	equipo_id serial PRIMARY KEY,
	equipo_info objetos.Equipo
);
CREATE TABLE objetos.Jugadores (
	jugador_id serial PRIMARY KEY,
	datos_personales objetos.Persona,
	jugador_info objetos.Jugador,
    equipo_id INT REFERENCES objetos.Equipos(equipo_id)
);

CREATE TABLE objetos.Partidos(
	partido_id serial PRIMARY KEY,
	fecha DATE,
	equipo_local INT REFERENCES objetos.Equipos(equipo_id),
	equipo_visitante INT REFERENCES objetos.Equipos(equipo_id)
);

INSERT INTO objetos.Jugadores(datos_personales,jugador_info) 
VALUES (ROW('Vinicius JR',23),ROW(7,'Delantero',1.73));

INSERT INTO objetos.Jugadores(datos_personales,jugador_info) 
VALUES (ROW('Luka Modric',38),ROW(19,'Centrocampista',1.74));

INSERT INTO objetos.Jugadores(datos_personales,jugador_info) 
VALUES (ROW('Fede Valverde',25),ROW(15,'Centrocampista',1.81));

INSERT INTO objetos.Jugadores(datos_personales,jugador_info) 
VALUES (ROW('Iago Aspas',36),ROW(10,'Delantero',1.74));

INSERT INTO objetos.Jugadores(datos_personales,jugador_info) 
VALUES (ROW('Unai Simon',26),ROW(1,'Portero',1.90));

INSERT INTO objetos.Jugadores(datos_personales,jugador_info) 
VALUES (ROW('Sergio Ramos',37),ROW(4,'Defensa',1.83));

INSERT INTO objetos.Jugadores(datos_personales,jugador_info) 
VALUES (ROW('Marc-André Ter Stegen',31),ROW(1,'Portero',1.89));

INSERT INTO objetos.Jugadores(datos_personales,jugador_info) 
VALUES (ROW('Yerson Mosquera',23),ROW(2,'Defensa',1.88));

INSERT INTO objetos.Equipos(equipo_info)
VALUES (ROW('Real Madrid CF','Madrid',ROW('Carlo Ancelloti',64)));

INSERT INTO objetos.Equipos(equipo_info)
VALUES (ROW('Celta de Vigo','Vigo',ROW('Rafa Benitez',63)));

INSERT INTO objetos.Equipos(equipo_info)
VALUES (ROW('FC Barcelona','Barcelona',ROW('Xavi Hernandez',44)));

INSERT INTO objetos.Equipos(equipo_info)
VALUES (ROW('Villarreal','Villarreal',ROW('Marcelino Garcia',58)));

INSERT INTO objetos.Equipos(equipo_info)
VALUES (ROW('Athletic Club de Bilbao','Bilbao',ROW('Ernesto Valverde',60)));

INSERT INTO objetos.Partidos(fecha, equipo_local, equipo_visitante)
VALUES ('15-06-2024',2,3);

INSERT INTO objetos.Partidos(fecha, equipo_local, equipo_visitante)
VALUES ('20-07-2024',1,4);

INSERT INTO objetos.Partidos(fecha, equipo_local, equipo_visitante)
VALUES ('05-04-2024',3,5);

INSERT INTO objetos.Partidos(fecha, equipo_local, equipo_visitante)
VALUES ('22-05-2024',2,1);

