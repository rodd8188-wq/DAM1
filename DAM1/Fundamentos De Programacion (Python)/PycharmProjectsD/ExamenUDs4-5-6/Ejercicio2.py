def analizar_lecturas(temp1,temp2,temp3,temp4,temp5):
    cont = 0
    lista=[]
    diccionario={
    }
    try:
        if temp1 > 100:
            cont = cont + 1
        lista.append(temp1)
    except:
        if not type(temp1) is int:
            print("Primera lectura")
            print("Dato omitido: tipo no válido")
    finally:
        try:
            if temp2 > 100:
                cont = cont + 1
            lista.append(temp2)
        except:
            if not type(temp2) is int:
                print("Segunda lectura")
                print("Dato omitido: tipo no válido")
        finally:
            try:
                if temp3 > 100:
                    cont = cont + 1
                lista.append(temp3)
            except:
                if not type(temp3) is int:
                    print("Tercera lectura")
                    print("Dato omitido: tipo no válido")
            finally:
                try:
                    if temp4 > 100:
                        cont = cont + 1
                    lista.append(temp4)
                except:
                    if not type(temp4) is int:
                        print("Cuarta lectura")
                        print("Dato omitido: tipo no válido")
                finally:
                    try:
                        if temp5 > 100:
                            cont = cont + 1
                        lista.append(temp5)
                    except:
                        if not type(temp5) is int:
                            print("Quinta lectura")
                            print("Dato omitido: tipo no válido")
                    finally:
                        if cont > 2:
                            raise Exception("Hay un mal funcionamiento con el sensor")
                        diccionario = {
                            "suma": calcular_suma(lista),
                            "máximo": calcular_max(lista),
                            "mínimo": calcular_min(lista),
                        }
                        print(diccionario)
def calcular_suma(lista):
    suma = 0
    for i in lista:
        suma = suma + i
    return suma
def calcular_max(lista):
    lista.sort(reverse=True)
    max = lista[0]
    return max
def calcular_min(lista):
    lista.sort()
    min = lista[0]
    return min

try:
    analizar_lecturas(25.5, 30, "error", 120, 18)
except:
    analizar_lecturas(0,0,0,0,0)
finally:
    print("Cierre de conexión con el sensor")