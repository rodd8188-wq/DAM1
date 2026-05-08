#Aplicacion peliculitas
import sqlite3

def set_up():
    conexion = sqlite3.connect('peliculas.db')
    cursor = conexion.cursor()

    cursor.execute("""
        DROP TABLE IF EXISTS peliculas
    """)
    cursor.execute("""
        CREATE TABLE IF NOT EXISTS peliculas (
            id_pelicula INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre TEXT NOT NULL,
            director TEXT NOT NULL,
            anio INTEGER NOT NULL,
            precio DECIMAL NOT NULL,
            fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP
        )
    """)
    datos_nuevos = {
        ("el diario se viste de prada 2", "david frankel", 2026, 4.5),
        ("la caida", "lucia puenzo", 2022, 2.5),
        ("scooby-doo: comienza el misterio", "brian levant", 2009,2.0)
    }
    cursor.executemany("""
        INSERT INTO peliculas(nombre, director, anio, precio)
        VALUES (?, ?, ?, ?)
    """, datos_nuevos)

    conexion.commit()   #Aplicar los cambios
    cursor.close()      #Cerrar el cursor (Cerrando la conexión tambien se cierra el cursor)
    conexion.close()    #Cerrar la conexión
def mostrar_todos():
    with sqlite3.connect('peliculas.db') as conexion:
        cursor = conexion.cursor()
        cursor.execute("""
            SELECT * FROM peliculas
        """)
        peliculas = cursor.fetchall()
        print(peliculas)
def mostrar_apartir_del_anio(anio):
    with sqlite3.connect('peliculas.db') as conexion:
        conexion.row_factory = sqlite3.Row
        cursor = conexion.cursor()
        cursor.execute("""
            SELECT * FROM peliculas
            WHERE anio > ?  
        """, (anio,))
        peliculas = cursor.fetchall()
        for pelicula in peliculas:
            print(pelicula['nombre'],pelicula['director'],pelicula['anio'])
def mostrar_con_el_id(id_pelicula):
    with sqlite3.connect('peliculas.db') as conexion:
        cursor = conexion.cursor()
        cursor.execute("""
            SELECT * FROM peliculas WHERE id_pelicula = ?
        """, (id_pelicula,))
        peliculas = cursor.fetchone()
        print(peliculas)
def guardar_nuevo(nombre, director, anio, precio):
    with sqlite3.connect('peliculas.db') as conexion:
        cursor = conexion.cursor()
        cursor.execute("""
            INSERT INTO peliculas(nombre, director, anio, precio)
            VALUES (?, ?, ?, ?)
        """, (nombre, director, anio, precio))
        conexion.commit()
        if cursor.rowcount == 0:    #Si se han visto afectadas 0 lineas
            print("No se ha podido guardar")
        else:
            print("Se ha guardado exitosamente")
def actualizar(nombre, director, anio, precio, id_pelicula):
    with sqlite3.connect('peliculas.db') as conexion:
        cursor = conexion.cursor()
        cursor.execute("""
            UPDATE peliculas SET nombre = ? director = ?, anio = ?, precio = ?
            WHERE id_pelicula = ?
        """, (nombre, director, anio, precio, id_pelicula))
        conexion.commit()
        if cursor.rowcount == 0:    #Comprobar si hay lineas que han sido afectadas
            print("No se ha podido actualizar")
        else:
            print("Se ha actualizado exitosamente")
def guardar(titulo, director, anio, precio, id = 0):
    if id == 0:
        guardar_nuevo(titulo, director, anio, precio)
    elif id > 0:
        actualizar(titulo, director, anio, precio)
    else:
        print("Solo números positivos")

set_up()
mostrar_todos()
mostrar_apartir_del_anio(2020)
mostrar_con_el_id(1)