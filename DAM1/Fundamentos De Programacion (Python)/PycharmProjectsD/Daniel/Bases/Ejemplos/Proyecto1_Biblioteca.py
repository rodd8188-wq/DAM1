# PROYECTO 1: Sistema de Gestión de Biblioteca
# Nivel: Principiante
# Concepto: CRUD básico con SQLite

import sqlite3
from datetime import datetime
import os

class BibliotecaDB:
    """Sistema simple de gestión de biblioteca"""
    
    def __init__(self, db_name='biblioteca.db'):
        self.db_name = db_name
        self.crear_tabla()
    
    def get_conexion(self):
        """Obtiene conexión a la BD"""
        conexion = sqlite3.connect(self.db_name)
        conexion.row_factory = sqlite3.Row
        return conexion
    
    def crear_tabla(self):
        """Crea la tabla de libros si no existe"""
        conexion = self.get_conexion()
        cursor = conexion.cursor()
        
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS libros (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                titulo TEXT NOT NULL,
                autor TEXT NOT NULL,
                isbn TEXT UNIQUE,
                año_publicacion INTEGER,
                estado TEXT DEFAULT 'disponible',
                fecha_agregado DATETIME DEFAULT CURRENT_TIMESTAMP
            )
        ''')
        
        conexion.commit()
        conexion.close()
    
    def agregar_libro(self, titulo, autor, isbn, año):
        """Agrega un nuevo libro"""
        conexion = self.get_conexion()
        cursor = conexion.cursor()
        
        try:
            cursor.execute('''
                INSERT INTO libros (titulo, autor, isbn, año_publicacion)
                VALUES (?, ?, ?, ?)
            ''', (titulo, autor, isbn, año))
            
            conexion.commit()
            print(f"✓ Libro '{titulo}' agregado exitosamente")
            return True
        except sqlite3.IntegrityError:
            print(f"✗ Error: El ISBN {isbn} ya existe")
            return False
        finally:
            conexion.close()
    
    def listar_libros(self):
        """Muestra todos los libros"""
        conexion = self.get_conexion()
        cursor = conexion.cursor()
        
        cursor.execute('SELECT * FROM libros ORDER BY titulo')
        libros = cursor.fetchall()
        conexion.close()
        
        if not libros:
            print("No hay libros en la biblioteca")
            return
        
        print("\n" + "="*80)
        print(f"{'ID':<4} {'Título':<25} {'Autor':<20} {'Año':<6} {'Estado':<12}")
        print("="*80)
        
        for libro in libros:
            print(f"{libro['id']:<4} {libro['titulo']:<25} {libro['autor']:<20} "
                  f"{libro['año_publicacion']:<6} {libro['estado']:<12}")
        print("="*80 + "\n")
    
    def buscar_libro(self, criterio, valor):
        """Busca libros por criterio (titulo, autor, isbn)"""
        conexion = self.get_conexion()
        cursor = conexion.cursor()
        
        criterios = {
            'titulo': 'titulo LIKE ?',
            'autor': 'autor LIKE ?',
            'isbn': 'isbn = ?'
        }
        
        if criterio not in criterios:
            print(f"Criterio '{criterio}' no válido")
            return
        
        query = f"SELECT * FROM libros WHERE {criterios[criterio]}"
        cursor.execute(query, (f"%{valor}%",))
        resultados = cursor.fetchall()
        conexion.close()
        
        if resultados:
            print(f"\nEncontrados {len(resultados)} libro(s):")
            for libro in resultados:
                print(f"  - {libro['titulo']} por {libro['autor']} ({libro['año_publicacion']})")
        else:
            print(f"No se encontraron libros con {criterio}='{valor}'")
    
    def cambiar_estado(self, libro_id, nuevo_estado):
        """Cambia el estado de un libro (disponible/prestado/dañado)"""
        estados_validos = ['disponible', 'prestado', 'dañado']
        
        if nuevo_estado not in estados_validos:
            print(f"Estado inválido. Usa: {', '.join(estados_validos)}")
            return False
        
        conexion = self.get_conexion()
        cursor = conexion.cursor()
        
        cursor.execute('UPDATE libros SET estado = ? WHERE id = ?', 
                      (nuevo_estado, libro_id))
        
        if cursor.rowcount > 0:
            conexion.commit()
            print(f"✓ Estado del libro actualizado a '{nuevo_estado}'")
            return True
        else:
            print(f"✗ Libro con ID {libro_id} no encontrado")
            return False
        
        conexion.close()
    
    def eliminar_libro(self, libro_id):
        """Elimina un libro de la biblioteca"""
        conexion = self.get_conexion()
        cursor = conexion.cursor()
        
        cursor.execute('DELETE FROM libros WHERE id = ?', (libro_id,))
        
        if cursor.rowcount > 0:
            conexion.commit()
            print(f"✓ Libro eliminado")
            return True
        else:
            print(f"✗ Libro con ID {libro_id} no encontrado")
            return False
        
        conexion.close()
    
    def estadisticas(self):
        """Muestra estadísticas de la biblioteca"""
        conexion = self.get_conexion()
        cursor = conexion.cursor()
        
        cursor.execute('SELECT COUNT(*) as total FROM libros')
        total = cursor.fetchone()['total']
        
        cursor.execute("SELECT COUNT(*) as disponibles FROM libros WHERE estado='disponible'")
        disponibles = cursor.fetchone()['disponibles']
        
        cursor.execute("SELECT COUNT(*) as prestados FROM libros WHERE estado='prestado'")
        prestados = cursor.fetchone()['prestados']
        
        conexion.close()
        
        print(f"\n{'ESTADÍSTICAS':-^40}")
        print(f"Total de libros: {total}")
        print(f"Disponibles: {disponibles}")
        print(f"Prestados: {prestados}")
        print(f"Otros estados: {total - disponibles - prestados}")
        print("-"*40 + "\n")


# ============ MENÚ INTERACTIVO ============

def menu():
    """Menú principal de la aplicación"""
    biblioteca = BibliotecaDB()
    
    while True:
        print("\n" + "="*40)
        print("📚 SISTEMA DE BIBLIOTECA 📚")
        print("="*40)
        print("1. Agregar libro")
        print("2. Listar todos los libros")
        print("3. Buscar libro")
        print("4. Cambiar estado de libro")
        print("5. Eliminar libro")
        print("6. Ver estadísticas")
        print("7. Salir")
        print("="*40)
        
        opcion = input("Selecciona una opción (1-7): ").strip()
        
        if opcion == '1':
            print("\n--- Agregar Nuevo Libro ---")
            titulo = input("Título: ").strip()
            autor = input("Autor: ").strip()
            isbn = input("ISBN: ").strip()
            try:
                año = int(input("Año de publicación: ").strip())
                biblioteca.agregar_libro(titulo, autor, isbn, año)
            except ValueError:
                print("✗ Error: El año debe ser un número")
        
        elif opcion == '2':
            print()
            biblioteca.listar_libros()
        
        elif opcion == '3':
            print("\n--- Buscar Libro ---")
            print("Buscar por: 1=título, 2=autor, 3=isbn")
            tipo = input("Opción (1-3): ").strip()
            
            criterio_map = {'1': 'titulo', '2': 'autor', '3': 'isbn'}
            if tipo in criterio_map:
                valor = input(f"Ingresa {criterio_map[tipo]}: ").strip()
                biblioteca.buscar_libro(criterio_map[tipo], valor)
            else:
                print("Opción no válida")
        
        elif opcion == '4':
            try:
                libro_id = int(input("ID del libro: ").strip())
                nuevo_estado = input("Nuevo estado (disponible/prestado/dañado): ").strip().lower()
                biblioteca.cambiar_estado(libro_id, nuevo_estado)
            except ValueError:
                print("✗ Error: Ingresa un ID válido")
        
        elif opcion == '5':
            try:
                libro_id = int(input("ID del libro a eliminar: ").strip())
                confirmar = input("¿Estás seguro? (s/n): ").strip().lower()
                if confirmar == 's':
                    biblioteca.eliminar_libro(libro_id)
            except ValueError:
                print("✗ Error: Ingresa un ID válido")
        
        elif opcion == '6':
            biblioteca.estadisticas()
        
        elif opcion == '7':
            print("\n¡Hasta luego! 👋")
            break
        
        else:
            print("✗ Opción no válida")


# ============ SCRIPT DE PRUEBA ============

def datos_prueba():
    """Crea datos de prueba"""
    biblioteca = BibliotecaDB()
    
    print("Agregando datos de prueba...\n")
    
    libros = [
        ("Don Quijote", "Miguel de Cervantes", "978-8400071829", 1605),
        ("1984", "George Orwell", "978-0451524935", 1949),
        ("El Quijote", "Miguel de Cervantes", "978-8499830123", 1615),
        ("Cien años de soledad", "Gabriel García Márquez", "978-8401338777", 1967),
        ("El Código Da Vinci", "Dan Brown", "978-0307474278", 2003),
    ]
    
    for titulo, autor, isbn, año in libros:
        biblioteca.agregar_libro(titulo, autor, isbn, año)
    
    print("\n✓ Datos de prueba agregados")
    biblioteca.listar_libros()


if __name__ == '__main__':
    import sys
    
    # Si pasa 'test' como argumento, agrega datos de prueba
    if len(sys.argv) > 1 and sys.argv[1] == 'test':
        # Limpiar BD anterior si existe
        if os.path.exists('biblioteca.db'):
            os.remove('biblioteca.db')
        datos_prueba()
    
    # Ejecutar menú interactivo
    menu()
