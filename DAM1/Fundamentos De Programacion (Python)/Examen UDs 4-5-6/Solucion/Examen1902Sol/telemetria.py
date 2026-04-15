def suma(lista_temperaturas):
    suma = 0
    for temperatura in lista_temperaturas:
        suma += temperatura
    return suma


def promedio(lista_temperaturas):
    return suma(lista_temperaturas)/len(lista_temperaturas)


def analizar_lecturas(*temperaturas):
    if len(temperaturas) == 0:
        return {
            "none":0
        }
    contador_cien = 0
    lista_temperaturas = []
    for temperatura in temperaturas:
        try:
            temperatura = float(temperatura)
        except:
            print("Dato omitido: tipo no válido.")
        else:
            if temperatura > 100:
                contador_cien += 1
                if contador_cien > 2:
                    raise Exception("Mal funcionamiento del sensor")
            else:
                lista_temperaturas.append(temperatura)
    diccionario_resultados = {}
    diccionario_resultados["suma"] = suma(lista_temperaturas)
    diccionario_resultados["promedio"] = promedio(lista_temperaturas)
    diccionario_resultados["maximo"] = max(lista_temperaturas)
    diccionario_resultados["minimo"] = min(lista_temperaturas)
    return diccionario_resultados

try:
    resultados = analizar_lecturas(20.5,50,100,36,"error",2)
    print("Todo bien:",resultados)
    resultados = analizar_lecturas(20.5,50,120,36,"error",2)
    print("temperatura por encima de 100:",resultados)
    #resultados = analizar_lecturas(20.5,50,120,360,"error",200)
    #print("temperatura por encima de 100:",resultados)
    print(analizar_lecturas())
except Exception as e:
    print(e)
finally:
    print("Cierre de conexión")