create database if not exists LibreriaPaula;
use LibreriaPaula;

create table libro (idLibro int primary key AUTO_INCREMENT, codigo varchar(20), titulo varchar(200), autor varchar(300));
create table socio (idCliente int primary key AUTO_INCREMENT, DNI char(9), nombre varchar(200), apellido varchar(200));
create table prestamos (idPres int primary key AUTO_INCREMENT, idLibro int, idCliente int, fecha Date,fechaDev Date, prestado bool, foreign key (idLibro) references libro(idLibro), foreign key (idCliente) references socio(idCliente));

insert into libro(idLibro,codigo, titulo, autor) values (1,'asdfg', 'Pride and Prejudice', 'Jane Austen');
insert into libro(idLibro,codigo, titulo, autor) values (2,'asrwqeg', 'Persuation', 'Jane Austen');
insert into libro(idLibro,codigo, titulo, autor) values (3,'asdfdsa', 'El Quijote', 'Miguel de Cervantes');
insert into socio(idCliente, DNI, nombre, apellido) values (1,'11111111A', 'Ana', 'Fernández');
insert into socio(idCliente, DNI, nombre, apellido) values (2,'22222222B', 'Pablo', 'Rodriguez');

insert into prestamos values (1,1,1,'2013-11-05','2023-11-05', 0);
insert into prestamos values (2,2,1,'2013-02-05',null, 1);
insert into prestamos values (3,3,1,'2013-12-10',null, 1);
insert into prestamos values (4,2,2,'2013-11-05','2023-11-05', 0);
insert into prestamos values (5,3,2,'2013-11-05','2023-11-05', 0);
