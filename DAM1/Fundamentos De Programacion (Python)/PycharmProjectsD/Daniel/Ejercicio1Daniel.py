# Lista de inventario inicial para el Ejercicio 1
inventario = [
    {"id": 101, "nombre": "Laptop Pro 16", "precio": 1450.00, "stock": 5},
    {"id": 102, "nombre": "Ratón Inalámbrico", "precio": 25.99, "stock": 50},
    {"id": 103, "nombre": "Monitor 4K 27\"", "precio": 380.50, "stock": 12},
    {"id": 104, "nombre": "Smartphone Alpha", "precio": 899.00, "stock": 8},
    {"id": 105, "nombre": "Teclado Mecánico RGB", "precio": 120.00, "stock": 20},
    {"id": 106, "nombre": "Tarjeta Gráfica RTX", "precio": 650.00, "stock": 4},
    {"id": 107, "nombre": "Auriculares Noise Cancelling", "precio": 210.00, "stock": 15},
    {"id": 108, "nombre": "Servidor NAS", "precio": 520.00, "stock": 2}
]
inventarioLimpio={
}
def limpiar_inventario(inventario):
    for i in range(len(inventario)):
        id = inventario[i]["id"]
        idCont = inventario[i]["id"]
        nombre = inventario[i]["nombre"]
        precio = inventario[i]["precio"]
        idCont = {
            "nombre": nombre,
            "precio": precio
        }
        inventario[i] = i
        inventarioLimpio.update({ id : idCont })

def filtrar_precio_entre(inventarioLimpio,min,max):
    if max in range(1, 8000):
        max = max
    else:
       max = 8000
    if max < min:
        max = 8000

    lista = []
    id= 101
    for i in range(len(inventarioLimpio)):
        if inventarioLimpio[id]["precio"] < max and inventarioLimpio[id]["precio"] > min:
            tupla = (inventarioLimpio[id]["nombre"], inventarioLimpio[id]["precio"])
            lista.append(tupla)
        id = id + 1
    for x in range(len(lista)):
        print(lista[x])

##Ejercicio 1
#Llamar a la función y mostrar las claves y su contenido del diccionario
'''
limpiar_inventario(inventario)
print(inventarioLimpio.keys())
for i in inventarioLimpio:
    print(inventarioLimpio[i])
'''

##Ejercicio2
#Lamar a la función para crear el diccionario limpio y luego llamar a la función para filtrar por precio

limpiar_inventario(inventario)
filtrar_precio_entre(inventarioLimpio,0,8000)
