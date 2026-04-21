import re

from PrimehProyecto.EP1.excepciones import validar_retirado


def main():
    print("***Examen del segundo trimestre***\n **Hecho por: Martín Taboada**\n")
    fin = False
    while not fin:
        print("----------------------------------\n"
              " 1.- Ejercicio1: Gestión de \n     inventario.\n"
              " 2.- Ejercicio2: Validador de \n     Telemetría.\n"
              "----------------------------------\n")
        entrada = input("\nIngrese el numero de entrada: ")
        fin = validacion(entrada)
    if entrada == "1":
        gestionInventario()
    else:
        validadorTelemetria()
    input("Fin del programa, presione ENTER para continuar: ")
def validacion(entrada):
    si= False
    patron = "[12]{1}"
    if re.search(patron, entrada):
        si = True
    else:
        si = False
        print("Valor no válido, intentalo de nuevo\n\n")
    return si

def gestionInventario():
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
    nuevo_inventario=limpiar_inventario(inventario)
    try:
        n1 = int(input("Ingrese precio minimo a filtrar: "))
        n2 = int(input("Ingrese precio maximo filtrar: "))
    except ValueError:
        print("Ingrese un numero valido")
    else:
        if n1 > n2:
            print("Error, el primer numero es mas grande que el segundo, no se puede filtrar")
        else:
            nuevo_inventario2 = filtrar_precio_entre(n1, n2, nuevo_inventario)


def limpiar_inventario(lista_datos):
    diccionario_comprim = {}
    for item in lista_datos:
        nombre = item["nombre"]
        precio = item["precio"]
        valor = {nombre: precio}
        diccionario_comprim[item["id"]] = valor
    print(diccionario_comprim)
    return diccionario_comprim


def filtrar_precio_entre(n1, n2, nuevo_inventario):
    p1= 0
    p2= 8000
    valor={}
    lista_tuplas = []
    p1=n1
    if n2 != 0:
        p2=n2
    keysinv= nuevo_inventario.keys()
    for key in keysinv:
            valor= nuevo_inventario[key]
            keysval = valor.keys()

            for keyval in keysval:
                if p1<keysval[keyval]<p2:
                    tupla= valor.items()
                    print(tupla)
                    lista_tuplas.append(tupla)
    print (lista_tuplas)
    return lista_tuplas







def validadorTelemetria():
    si=False

def analizar_lecturas():


if __name__== "__main__":
    main()
else:
    print("Soy un módulo cargado")
