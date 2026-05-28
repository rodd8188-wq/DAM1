
lista_paises = {}

finalizar = False
while finalizar == False:
    nombre = str(input("Nombre del país: "))
    if nombre == "-1":
        finalizar = True
    else:
        poblacion_erroena = True
        while poblacion_erroena == True:
            try:
                poblacion = int(input("Poblacion (Millones): "))
                poblacion_erroena = False
                lista_paises[nombre] = poblacion
            except:
                print("Poblacion invalida")
lista_paises_ordenada = sorted(lista_paises.items(),
                               key=lambda item: item[1],
                               reverse=True)
print(lista_paises_ordenada)