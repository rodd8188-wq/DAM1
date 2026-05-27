import sqlite3
from datetime import date

def conectar():
    conn = sqlite3.connect("ministerio_naval.db")
    return conn


def crear_tablas():
    conn = conectar()
    cur = conn.cursor()

    cur.executescript("""
        CREATE TABLE IF NOT EXISTS naves (
            id               INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre           TEXT    NOT NULL,
            tipo             TEXT,
            año_construccion INTEGER,
            epoca            TEXT
        );

        CREATE TABLE IF NOT EXISTS agentes (
            id               INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre           TEXT    NOT NULL,
            año_nacimiento   INTEGER,
            especialidad     TEXT,
            activo           INTEGER DEFAULT 1
        );

        CREATE TABLE IF NOT EXISTS misiones (
            id             INTEGER PRIMARY KEY AUTOINCREMENT,
            id_agente      INTEGER NOT NULL REFERENCES agentes(id),
            id_nave        INTEGER NOT NULL REFERENCES naves(id),
            fecha_partida  TEXT    NOT NULL,
            fecha_regreso  TEXT,
            exito          INTEGER DEFAULT 0,
            incidencias    TEXT
        );
    """)

    conn.commit()
    conn.close()

def registrar_nave(nombre, tipo, año, epoca):
    conn = conectar()
    cur = conn.cursor()
    cur.execute(
        "INSERT INTO naves (nombre, tipo, año_construccion, epoca) VALUES (?, ?, ?, ?)",
        (nombre, tipo, año, epoca)
    )
    conn.commit()
    nave_id = cur.lastrowid
    conn.close()
    print(f"  nave registrada: '{nombre}' (ID {nave_id})")
    return nave_id


def enviar_agente(id_agente, id_nave, fecha_partida):
    conn = conectar()
    cur = conn.cursor()

    cur.execute("""
        SELECT m.id, a.nombre
          FROM misiones m
          JOIN agentes a ON a.id = m.id_agente
         WHERE m.id_agente = ? AND m.fecha_regreso IS NULL
    """, (id_agente,))
    en_mision = cur.fetchone()

    if en_mision:
        cur.execute("SELECT nombre FROM agentes WHERE id = ?", (id_agente,))
        row = cur.fetchone()
        nombre_agente = row[0] if row else f"ID {id_agente}"
        conn.close()
        print(f"  ERROR: El agente '{nombre_agente}' ya esta en mision. "
              f"Paradoja temporal detectada?")
        return None

    cur.execute(
        "INSERT INTO misiones (id_agente, id_nave, fecha_partida) VALUES (?, ?, ?)",
        (id_agente, id_nave, fecha_partida)
    )
    conn.commit()
    mision_id = cur.lastrowid
    conn.close()
    print(f"  mision {mision_id} iniciada -- agente {id_agente} -> nave {id_nave} "
          f"(partida: {fecha_partida})")
    return mision_id


def cerrar_mision(id_mision, exito, incidencias=None):
    conn = conectar()
    cur = conn.cursor()
    fecha_regreso = date.today().isoformat()
    cur.execute("""
        UPDATE misiones
           SET fecha_regreso = ?, exito = ?, incidencias = ?
         WHERE id = ?
    """, (fecha_regreso, exito, incidencias, id_mision))
    conn.commit()
    conn.close()
    estado = "exito" if exito else "fallida"
    print(f"  mision {id_mision} cerrada ({estado}, regreso: {fecha_regreso})")


def naves_sin_capitan():
    conn = conectar()
    cur = conn.cursor()
    cur.execute("""
        SELECT n.id, n.nombre, n.tipo, n.epoca
          FROM naves n
         WHERE n.id NOT IN (
               SELECT id_nave FROM misiones WHERE fecha_regreso IS NULL
         )
         ORDER BY n.nombre
    """)
    filas = cur.fetchall()
    conn.close()
    return filas


def historial_agente(id_agente):
    conn = conectar()
    cur = conn.cursor()
    cur.execute("""
        SELECT a.nombre,
               n.nombre  AS nave,
               m.fecha_partida,
               m.fecha_regreso,
               m.exito,
               m.incidencias
          FROM misiones m
          JOIN agentes a ON a.id = m.id_agente
          JOIN naves   n ON n.id = m.id_nave
         WHERE m.id_agente = ?
         ORDER BY m.fecha_partida
    """, (id_agente,))
    filas = cur.fetchall()
    conn.close()
    return filas


def misiones_fallidas():
    conn = conectar()
    cur = conn.cursor()
    cur.execute("""
        SELECT n.nombre  AS nave,
               a.nombre  AS agente,
               m.fecha_partida,
               m.fecha_regreso,
               m.incidencias
          FROM misiones m
          JOIN agentes a ON a.id = m.id_agente
          JOIN naves   n ON n.id = m.id_nave
         WHERE m.exito = 0
           AND m.fecha_regreso IS NOT NULL
         ORDER BY m.fecha_regreso
    """)
    filas = cur.fetchall()
    conn.close()
    return filas


# --- datos de prueba ---

def poblar_datos():
    print("\nregistrando naves historicas:")
    registrar_nave("Santa Maria", "nao", 1480, "Epoca de los Descubrimientos")
    registrar_nave("San Martin", "galeon",    1580, "Siglo de Oro")
    registrar_nave("La Victoria", "nao", 1519, "Epoca de los Descubrimientos")
    registrar_nave("Nuestra Señora de Atocha","galeon", 1620, "Siglo de Oro")
    registrar_nave("El Pelicano", "fragata", 1577, "Era Isabelina")
    registrar_nave("La Bretagne", "bergantin", 1749, "Ilustracion")

    print("\nregistrando agentes del ministerio:")
    conn = conectar()
    cur = conn.cursor()
    agentes = [
        ("Isabel Montoya", 1985, "infiltracion"),
        ("Rodrigo Pelaez", 1978, "combate naval"),
        ("Nuria Espinosa", 1991, "diplomacia"),
        ("Fermin Calvo", 1970, "cartografia"),
    ]
    cur.executemany(
        "INSERT INTO agentes (nombre, año_nacimiento, especialidad) VALUES (?, ?, ?)",
        agentes
    )
    conn.commit()
    conn.close()
    for a in agentes:
        print(f"  agente registrado: {a[0]} ({a[2]})")

    m1 = enviar_agente(1, 1, "1492-08-03")
    cerrar_mision(m1, 1)

    m2 = enviar_agente(2, 2, "1588-07-12")
    cerrar_mision(m2, 0, "El agente altero el orden de combate. La Armada Invencible "
                         "llego tarde a Gravelinas por un error de navegacion sospechoso.")

    m3 = enviar_agente(3, 3, "1519-09-20")
    cerrar_mision(m3, 1)

    m4 = enviar_agente(4, 4, "1622-09-04")
    cerrar_mision(m4, 0, "El agente intento redirigir la ruta para evitar el huracan. "
                         "Fracaso. La nave naufrage con todo el tesoro a bordo.")

    m5 = enviar_agente(1, 6, "1756-03-18")
    print(f"  mision {m5} en curso, agente 1 no ha regresado todavia")

    m6 = enviar_agente(2, 5, "1577-12-13")
    print(f"  mision {m6} en curso, agente 2 tampoco")

    print("\nintentando mandar a un agente que ya esta ocupado:")
    enviar_agente(1, 2, "1590-01-01")


# --- informe ---

def imprimir_informe():

    sin_capitan = naves_sin_capitan()
    print(f"naves sin capitan asignado: {len(sin_capitan)}")
    for nave in sin_capitan:
        print(f"  [{nave[2]}] {nave[1]}  ({nave[3]})")

    historial = historial_agente(2)
    nombre_agente = historial[0][0] if historial else "agente 2"
    print(f"historial de {nombre_agente}:")
    for h in historial:
        _, nave, partida, regreso, exito, incidencias = h
        if regreso is None:
            estado = "en curso"
        elif exito:
            estado = "exito"
        else:
            estado = "fallida"
        regreso_str = regreso if regreso else "en curso"
        print(f"  [{estado}] {nave:<35} {partida} -> {regreso_str}")

    fallidas = misiones_fallidas()
    print(f"misiones fallidas registradas: {len(fallidas)}")
    for f in fallidas:
        nave, agente, partida, regreso, incidencias = f
        resumen = (incidencias[:90]) if incidencias and len(incidencias) > 90 else (incidencias or "sin incidencias")
        print(f"  {nave} | {agente}")
        print(f"    incidencia: {resumen}")

    print("base de datos: ministerio_naval.db\n")

crear_tablas()
poblar_datos()
imprimir_informe()