DROP DATABASE examen1hiber;
CREATE DATABASE examen1hiber;
USE examen1hiber;

CREATE table municipios(
id_municipio varchar(5) PRIMARY key,
nombre varchar (10),
n_habitantes int);

CREATE TABLE alumnos(
nia int PRIMARY key,
nombre Varchar(15),
apellidos varchar(20),
id_municipio varchar(5),
foreign key(id_municipio) references municipios(id_municipio));

INSERT INTO municipios VALUES ('46000','VALENCIA',800000);
INSERT INTO municipios VALUES ('46120','ALBORAYA',23500);
INSERT INTO municipios VALUES ('46160','LIRIA',24000);
INSERT INTO municipios VALUES ('46900','TORRENTE',81500);

INSERT INTO alumnos VALUES (1, 'Raul', 'Garcia', '46000');
INSERT INTO alumnos VALUES (2, 'Maria', 'Lopez', '46000');
INSERT INTO alumnos VALUES (3, 'Pablo', 'Morales', '46120');
INSERT INTO alumnos VALUES (4, 'Raul', 'Leal', '46160');

SELECT A.nombre, A.apellidos, M.nombre FROM alumnos as A join municipios as M where A.id_municipio = M.id_municipio;

describe alumnos;

select * from alumnos;
describe municipios;
describe alumnos;
