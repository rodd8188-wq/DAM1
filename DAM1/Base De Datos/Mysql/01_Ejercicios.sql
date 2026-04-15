 -- BasesDeDatos/Ejemplos/01_CrecionBBDD/01_Ejercicios.md
 
 --
 -- Ejercicio1  ····································
 --
DROP DATABASE IF EXISTS ejercicio1;
CREATE DATABASE ejercicio1;
USE ejercicio1;

DROP TABLE IF EXISTS vehiculo;
CREATE TABLE vehiculo (
    id_vehiculo INT AUTO_INCREMENT,
    matricula VARCHAR(8),
    tipo VARCHAR(50),
    precio DECIMAL(10,2),
    fecha_compra DATE,
    CONSTRAINT pk_vehiculo PRIMARY KEY (id_vehiculo),
    CONSTRAINT chk_precio_es_dinero CHECK (precio > 0),
    CONSTRAINT chk_matricula_comprobar CHECK (REGEXP_LIKE(matricula, '[1-9]{4}-[B-Z^EIOU]{3}')),
    CONSTRAINT uniq_matricula UNIQUE(matricula)
);

 --
 -- Ejercicio2  ····································
 --
DROP DATABASE IF EXISTS ejercicio2;
CREATE DATABASE ejercicio2;
USE ejercicio2;
SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE laboratorio(
	id INT UNSIGNED AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    investigador_principal INT UNSIGNED,
    CONSTRAINT pk_laboratorio PRIMARY KEY (id),
    CONSTRAINT fk_laboratorio_investigador FOREIGN KEY (investigador_principal)
		REFERENCES investigador(id)
        ON DELETE RESTRICT ON UPDATE CASCADE
);
CREATE TABLE investigador (
    id INT UNSIGNED AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    laboratorio INT UNSIGNED,
    CONSTRAINT pk_investigador PRIMARY KEY (id),
    CONSTRAINT fk_investigador_laboratorio FOREIGN KEY (laboratorio)
		REFERENCES laboratorio(id) 
		ON DELETE RESTRICT ON UPDATE CASCADE
);
SET FOREIGN_KEY_CHECKS=1;

 --
 -- Ejercicio3	····································
 --
DROP DATABASE IF EXISTS Ejercicio3;
CREATE DATABASE Ejercicio3;
USE Ejercicio3;
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS empleados;
CREATE TABLE empleados(
	id_empleado SMALLINT UNSIGNED AUTO_INCREMENT,
    dni CHAR(10) UNIQUE NOT NULL ,
    salario DOUBLE DEFAULT(1200.00),
    estado ENUM('ACTIVO','INACTIVO') DEFAULT('ACTIVO'),
    CONSTRAINT check_dni CHECK (REGEXP_LIKE(dni, '[1-9]{9}[A-Z]')),
    CONSTRAINT pk_empleados PRIMARY KEY (id_empleado)
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
DROP TABLE IF EXISTS departamentos;
CREATE TABLE departamentos(
	id_departamento SMALLINT UNSIGNED AUTO_INCREMENT,
    codigo_dpto CHAR(5) UNIQUE NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    presupuesto DOUBLE UNIQUE,
    CONSTRAINT pk_departamento PRIMARY KEY (id_departamento),
    CONSTRAINT check_presupuesto CHECK(presupuesto>=0)
);

SET FOREIGN_KEY_CHECKS=1;