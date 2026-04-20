def convertirEntero(lista):
    resultado = []
    for el in lista:
        try:
            num = int(el)
            resultado.append(num)
        except ValueError:
            pass
    return resultado

lis = ["20", "daniel", "29", "10.1"]

print(convertirEntero(lis))