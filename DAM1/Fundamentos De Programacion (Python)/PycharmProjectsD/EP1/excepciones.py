import re
def main():
    print("excepcionesTryExcept versión 1.0")
    print("---------------------------------\n1. Division por cero\n2. Conversión de tipos Segura\n3. Guardián de listas\n4. Cajero automatico")
    try:
        eleccion=int(input("Introduce la funcion a elegir: "))
        if 0<eleccion<5:
            if eleccion==1:
                resultado= divisionCero()
                print(resultado)
            elif eleccion==2:
                conversionEnteros()
            elif eleccion==3:
                guardian_istas()
            elif eleccion==4:
                cajero_automatico()
        else:
            print("El numero no es valido")
    except ValueError:
        print("El numero no es valido")
    input("Presiona ENTER para terminar:")


def divisionCero():
    dividendo= int(input("Introduce el numero de dividendo: "))
    try:
        divisor = int(input("Introduce el numero de divisor: "))
    except ValueError:
        print("El numero no es valido")
    except ZeroDivisionError:
        print("No se puede dividir con un divisor cero\nQue exploto!!!!")
    return dividendo/divisor

def conversionEnteros():
    lista_entrada = ["10", "hola", "20", "3.5"]
    print(lista_entrada)
    lista_numeros = convertir_a_entero(lista_entrada)
    print(lista_numeros)

def convertir_a_entero(lista = []):
    resultado = []
    for item in lista:
        try:
            numero = int(item)
            resultado.append(numero)
        except ValueError:
            pass
        '''else:
            resultado.append(numero)'''
    return resultado


def guardian_istas():
    colores=["red", "green", "blue", "yellow", "magenta", "cyan", "white"]
    print("Colores: ", colores)
    try:
        posicion = int(input("Dime una posicion"))
        print(colores[posicion])
    except ValueError:
        print("El numero no eh valido")
    except IndexError:
        print(f"Te pasaste brother, Solo tenemos {len(colores)} Posiciones")


def cajero_automatico():
    saldo=1000
    print("Saldo total:", saldo)
    try:
        '''retirada = int(input("Introduce la candidad a retirar: "))
        retirada = validar_retirado(retirada, saldo)'''
        retirada = validar_retirado(input("Introduce la cantidad a retirar: "), saldo)
        if retirada > saldo:
            raise Exception("La retirada no puede ser mayor que el saldo")
        saldo -= retirada
    except ValueError as e:
        print(e)
    else:
        print("Saldo restante: ", saldo)
        print("Efectivo: ", retirada)
    print("Gracias por usar nuestro cajero!")

def validar_retirado(retirada, saldoActual):
    retirada = int(retirada)
    if retirada <0:
        raise ValueError("No se pueden retirar cantidades negativas")
    if retirada %20 !=0 or retirada % 50 !=0:
        raise ValueError("Solo múltiplos de 20 o 50")
    if retirada>saldoActual:
        raise ValueError("La retirada no puede ser mayor que el saldo")
    return retirada

if __name__== "__main__":
    main()
else:
    print("Soy un módulo cargado")