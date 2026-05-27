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
