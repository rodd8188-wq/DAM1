import sqlite3

# Función para pruebas (Muestra todos)
def mostrarTodos():
    with sqlite3.connect('gimnasio.db') as conexion:
        cursor = conexion.cursor()
        cursor.execute("""
            SELECT * FROM socios;
        """)
        socios = cursor.fetchall()
        for socio in socios:
            print(socio[0], socio[1],
                  "| Actividad:", socio[2],
                  "| Cuota mensual:", socio[3],
                  "| Antiguedad:", socio[4])

# Pregunta 1: Consulta con filtros complejos (2 puntos)
def mostrarYogaMayor12():
    with sqlite3.connect('gimnasio.db') as conexion:
        cursor = conexion.cursor()
        cursor.execute("""
            SELECT * FROM socios
            WHERE actividad = 'Yoga'
            AND antiguedad_meses > 12;
        """)
        socios = cursor.fetchall()
        for socio in socios:
            print(socio[0],socio[1], "| Actividad:",socio[2], "| Antiguedad:",socio[4])

# Pregunta 2: Actualización masiva de cuotas (2 puntos)
def actualizacionMasiva():
    with sqlite3.connect('gimnasio.db') as conexion:
        cursor = conexion.cursor()
        cursor.execute("""
            UPDATE socios SET cuota_mensual = cuota_mensual + 10
            WHERE actividad = 'Crossfit';
        """)
# Pregunta 3: Baja de usuarios (2 puntos)
def limpiarBase():
    with sqlite3.connect('gimnasio.db') as conexion:
        cursor = conexion.cursor()
        cursor.execute("""
            DELETE FROM socios
            WHERE antiguedad_meses = 0;
        """)

# Pregunta 4: Estadísticas de actividad (2 puntos)
def mostrarEstadisticas():
    with sqlite3.connect('gimnasio.db') as conexion:
        cursor = conexion.cursor()
        cursor.execute("""
            SELECT COUNT(*),AVG(cuota_mensual) FROM socios
            WHERE actividad = 'Musculación';
        """)
        cuenta = cursor.fetchone();
        print("Número de socios inscritos en 'Musculación:'", cuenta[0])
        print("La cuota mensual promedio:", round(cuenta[1], 2))

def nuevoRegistro(nombre, actividad, cuota_mensual, antiguedad_meses):
    with sqlite3.connect('gimnasio.db') as conexion:
        cursor = conexion.cursor()
        cursor.execute("""
            INSERT INTO socios (nombre, actividad, cuota_mensual, antiguedad_meses)
            VALUES (?, ?, ?, ?);
        """, (nombre, actividad, cuota_mensual, antiguedad_meses))
        conexion.commit()

# Llamada pregunta 1
print("Pregunta 1")
mostrarYogaMayor12()

# Llamada pregunta 2
actualizacionMasiva()

# Llamada pregunta 3
limpiarBase()

# Llamada pregunta 4
print("Pregunta 4")
mostrarEstadisticas()

# Llamada pregunta 5
nuevoRegistro("Elena Rodríguez", "Natación", 45.50, 1)