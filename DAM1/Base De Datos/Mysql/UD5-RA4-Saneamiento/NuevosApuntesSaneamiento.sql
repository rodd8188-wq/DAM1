USE gha_analytics;
-- ===================================================================================
-- Eliminar duplicados manteniendo el id más bajo
 -- Comparando por el nif y el nombre
-- ===================================================================================
START TRANSACTION;

ROLLBACK;

SET SQL_SAFE_UPDATES = 0;
 
DELETE p1
FROM pacientes p1 JOIN pacientes p2 ON p1.nif = p2.nif
AND UPPER(REPLACE(p1.nombre_completo,' ','')) = UPPER(REPLACE(p2.nombre_completo,' ',''))
WHERE p1.id > p2.id;
-- --------- Otra forma de hacerlo (Sin self join) --------- --
DELETE FROM pacientes
WHERE nif IS NOT NULL
  AND id NOT IN (
      SELECT id_minimo FROM (
          SELECT MIN(id) AS id_minimo
          FROM pacientes
          WHERE nif IS NOT NULL
          GROUP BY nif
      ) AS p_conservar
  );
 
SET SQL_SAFE_UPDATES = 1;
 
COMMIT;
-- ===================================================================================
-- Cambia las comas por puntos del correo de los clientes
-- ===================================================================================
START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

UPDATE clientes
SET email = REPLACE(email, ',', '.')
WHERE email LIKE '%@%,%';

SET SQL_SAFE_UPDATES = 1;

COMMIT;
-- ===================================================================================
-- NIF sin espacios, 8 números y una letra, eliminando o modificando los que no cumplan
-- ===================================================================================
START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;
 
UPDATE pacientes
SET nif = TRIM(nif);
UPDATE pacientes
SET nif = UPPER(nif);
UPDATE pacientes
SET nif = REPLACE(nif, ' ', '');
UPDATE pacientes
SET nif = REPLACE(nif, '-', '');
UPDATE pacientes
SET nif = REPLACE(nif, '_', '');
 
SET SQL_SAFE_UPDATES = 1;
 
COMMIT;

 -- Eliminar los que no cumplen

START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

DELETE p1
FROM pacientes p1
WHERE p1.nif NOT REGEXP('[0-9]{8}[A-Za-z]');

SET SQL_SAFE_UPDATES = 1;

COMMIT;
-- ===================================================================================
-- Convertir la columna NIF en UNIQUE y NOT NULL
-- ===================================================================================
START TRANSACTION;

ALTER TABLE pacientes
MODIFY nif VARCHAR(50) NOT NULL UNIQUE;

COMMIT;
-- ===================================================================================
-- Corregir o eliminar los números de los colegiados en formato COL-XX-YYYY donde XX es la provincia y YYYY es el número
-- ===================================================================================
START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

UPDATE medicos
SET num_colegiado = CONCAT(
	'COL-',
	-- Busca y extrae los 2 primeros dígitos al inicio
	LPAD(CAST(REGEXP_SUBSTR(num_colegiado, '^[0-9]{2}') AS UNSIGNED), 2, '0'),
	'-',
	-- Busca y extrae el grupo de 3 o 4 dígitos que se encuentra al final
	LPAD(CAST(REGEXP_SUBSTR(num_colegiado, '[0-9]{3,4}$') AS UNSIGNED), 4, '0')
)
WHERE num_colegiado NOT REGEXP 'COL-[0-9]{2}-[0-9]{4}';

SET SQL_SAFE_UPDATES = 1;

COMMIT;

 -- Añadir un check para validar el formato anterior
START TRANSACTION;

ALTER TABLE medicos
ADD CONSTRAINT check_num_colegiado CHECK( num_colegiado REGEXP 'COL-[0-9]{2}-[0-9]{4}');

COMMIT;
-- ===================================================================================
-- Asignar a los médicos con especialidades inexistentes a la especialidad "Medicina General"
-- ===================================================================================
START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

UPDATE medicos
LEFT JOIN especialidades ON medicos.especialidad_id = especialidades.id
SET medicos.especialidad_id = 1 
WHERE especialidades.id IS NULL;
-- --------- Otra forma de hacerlo --------- --
UPDATE medicos 
SET especialidad_id = 1 
WHERE especialidad_id IS NOT NULL 
  AND especialidad_id NOT IN (SELECT id FROM especialidades);

SET SQL_SAFE_UPDATES = 1;

COMMIT;
-- ===================================================================================
-- Añadir las claves foraneas a medicos y visitas
-- ===================================================================================
START TRANSACTION;

SET SQL_SAFE_UPDATES = 1;

-- medicos -> especialidades
ALTER TABLE medicos 
ADD CONSTRAINT fk_medicos_especialidades 
FOREIGN KEY (especialidad_id) REFERENCES especialidades(id)
ON DELETE RESTRICT ON UPDATE CASCADE;

-- visitas -> pacientes
ALTER TABLE visitas 
ADD CONSTRAINT fk_visitas_pacientes 
FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
ON DELETE CASCADE ON UPDATE CASCADE;

-- visitas -> medicos
ALTER TABLE visitas 
ADD CONSTRAINT fk_visitas_medicos 
FOREIGN KEY (medico_id) REFERENCES medicos(id)
ON DELETE RESTRICT ON UPDATE CASCADE;

SET SQL_SAFE_UPDATES = 1;

COMMIT;
-- ===================================================================================
-- Extraer la información de la tabla pacientes a una nueva tabla llamada seguros_pacientes
 -- Datos de la nueva tabla: paciente_id, num_poliza y estado_poliza default 'ACTIVA'
-- ===================================================================================
START TRANSACTION;

-- Crear la tabla
CREATE TABLE seguros_pacientes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  paciente_id INT NOT NULL,
  num_poliza VARCHAR(50) NOT NULL,
  estado_poliza VARCHAR(20) DEFAULT 'ACTIVA'
) ENGINE=InnoDB;

-- Migrar los datos
INSERT INTO seguros_pacientes(paciente_id, num_poliza)
SELECT id, TRIM(num_poliza) 
FROM pacientes
WHERE num_poliza IS NOT NULL;

-- Borrar la columna num_poliza de pacientes
ALTER TABLE pacientes 
DROP COLUMN num_poliza;

-- Garantizar la integridad referencial
ALTER TABLE seguros_pacientes 
ADD CONSTRAINT fk_seguros_pacientes
FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
ON DELETE CASCADE ON UPDATE CASCADE;

COMMIT;
-- ===================================================================================
-- Añadir a la tabla visitas una columna llamada copago_estimado DECIMAL(10,2)
 -- 20% del importe (saneandola primero)
-- ===================================================================================
START TRANSACTION;

-- Crear la columna
ALTER TABLE visitas
ADD COLUMN copago_estimado DECIMAL(10,2);

-- Sanear importe_sucio
SET SQL_SAFE_UPDATES = 0;

UPDATE visitas
SET importe_sucio = CONCAT(TRIM(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(
importe_sucio, '€', ''), '$', ''), 'EUR', ''), 'Gratis', '0'), ',', '.')), '€');

SET SQL_SAFE_UPDATES = 1;

-- Renombrar la columna
ALTER TABLE visitas
RENAME COLUMN importe_sucio To importe_limpio;

-- Asignar el valor a copago_estimado
SET SQL_SAFE_UPDATES = 0;

UPDATE visitas
SET copago_estimado = CAST(REPLACE(importe_limpio, '€', '') AS DECIMAL(10,2)) * 20 / 100;

SET SQL_SAFE_UPDATES = 1;

-- Establecer los campos de num_poliza y copago_estimado como NOT NULL
SET SQL_SAFE_UPDATES = 0;

ALTER TABLE visitas
MODIFY copago_estimado DECIMAL(10,2) NOT NULL;
ALTER TABLE seguros_pacientes
MODIFY num_poliza VARCHAR(20) NOT NULL;

SET SQL_SAFE_UPDATES = 1;

COMMIT;
-- ===================================================================================
-- Limpiar el formato de tel_contacto
-- ===================================================================================
START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

UPDATE pacientes
SET tel_contacto = TRIM(REPLACE(REPLACE(REPLACE(REPLACE(
tel_contacto, '-', ''), '+34', ''), '0034', ''), ' ', ''));
-- Eliminar los que tengan menos de 9 números
UPDATE pacientes
SET tel_contacto = NULL
WHERE LENGTH(tel_contacto) < 9;

SET SQL_SAFE_UPDATES = 1;

COMMIT;

SELECT * FROM pacientes;
-- ===================================================================================
-- Sanear pacientes
-- ===================================================================================
START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

-- Sanear el nif
UPDATE pacientes
SET nif = TRIM(UPPER(REPLACE(nif, '-', '')));
DELETE p
FROM pacientes p
WHERE LENGTH(nif) < 9;
-- Sanear el nombre_completo
UPDATE pacientes
SET nombre_completo = TRIM(UPPER(REPLACE(nombre_completo, '  ', ' ')));
DELETE p
FROM pacientes p
WHERE nombre_completo = 'PACIENTE DE BORRADO';
-- Sanear el email
UPDATE pacientes
SET email = TRIM(REPLACE(email, ',', '.'));
UPDATE pacientes
SET email = CONCAT(SUBSTRING_INDEX(email, '@', 2), '.', SUBSTRING_INDEX(email, '@', -1))
WHERE email LIKE '%@%@%';
-- Sanear f_nacimiento
UPDATE pacientes
SET f_nacimiento = TRIM(REPLACE(REPLACE(f_nacimiento, '/', '-'), '.', '-'));
UPDATE pacientes
SET f_nacimiento = CONCAT(
	SUBSTRING_INDEX(f_nacimiento, '-', -1), '-',
	SUBSTRING_INDEX(SUBSTRING_INDEX(f_nacimiento, '-', 2), '-', -1), '-',
    SUBSTRING_INDEX(f_nacimiento, '-', 1))
WHERE f_nacimiento LIKE '____-__-__';
-- WHERE f_nacimiento LIKE '^[0-9]{4}-[0-9]{2}-[0-9]{2}$';

SET SQL_SAFE_UPDATES = 1;

COMMIT;

SELECT * FROM raw_import_visitas;
-- ===================================================================================
-- Limpiar e insertar los datos de raw_import_visitas sin crear duplicados
-- ===================================================================================
START TRANSACTION;

INSERT INTO pacientes(nif, nombre_completo, tel_contacto, f_nacimiento)
SELECT 
	TRIM(UPPER(SUBSTRING_INDEX(raw_data, '|', 1))),
    TRIM(UPPER(SUBSTRING_INDEX(SUBSTRING_INDEX(raw_data, '|', 2), '|', -1))),
    TRIM(raw_phone),
    TRIM(REPLACE(REPLACE(SUBSTRING_INDEX(SUBSTRING_INDEX(raw_data, '|', 3), '|', -1), '/', '-'), '.', '-'))
FROM raw_import_visitas
WHERE TRIM(UPPER(SUBSTRING_INDEX(raw_data, '|', 1))) NOT IN (
	SELECT nif FROM pacientes WHERE nif IS NOT NULL);

COMMIT;
-- ===================================================================================
-- Gestión de NULLs
-- ===================================================================================
SELECT 
	id, 
    nif, 
    nombre_completo, 
    COALESCE(email, 'Sin correo') AS email, 
	COALESCE(tel_contacto, 'Sin telefono') AS tel_contacto, f_nacimiento
FROM pacientes;

-- ===================================================================================
-- Castear las fechas a DATETIME
-- ===================================================================================
START TRANSACTION;

SET SQL_SAFE_UPDATES = 0;

UPDATE pacientes
SET f_nacimiento = CONCAT(
	SUBSTRING_INDEX(f_nacimiento, '-', -1), '-',
    SUBSTRING_INDEX(SUBSTRING_INDEX(f_nacimiento, '-', 2), '-', -1), '-',
    SUBSTRING_INDEX(f_nacimiento, '-', 1)
)
WHERE f_nacimiento LIKE '__-__-____';

COMMIT;

-- Castear automáticamente el texto 'YYYY-MM-DD' al nuevo tipo numérico.
ALTER TABLE pacientes
MODIFY f_nacimiento DATE NULL;

-- ___________________________________________________________________________________
-- ===================================================================================
	-- Script de saneamiento Guia Daniel (Ordinaria)
-- ===================================================================================
-- ___________________________________________________________________________________

USE FugaTech_Forensic;
--
-- --- USAR EL MODO SEGURO --- --
--
-- Workbench lanza el Error Code: 1175 si intentas un UPDATE sin una PK en el WHERE. Como
-- “forenses”, debemos sortearlo:
SET SQL_SAFE_UPDATES = 0;

UPDATE clientes
SET email = REPLACE(email, ',', '.')
WHERE email LIKE '%@%,%';

SET SQL_SAFE_UPDATES = 1;

--
-- --- UPDATE Y DELETE --- --
--
-- ON DELETE CASCADE: Borrar un padre fulmina automáticamente a sus hijos

-- ON UPDATE CASCADE: Si el ID principal cambia el cambio se propaga a todas las tablas dependientes.

-- RESTRICT / NO ACTION: El escudo principal. Bloquea la sentencia si existen registros dependientes.

-- SET NULL: Conservación de históricos. Si eliminamos un comercial, no borramos sus
-- ventas (CASCADE), sino que las dejamos “huérfanas” pero intactas colocando NULL en su FK.

--
-- --- DESACTIVACIÓN CLAVES FORÁNEAS --- --
--
SET FOREIGN_KEY_CHECKS = 0; -- Apagar la verificación de claves foráneas

TRUNCATE TABLE ventas; -- Destruye y vuelve a crear la tabla vacia (No genera rollback)
TRUNCATE TABLE clientes;

SET FOREIGN_KEY_CHECKS = 1; -- Encender la verificación de claves foráneas

--
-- --- INSERCIÓN AVANZADA Y CONSODALIDACIÓN --- --
--
-- Legacy Systems: Pasar datos de una base antigua a una nueva estructura.

-- Consolidación: Unificar bases tras una fusión de empresas.

-- ETL de staging: Procesar datos de Excels externos antes de llevarlos a producción.

-- Creamos una tabla destino
CREATE TABLE IF NOT EXISTS clientes_vip (
	cliente_id INT PRIMARY KEY,
    nombre_completo VARCHAR(150),
    email_contacto VARCHAR(100),
    potencial_compra DECIMAL(10, 2),
    fecha_incusion DATETIME DEFAULT NOW()
);

TRUNCATE TABLE clientes_vip;

INSERT INTO clientes_vip (cliente_id, nombre_completo, email_contacto, potencial_compra)
SELECT
	id,
    TRIM(nombre), -- quitamos espacios
    LOWER(REPLACE(email, ',', '.')), -- arreglamos las comas
    credito * 1.20 -- calcular el potencial (20% más)
FROM clientes
WHERE activo = 1
	AND credito > 50; -- y que el credito sea de mas de 50

SELECT * FROM clientes_vip;

-- Tecnica de UPSERT aplicada a la tabla de clientes.
-- Si el cliente con ID 1 no existe, se crea con los datos proporcionados.
-- Si el ID 1 ya existe, se actualiza su credito (anadiendo 100) y se marca como activo
INSERT INTO clientes (id, nombre, email, telefono, credito, activo)
VALUES (1, 'Juan Gomez', 'juan.gomez@empresa.local', '+34 600-111-222', 100.00, 1)
ON DUPLICATE KEY UPDATE
	credito = credito + 100.00,
    activo = 1;

--
-- --- UPDATE: SANEAMIENTO PROFUNDO
--
SET SQL_SAFE_UPDATES = 0;
-- Quita los '+34', los '0034' y los '-' del número de telefono
UPDATE clientes
SET telefono = REPLACE(REPLACE(REPLACE(telefono, '+34', ''), '0034', ''), '-', '')
WHERE telefono LIKE '+34%' OR telefono LIKE '0034%' OR telefono LIKE '%-%';
-- Limpiar posibles espacios generados
UPDATE clientes SET telefono = TRIM(telefono);
SET SQL_SAFE_UPDATES = 1;

SELECT * FROM clientes;

--
-- --- UPDATE: SUBCONSULTAS Y JOINS
--
SET SQL_SAFE_UPDATES = 0;
-- Bloquear a los clientes cuyas cuentas bancarias asociadas esten marcadas como fraudulentas
UPDATE clientes
SET activo = 0
WHERE id IN (
	SELECT id_cliente FROM cuentas_bancarias WHERE fraude = 1
);
SET SQL_SAFE_UPDATES = 1;

SET SQL_SAFE_UPDATES = 0;
-- Actualizar los precios del catálogo cruzándolos con una tabla temporal de nuevas tarifas
UPDATE catalogo c
INNER JOIN importacion_tarifas t ON c.referencia = t.referencia
SET c.precio = t.nuevo_precio, c.fecha_actualizacion = NOW();
SET SQL_SAFE_UPDATES = 1;

SET SQL_SAFE_UPDATES = 0;
-- Normalizar prefijos internacionales basándose en patrones
UPDATE clientes
SET telefono = CASE
	WHEN email LIKE '%.local' THEN CONCAT('+34', telefono)
    WHEN nombre LIKE 'Admin%' THEN '+00 000000000'
    ELSE telefono
END
WHERE activo = 1;
SET SQL_SAFE_UPDATES = 1;

--
-- --- UPDATE: SUBCONSULTAS Y JOINS
--
INSERT INTO clientes (nombre, email, telefono, credito) VALUES
('Sujeto A', NULL, '600-000-001', 10.00), -- Solo teléfono
('Sujeto B', 'b@test.com', NULL, 20.00), -- Solo email
('Sujeto C', NULL, NULL, 0.00), -- Sin datos de contacto
('Sujeto D', '@test.com', '600-000-004', 5.00); -- Datos completos

SET SQL_SAFE_UPDATES = 0;
-- Si el teléfono es NULL, pone ’DESCONOCIDO’.
UPDATE clientes
SET telefono = IFNULL(telefono, 'DESCONOCIDO')
WHERE nombre LIKE 'Sujeto%';
SET SQL_SAFE_UPDATES = 1;

-- Devuelve el primer valor NO NULO de una lista
SELECT
	nombre,
    COALESCE(email, telefono, 'ILOCALIZABLE') AS contacto_urgente
FROM clientes
WHERE nombre LIKE 'Sujeto%';

-- Si el nombre es NULL devuelve 'Expediente: ANÓNIMO', sino 'Expediente: *Nombre*'
SELECT
	CONCAT('Expediente: ', COALESCE(nombre, 'ANÓNIMO')) AS info_sujeto
FROM clientes;

--
-- --- UPDATE: SANEAMIENTO FECHAS Y TIPOS (CAST)
--
SET SQL_SAFE_UPDATES = 0;
-- Conversión de tipos y saneamiento de fechas
UPDATE sys_logs
SET created_at = STR_TO_DATE('12/03/2026 10:30:00', '%d/%m/%Y %H:%i:%s')
WHERE id = 1;
SET SQL_SAFE_UPDATES = 1;

SET SQL_SAFE_UPDATES = 0;
-- Uso de CAST para asegurar tipos numéricos en importaciones de cadenas
UPDATE clientes
SET credito = CAST('150.50' AS DECIMAL(10, 2))
WHERE nombre = 'Carlos Ruiz';
SET SQL_SAFE_UPDATES = 1;

--
-- --- AÑADIR Y ELIMINAR COLUMNAS
--
-- Añadir una columna de NIF después del nombre
ALTER TABLE clientes ADD COLUMN nif VARCHAR(12) AFTER nombre;
-- Eliminar una columna que ya no es necesaria
ALTER TABLE sys_logs DROP COLUMN metadata_obsoleta;

--
-- --- MODIFICAR TIPO DE COLUMNAS
--
-- Ampliar la longitud permitida para el nombre del cliente
ALTER TABLE clientes MODIFY COLUMN nombre VARCHAR(250);
-- Ejemplo de cambio de nombre y tipo
ALTER TABLE clientes CHANGE COLUMN nombre nombre_completo VARCHAR(200);

--
-- --- IMPONER LA INTEGRIDAD: UNICIDAD Y OBLIGATORIEDAD (UNIQUE, NOT NULL)
--
-- Forzar que los correos electrónicos sean unicos para evitar duplicados en el futuro
ALTER TABLE clientes ADD CONSTRAINT uq_email UNIQUE (email);

-- Convertir una columna en obligatoria
-- Primero saneamos los nulos existentes para evitar errores de restricción
UPDATE clientes SET nif = '00000000T' WHERE nif IS NULL;
-- Ahora convertimos la columna libre de nulos
ALTER TABLE clientes MODIFY nif VARCHAR(12) NOT NULL;

--
-- --- AÑADIR REGLAS (CHECK)
--
-- Impedir que el crédito del cliente sea negativo mediante una restricción de validación
ALTER TABLE clientes ADD CONSTRAINT chk_credito_positivo CHECK (credito >= 0);
-- Validación compleja: Asegurar que el NIF tenga 8 números y una letra
ALTER TABLE clientes ADD CONSTRAINT chk_nif_formato CHECK (nif REGEXP '^[0-9]{8}[A-Z]$'); -- "$" indica el final de cadena

--
-- --- DELETE VS TRUNCATE
--
-- Purga de registros obsoletos basandose en fechas
DELETE FROM sys_logs
WHERE created_at < DATE_SUB(NOW(), INTERVAL 5 YEAR);

-- Vaciado inmediato y reseteo del auto_incremento de la tabla de importacion tras procesarla
TRUNCATE TABLE import_raw;

--
-- --- TRANSACCIONES
--
-- Las sentencias CREATE, DROP, ALTER, TRUNCATE provocan un COMMIT implícito
-- No se pueden deshacer con un ROLLBACK.

--
-- --- SAVEPOINTS
--
SAVEPOINT antes_borrado;
ROLLBACK TO antes_borrado;

START TRANSACTION;											-- Empieza la transacción
INSERT INTO clientes (nombre, email, telefono, credito)		-- Inserta datos nuevos (Erroneos)
VALUES ('persona', 'p@gmail.es', '600-000-001', 10.00);
ROLLBACK;													-- Vuelve a la transacción eliminando la inserción
INSERT INTO clientes (nombre, email, telefono, credito)		-- Inserta datos nuevos (Corregidos)
VALUES ('Persona', 'persona@gmail.es', '600-000-001', 10.00);
COMMIT;														-- Guarda los cambios

-- READ UNCOMMITTED: Permite leer cambios no confirmados (Lecturas Sucias).
SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;

-- READ COMMITTED: Solo lee datos confirmados. Evita lecturas sucias, pero
-- permite Lecturas No Repetibles.
SET SESSION TRANSACTION ISOLATION LEVEL READ COMMITTED;

-- REPEATABLE READ: Garantiza que los datos no cambien durante tu transacción.
-- Usa Snapshots.
SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ;

-- SERIALIZABLE: El aislamiento total. Bloquea rangos de filas para evitar Lecturas
-- Fantasma.
SET SESSION TRANSACTION ISOLATION LEVEL SERIALIZABLE;