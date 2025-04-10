CREATE SCHEMA objetos;

CREATE TYPE objetos.Persona AS(
	nombre VARCHAR(255),
	edad INT
);
CREATE TYPE objetos.Paciente AS(
	numero_historia VARCHAR(255),
	grupo_sanguineo VARCHAR(20)
);

CREATE TYPE objetos.ExamenMedico AS(
	nombre_examen VARCHAR(255),
	fecha_realizacion DATE,
	resultado TEXT
);
CREATE TYPE objetos.Medico AS(
	codigo_medico VARCHAR(255),
	especialidad VARCHAR(255)
);

CREATE TABLE objetos.Medicos (
	medico_id serial PRIMARY KEY,
	datos_personales objetos.Persona,
	medico_info objetos.Medico
);

CREATE TABLE objetos.Pacientes(
	paciente_id serial PRIMARY KEY,
	datos_personales objetos.Persona,
	paciente_info objetos.Paciente
);

CREATE TABLE objetos.CitasMedicas(
	cita_id serial PRIMARY KEY,
	fecha_hora TIMESTAMP,
	medico_id INT REFERENCES objetos.Medicos(medico_id),
	paciente_id INT REFERENCES objetos.Pacientes(paciente_id)
);

CREATE TABLE objetos.ExamenesMedicos(
	examen_id serial PRIMARY KEY,
	paciente_id INT REFERENCES objetos.Pacientes(paciente_id),
	examen_info objetos.ExamenMedico
);

-- Insertar datos de pacientes
INSERT INTO objetos.Pacientes (datos_personales, paciente_info)
VALUES (ROW('Juan Pérez', 45), ROW('PH001', 'A+'));

INSERT INTO objetos.Pacientes (datos_personales, paciente_info)
VALUES (ROW('María Gómez', 32), ROW('PH002', 'B-'));

-- Insertar datos de médicos
INSERT INTO objetos.Medicos (datos_personales, medico_info)
VALUES (ROW('Dr. Carlos Rodríguez', 50), ROW('M001', 'Cirugía'));

INSERT INTO objetos.Medicos (datos_personales, medico_info)
VALUES (ROW('Dra. Ana Martínez', 40), ROW('M002', 'Pediatría'));

-- Insertar citas médicas
INSERT INTO objetos.CitasMedicas (fecha_hora, paciente_id, medico_id)
VALUES ('2023-10-15 10:00:00', 1, 1);

INSERT INTO objetos.CitasMedicas (fecha_hora, paciente_id, medico_id)
VALUES ('2023-10-16 15:30:00', 2, 2);

-- Insertar datos de exámenes médicos
INSERT INTO objetos.ExamenesMedicos (paciente_id, examen_info)
VALUES (1, ROW('Análisis de Sangre', '2023-10-20', 'Resultados dentro del rango normal'));

INSERT INTO objetos.ExamenesMedicos (paciente_id, examen_info)
VALUES (2, ROW('Radiografía de Tórax', '2023-10-18', 'Ninguna anormalidad detectada'));