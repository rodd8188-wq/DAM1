 --
 -- Ejercicio3	····································
 --
DROP DATABASE IF EXISTS gestion_proyectos;
CREATE DATABASE gestion_proyectos;
USE gestion_proyectos;
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS empleados;
CREATE TABLE empleados(
	id_empleado SMALLINT UNSIGNED AUTO_INCREMENT,
    dni CHAR(9) UNIQUE NOT NULL ,
    salario DOUBLE DEFAULT(1200.00),
    estado ENUM('ACTIVO','INACTIVO') DEFAULT('ACTIVO'),
    CONSTRAINT `check_dni` CHECK (REGEXP_LIKE(dni, '[1-9]{8}[A-Z]{1}')),
    CONSTRAINT `pk_empleados` PRIMARY KEY (id_empleado)
);
DROP TABLE IF EXISTS departamentos;
CREATE TABLE departamentos(
	id_departamento SMALLINT UNSIGNED AUTO_INCREMENT,
    codigo_dpto CHAR(5) UNIQUE NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    presupuesto DOUBLE UNIQUE NOT NULL,
    CONSTRAINT pk_departamento PRIMARY KEY (id_departamento),
    CONSTRAINT check_presupuesto CHECK(presupuesto>=0)
);
DROP TABLE IF EXISTS proyectos;
CREATE TABLE proyectos(
	id_proyecto SMALLINT UNSIGNED AUTO_INCREMENT,
    nombre VARCHAR(20) UNIQUE NOT NULL,
    id_departamento SMALLINT UNSIGNED NOT NULL,
    fecha_inicio TIMESTAMP NOT NULL,
    fecha_fin TIMESTAMP,
    CONSTRAINT check_fecha_fin CHECK(fecha_fin > fecha_inicio OR fecha_fin IS NULL),
    CONSTRAINT pk_proyecto PRIMARY KEY (id_proyecto),
    CONSTRAINT fk_proyectos_departamentos FOREIGN KEY (id_departamento)
		REFERENCES departamentos(id_departamento)
        ON DELETE RESTRICT ON UPDATE CASCADE
);
DROP TABLE IF EXISTS asignaciones;
CREATE TABLE asignaciones(
	id_empleado SMALLINT UNSIGNED,
    id_proyecto SMALLINT UNSIGNED,
    horas_asignadas SMALLINT UNSIGNED DEFAULT 0,
    CONSTRAINT pk_asignaciones PRIMARY KEY (id_empleado,id_proyecto),
    CONSTRAINT fk_asignaciones_empleados FOREIGN KEY (id_empleado)
		REFERENCES empleados(id_empleado)
        ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT fk_asignaciones_proyectos FOREIGN KEY (id_proyecto)
		REFERENCES proyectos(id_proyecto)
        ON DELETE RESTRICT ON UPDATE CASCADE
);
SET FOREIGN_KEY_CHECKS=1;