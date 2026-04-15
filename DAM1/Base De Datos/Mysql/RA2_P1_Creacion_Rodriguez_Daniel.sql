DROP DATABASE IF EXISTS gestion_universidad;
CREATE DATABASE gestion_universidad;
USE gestion_universidad;
SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE facultades(
	id_facultad SMALLINT AUTO_INCREMENT,
    codigo CHAR(4) UNIQUE NOT NULL,
    nombre VARCHAR(30) UNIQUE NOT NULL,
    id_decano SMALLINT DEFAULT(NULL),
    CONSTRAINT pk_facultades PRIMARY KEY (id_facultad),
    CONSTRAINT fk_facultades_profesores FOREIGN KEY (id_decano)
    	REFERENCES profesores(id_profesor)
        ON DELETE RESTRICT ON UPDATE CASCADE
);
CREATE TABLE asignaturas(
	id_asignatura SMALLINT AUTO_INCREMENT,
    codigo_asig VARCHAR(10) UNIQUE NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    creditos SMALLINT DEFAULT(6),
    CONSTRAINT pk_asignaturas PRIMARY KEY (id_asignatura),
    CONSTRAINT creditos_check CHECK (creditos >= 3)
);
CREATE TABLE profesores(
	id_profesor SMALLINT AUTO_INCREMENT,
    nif CHAR(9) UNIQUE NOT NULL,
    nombre_completo VARCHAR(50) NOT NULL,
    salario DOUBLE DEFAULT(2000.00),
    id_facultad SMALLINT NOT NULL,
    CONSTRAINT pk_profesores PRIMARY KEY (id_profesor),
    CONSTRAINT nif_check CHECK (REGEXP_LIKE(nif, '[0-9]{8}[A-Za-z]')),
    CONSTRAINT salario_check CHECK (salario > 0),
    CONSTRAINT fk_profesores_facultades FOREIGN KEY (id_facultad)
    	REFERENCES facultades(id_facultad)
        ON DELETE RESTRICT ON UPDATE CASCADE
);
CREATE TABLE grados(
	id_grado SMALLINT AUTO_INCREMENT,
    nombre VARCHAR(20) UNIQUE NOT NULL,
    id_facultad SMALLINT NOT NULL,
    CONSTRAINT pk_grados PRIMARY KEY (id_grado),
    CONSTRAINT fk_grados_facultades FOREIGN KEY (id_facultad)
		REFERENCES facultades(id_facultad)
        ON DELETE RESTRICT ON UPDATE CASCADE
);
CREATE TABLE imparten(
	id_profesor SMALLINT,
    id_asignatura SMALLINT,
    tipo_grupo ENUM('TEORIA','PRACTICA') DEFAULT('TEORIA'),
    CONSTRAINT pf_imparten PRIMARY KEY (id_profesor,id_asignatura),
    CONSTRAINT fk_imparten_profesores FOREIGN KEY (id_profesor)
		REFERENCES profesores(id_profesor)
        ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_imparten_asignatura FOREIGN KEY (id_asignatura)
		REFERENCES asignaturas(id_asignatura)
        ON DELETE CASCADE ON UPDATE CASCADE
);
SET FOREIGN_KEY_CHECKS=1;

CREATE VIEW v_cuadro_docente AS 
SELECT 
	profesores.nombre_completo AS profesor,
	profesores.nif AS nif_profesor,
	asignaturas.nombre AS asignatura,
	imparten.tipo_grupo AS modalidad,
	facultades.nombre AS facultad_origen 
	FROM profesores
	JOIN facultades USING(id_facultad)
	JOIN imparten USING(id_profesor)
	JOIN asignaturas USING(id_asignatura);

CREATE VIEW v_resumen_facultades AS
SELECT
	facultades.nombre AS facultad,
	facultades.codigo AS codigo_facultad,
	COUNT(profesores.id_profesor) AS num_profesores,
	SUM(profesores.salario) AS masa_salarial,
	ROUND(AVG(profesores.salario),2) AS salario_medio
	FROM profesores
	JOIN facultades USING(id_facultad)
	GROUP BY facultades.nombre;
