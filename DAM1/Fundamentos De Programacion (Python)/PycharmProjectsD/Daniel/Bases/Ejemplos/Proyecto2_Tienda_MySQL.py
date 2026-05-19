# PROYECTO 2: Sistema de Gestión de Tienda
# Nivel: Intermedio
# Concepto: MySQL, múltiples tablas, relaciones, consultas avanzadas

"""
REQUISITOS:
- pip install mysql-connector-python

SETUP DE BASE DE DATOS:
1. Crea la base de datos:
   mysql -u root -p
   CREATE DATABASE tienda_db CHARACTER SET utf8mb4;
   USE tienda_db;

2. Ejecuta este script inicialmente con la función crear_tablas()
"""

import mysql.connector
from mysql.connector import Error
from datetime import datetime
from decimal import Decimal
import os


class TiendaDB:
    """Gestión de tienda con MySQL"""
    
    def __init__(self, host='localhost', user='root', password='', database='tienda_db'):
        self.config = {
            'host': host,
            'user': user,
            'password': password,
            'database': database
        }
    
    def conectar(self):
        """Crea una conexión a la BD"""
        try:
            conexion = mysql.connector.connect(**self.config)
            return conexion
        except Error as e:
            print(f"✗ Error de conexión: {e}")
            return None
    
    def crear_tablas(self):
        """Crea las tablas necesarias"""
        conexion = self.conectar()
        if not conexion:
            return
        
        cursor = conexion.cursor()
        
        # Tabla de categorías
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS categorias (
                id INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(100) NOT NULL UNIQUE,
                descripcion TEXT,
                fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
        ''')
        
        # Tabla de productos
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS productos (
                id INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(150) NOT NULL,
                descripcion TEXT,
                precio DECIMAL(10, 2) NOT NULL,
                stock INT DEFAULT 0,
                categoria_id INT NOT NULL,
                fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (categoria_id) REFERENCES categorias(id)
            )
        ''')
        
        # Tabla de clientes
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS clientes (
                id INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(100) NOT NULL,
                email VARCHAR(100) UNIQUE,
                telefono VARCHAR(20),
                direccion VARCHAR(200),
                ciudad VARCHAR(50),
                fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
        ''')
        
        # Tabla de órdenes
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS ordenes (
                id INT AUTO_INCREMENT PRIMARY KEY,
                cliente_id INT NOT NULL,
                fecha_orden TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                total DECIMAL(10, 2) NOT NULL,
                estado ENUM('pendiente', 'completada', 'cancelada') DEFAULT 'pendiente',
                FOREIGN KEY (cliente_id) REFERENCES clientes(id)
            )
        ''')
        
        # Tabla de detalles de orden
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS orden_detalles (
                id INT AUTO_INCREMENT PRIMARY KEY,
                orden_id INT NOT NULL,
                producto_id INT NOT NULL,
                cantidad INT NOT NULL,
                precio_unitario DECIMAL(10, 2) NOT NULL,
                subtotal DECIMAL(10, 2) NOT NULL,
                FOREIGN KEY (orden_id) REFERENCES ordenes(id),
                FOREIGN KEY (producto_id) REFERENCES productos(id)
            )
        ''')
        
        conexion.commit()
        print("✓ Tablas creadas exitosamente")
        
        cursor.close()
        conexion.close()
    
    # ========== CATEGORÍAS ==========
    
    def crear_categoria(self, nombre, descripcion=''):
        """Agrega una nueva categoría"""
        conexion = self.conectar()
        if not conexion:
            return
        
        cursor = conexion.cursor()
        
        try:
            cursor.execute('''
                INSERT INTO categorias (nombre, descripcion)
                VALUES (%s, %s)
            ''', (nombre, descripcion))
            
            conexion.commit()
            print(f"✓ Categoría '{nombre}' creada")
            return cursor.lastrowid
        except Error as e:
            print(f"✗ Error: {e}")
            return None
        finally:
            cursor.close()
            conexion.close()
    
    def listar_categorias(self):
        """Lista todas las categorías"""
        conexion = self.conectar()
        if not conexion:
            return []
        
        cursor = conexion.cursor(dictionary=True)
        cursor.execute('SELECT * FROM categorias ORDER BY nombre')
        categorias = cursor.fetchall()
        
        cursor.close()
        conexion.close()
        
        return categorias
    
    # ========== PRODUCTOS ==========
    
    def crear_producto(self, nombre, precio, stock, categoria_id, descripcion=''):
        """Agrega un nuevo producto"""
        conexion = self.conectar()
        if not conexion:
            return
        
        cursor = conexion.cursor()
        
        try:
            cursor.execute('''
                INSERT INTO productos (nombre, descripcion, precio, stock, categoria_id)
                VALUES (%s, %s, %s, %s, %s)
            ''', (nombre, descripcion, precio, stock, categoria_id))
            
            conexion.commit()
            print(f"✓ Producto '{nombre}' creado")
            return cursor.lastrowid
        except Error as e:
            print(f"✗ Error: {e}")
            return None
        finally:
            cursor.close()
            conexion.close()
    
    def listar_productos(self, categoria_id=None):
        """Lista productos, opcionalmente filtrados por categoría"""
        conexion = self.conectar()
        if not conexion:
            return []
        
        cursor = conexion.cursor(dictionary=True)
        
        if categoria_id:
            cursor.execute('''
                SELECT p.*, c.nombre as categoria
                FROM productos p
                JOIN categorias c ON p.categoria_id = c.id
                WHERE p.categoria_id = %s
                ORDER BY p.nombre
            ''', (categoria_id,))
        else:
            cursor.execute('''
                SELECT p.*, c.nombre as categoria
                FROM productos p
                JOIN categorias c ON p.categoria_id = c.id
                ORDER BY p.nombre
            ''')
        
        productos = cursor.fetchall()
        cursor.close()
        conexion.close()
        
        return productos
    
    def obtener_producto(self, producto_id):
        """Obtiene detalles de un producto específico"""
        conexion = self.conectar()
        if not conexion:
            return None
        
        cursor = conexion.cursor(dictionary=True)
        cursor.execute('''
            SELECT p.*, c.nombre as categoria
            FROM productos p
            JOIN categorias c ON p.categoria_id = c.id
            WHERE p.id = %s
        ''', (producto_id,))
        
        producto = cursor.fetchone()
        cursor.close()
        conexion.close()
        
        return producto
    
    def actualizar_stock(self, producto_id, cantidad):
        """Actualiza el stock de un producto"""
        conexion = self.conectar()
        if not conexion:
            return False
        
        cursor = conexion.cursor()
        
        try:
            cursor.execute('''
                UPDATE productos SET stock = stock + %s WHERE id = %s
            ''', (cantidad, producto_id))
            
            conexion.commit()
            return cursor.rowcount > 0
        except Error as e:
            print(f"✗ Error: {e}")
            return False
        finally:
            cursor.close()
            conexion.close()
    
    # ========== CLIENTES ==========
    
    def crear_cliente(self, nombre, email='', telefono='', direccion='', ciudad=''):
        """Registra un nuevo cliente"""
        conexion = self.conectar()
        if not conexion:
            return
        
        cursor = conexion.cursor()
        
        try:
            cursor.execute('''
                INSERT INTO clientes (nombre, email, telefono, direccion, ciudad)
                VALUES (%s, %s, %s, %s, %s)
            ''', (nombre, email, telefono, direccion, ciudad))
            
            conexion.commit()
            print(f"✓ Cliente '{nombre}' registrado")
            return cursor.lastrowid
        except Error as e:
            print(f"✗ Error: {e}")
            return None
        finally:
            cursor.close()
            conexion.close()
    
    def listar_clientes(self):
        """Lista todos los clientes"""
        conexion = self.conectar()
        if not conexion:
            return []
        
        cursor = conexion.cursor(dictionary=True)
        cursor.execute('SELECT * FROM clientes ORDER BY nombre')
        clientes = cursor.fetchall()
        
        cursor.close()
        conexion.close()
        
        return clientes
    
    # ========== ÓRDENES ==========
    
    def crear_orden(self, cliente_id, items):
        """
        Crea una nueva orden
        items: lista de tuplas (producto_id, cantidad)
        """
        conexion = self.conectar()
        if not conexion:
            return
        
        cursor = conexion.cursor()
        total = Decimal('0')
        
        try:
            # Calcular total
            for producto_id, cantidad in items:
                producto = self.obtener_producto(producto_id)
                if not producto:
                    print(f"✗ Producto {producto_id} no encontrado")
                    return None
                
                if producto['stock'] < cantidad:
                    print(f"✗ Stock insuficiente para {producto['nombre']}")
                    return None
                
                total += Decimal(str(producto['precio'])) * cantidad
            
            # Crear orden
            cursor.execute('''
                INSERT INTO ordenes (cliente_id, total)
                VALUES (%s, %s)
            ''', (cliente_id, total))
            
            orden_id = cursor.lastrowid
            
            # Crear detalles de orden
            for producto_id, cantidad in items:
                producto = self.obtener_producto(producto_id)
                precio_unitario = Decimal(str(producto['precio']))
                subtotal = precio_unitario * cantidad
                
                cursor.execute('''
                    INSERT INTO orden_detalles (orden_id, producto_id, cantidad, precio_unitario, subtotal)
                    VALUES (%s, %s, %s, %s, %s)
                ''', (orden_id, producto_id, cantidad, precio_unitario, subtotal))
                
                # Actualizar stock
                self.actualizar_stock(producto_id, -cantidad)
            
            conexion.commit()
            print(f"✓ Orden #{orden_id} creada - Total: ${total}")
            return orden_id
        
        except Error as e:
            print(f"✗ Error: {e}")
            conexion.rollback()
            return None
        finally:
            cursor.close()
            conexion.close()
    
    def listar_ordenes(self, cliente_id=None):
        """Lista órdenes"""
        conexion = self.conectar()
        if not conexion:
            return []
        
        cursor = conexion.cursor(dictionary=True)
        
        if cliente_id:
            cursor.execute('''
                SELECT o.*, c.nombre as cliente_nombre
                FROM ordenes o
                JOIN clientes c ON o.cliente_id = c.id
                WHERE o.cliente_id = %s
                ORDER BY o.fecha_orden DESC
            ''', (cliente_id,))
        else:
            cursor.execute('''
                SELECT o.*, c.nombre as cliente_nombre
                FROM ordenes o
                JOIN clientes c ON o.cliente_id = c.id
                ORDER BY o.fecha_orden DESC
            ''')
        
        ordenes = cursor.fetchall()
        cursor.close()
        conexion.close()
        
        return ordenes
    
    def obtener_detalles_orden(self, orden_id):
        """Obtiene los detalles de una orden"""
        conexion = self.conectar()
        if not conexion:
            return []
        
        cursor = conexion.cursor(dictionary=True)
        cursor.execute('''
            SELECT od.*, p.nombre as producto_nombre
            FROM orden_detalles od
            JOIN productos p ON od.producto_id = p.id
            WHERE od.orden_id = %s
        ''', (orden_id,))
        
        detalles = cursor.fetchall()
        cursor.close()
        conexion.close()
        
        return detalles
    
    # ========== REPORTES ==========
    
    def reporte_ventas(self):
        """Genera reporte de ventas"""
        conexion = self.conectar()
        if not conexion:
            return
        
        cursor = conexion.cursor(dictionary=True)
        
        # Total de ventas
        cursor.execute('SELECT SUM(total) as total FROM ordenes WHERE estado="completada"')
        resultado = cursor.fetchone()
        total_ventas = resultado['total'] or 0
        
        # Productos más vendidos
        cursor.execute('''
            SELECT p.nombre, SUM(od.cantidad) as cantidad_vendida, SUM(od.subtotal) as monto
            FROM orden_detalles od
            JOIN productos p ON od.producto_id = p.id
            GROUP BY p.id
            ORDER BY monto DESC
            LIMIT 10
        ''')
        
        top_productos = cursor.fetchall()
        
        # Clientes principales
        cursor.execute('''
            SELECT c.nombre, COUNT(o.id) as ordenes, SUM(o.total) as monto_total
            FROM ordenes o
            JOIN clientes c ON o.cliente_id = c.id
            GROUP BY c.id
            ORDER BY monto_total DESC
            LIMIT 5
        ''')
        
        top_clientes = cursor.fetchall()
        
        cursor.close()
        conexion.close()
        
        print("\n" + "="*60)
        print("REPORTE DE VENTAS")
        print("="*60)
        print(f"Total de Ventas: ${total_ventas:.2f}")
        
        print("\n📊 Top 10 Productos Vendidos:")
        for p in top_productos:
            print(f"  {p['nombre']}: {p['cantidad_vendida']} unidades (${p['monto']:.2f})")
        
        print("\n👥 Top 5 Clientes:")
        for c in top_clientes:
            print(f"  {c['nombre']}: {c['ordenes']} órdenes (${c['monto_total']:.2f})")
        print("="*60 + "\n")
    
    def productos_bajo_stock(self, minimo=10):
        """Lista productos con stock bajo"""
        conexion = self.conectar()
        if not conexion:
            return []
        
        cursor = conexion.cursor(dictionary=True)
        cursor.execute('''
            SELECT * FROM productos
            WHERE stock < %s
            ORDER BY stock
        ''', (minimo,))
        
        productos = cursor.fetchall()
        cursor.close()
        conexion.close()
        
        return productos


# ============ MENÚ INTERACTIVO ============

def menu():
    """Menú principal"""
    tienda = TiendaDB()
    
    while True:
        print("\n" + "="*50)
        print("🏪 SISTEMA DE TIENDA 🏪")
        print("="*50)
        print("1. Gestión de Categorías")
        print("2. Gestión de Productos")
        print("3. Gestión de Clientes")
        print("4. Gestión de Órdenes")
        print("5. Reportes")
        print("6. Inicializar BD (crear tablas)")
        print("7. Salir")
        print("="*50)
        
        opcion = input("Selecciona opción (1-7): ").strip()
        
        if opcion == '1':
            menu_categorias(tienda)
        elif opcion == '2':
            menu_productos(tienda)
        elif opcion == '3':
            menu_clientes(tienda)
        elif opcion == '4':
            menu_ordenes(tienda)
        elif opcion == '5':
            tienda.reporte_ventas()
        elif opcion == '6':
            tienda.crear_tablas()
        elif opcion == '7':
            print("¡Hasta luego! 👋")
            break
        else:
            print("✗ Opción no válida")


def menu_categorias(tienda):
    """Menú de categorías"""
    while True:
        print("\n--- Gestión de Categorías ---")
        print("1. Crear categoría")
        print("2. Listar categorías")
        print("3. Volver")
        
        opcion = input("Opción (1-3): ").strip()
        
        if opcion == '1':
            nombre = input("Nombre: ").strip()
            desc = input("Descripción: ").strip()
            tienda.crear_categoria(nombre, desc)
        elif opcion == '2':
            categorias = tienda.listar_categorias()
            for cat in categorias:
                print(f"  [{cat['id']}] {cat['nombre']}")
        elif opcion == '3':
            break


def menu_productos(tienda):
    """Menú de productos"""
    while True:
        print("\n--- Gestión de Productos ---")
        print("1. Crear producto")
        print("2. Listar productos")
        print("3. Ver producto")
        print("4. Volver")
        
        opcion = input("Opción (1-4): ").strip()
        
        if opcion == '1':
            nombre = input("Nombre: ").strip()
            try:
                precio = float(input("Precio: ").strip())
                stock = int(input("Stock: ").strip())
                
                categorias = tienda.listar_categorias()
                for cat in categorias:
                    print(f"  [{cat['id']}] {cat['nombre']}")
                
                cat_id = int(input("ID Categoría: ").strip())
                desc = input("Descripción: ").strip()
                
                tienda.crear_producto(nombre, precio, stock, cat_id, desc)
            except ValueError:
                print("✗ Datos inválidos")
        
        elif opcion == '2':
            productos = tienda.listar_productos()
            for prod in productos:
                print(f"  [{prod['id']}] {prod['nombre']} - ${prod['precio']:.2f} (Stock: {prod['stock']})")
        
        elif opcion == '3':
            try:
                prod_id = int(input("ID Producto: ").strip())
                prod = tienda.obtener_producto(prod_id)
                if prod:
                    print(f"\n{prod['nombre']}")
                    print(f"  Categoría: {prod['categoria']}")
                    print(f"  Precio: ${prod['precio']:.2f}")
                    print(f"  Stock: {prod['stock']}")
                    print(f"  Descripción: {prod['descripcion']}")
            except ValueError:
                print("✗ ID inválido")
        
        elif opcion == '4':
            break


def menu_clientes(tienda):
    """Menú de clientes"""
    while True:
        print("\n--- Gestión de Clientes ---")
        print("1. Registrar cliente")
        print("2. Listar clientes")
        print("3. Ver órdenes del cliente")
        print("4. Volver")
        
        opcion = input("Opción (1-4): ").strip()
        
        if opcion == '1':
            nombre = input("Nombre: ").strip()
            email = input("Email: ").strip()
            tel = input("Teléfono: ").strip()
            dir = input("Dirección: ").strip()
            ciudad = input("Ciudad: ").strip()
            
            tienda.crear_cliente(nombre, email, tel, dir, ciudad)
        
        elif opcion == '2':
            clientes = tienda.listar_clientes()
            for cli in clientes:
                print(f"  [{cli['id']}] {cli['nombre']} - {cli['email']}")
        
        elif opcion == '3':
            try:
                cli_id = int(input("ID Cliente: ").strip())
                ordenes = tienda.listar_ordenes(cli_id)
                for ord in ordenes:
                    print(f"  Orden #{ord['id']}: ${ord['total']:.2f} ({ord['estado']})")
            except ValueError:
                print("✗ ID inválido")
        
        elif opcion == '4':
            break


def menu_ordenes(tienda):
    """Menú de órdenes"""
    while True:
        print("\n--- Gestión de Órdenes ---")
        print("1. Crear orden")
        print("2. Listar órdenes")
        print("3. Ver detalles de orden")
        print("4. Ver productos con stock bajo")
        print("5. Volver")
        
        opcion = input("Opción (1-5): ").strip()
        
        if opcion == '1':
            try:
                cli_id = int(input("ID Cliente: ").strip())
                items = []
                
                while True:
                    prod_id = int(input("ID Producto (0 para terminar): ").strip())
                    if prod_id == 0:
                        break
                    cantidad = int(input("Cantidad: ").strip())
                    items.append((prod_id, cantidad))
                
                if items:
                    tienda.crear_orden(cli_id, items)
            except ValueError:
                print("✗ Datos inválidos")
        
        elif opcion == '2':
            ordenes = tienda.listar_ordenes()
            for ord in ordenes:
                print(f"  Orden #{ord['id']}: {ord['cliente_nombre']} - ${ord['total']:.2f}")
        
        elif opcion == '3':
            try:
                ord_id = int(input("ID Orden: ").strip())
                detalles = tienda.obtener_detalles_orden(ord_id)
                for det in detalles:
                    print(f"    {det['producto_nombre']}: {det['cantidad']} x ${det['precio_unitario']:.2f}")
            except ValueError:
                print("✗ ID inválido")
        
        elif opcion == '4':
            productos = tienda.productos_bajo_stock()
            if productos:
                print("\n⚠️  Productos con stock bajo:")
                for prod in productos:
                    print(f"  {prod['nombre']}: {prod['stock']} unidades")
            else:
                print("✓ Todos los productos tienen stock suficiente")
        
        elif opcion == '5':
            break


if __name__ == '__main__':
    menu()
