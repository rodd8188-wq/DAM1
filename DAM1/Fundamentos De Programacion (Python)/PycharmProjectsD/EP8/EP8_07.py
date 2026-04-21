from random import randint
def main():
    print("EnciptadoryDesencriptadordeMensajesinador versión 1.7.2")
    dic_cif=cifrado()
    si= False
    while not si:
        opcion= input("Quiere encriptar, desencriptar, mostrar diccionario o salir?(E/D/M/S): ")
        opcion = opcion.upper()
        if opcion == "E":
            encriptacion(dic_cif)
        elif opcion == "D":
            desencriptar(dic_cif)
        elif opcion == "M":
            print(dic_cif)
        elif opcion == "S":
            si= True
        else:
            print("Opcion invalida")


def encriptacion(dic_cif):
    mensaje = input("Escribe el mensaje a encriptar: ")
    print(menscifrado(dic_cif, mensaje))


def cifrado():
    dick={}
    abc= "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
    for i in abc:
        dick.update({i:9})
    for i in dick:
        random = randint(10, 99)
        while(random in dick.values()):
            random = randint(10, 99)
        dick.update({i:random})
    return dick

def menscifrado(dic, mens):
    mens=(mens.upper())
    mens_cif=""
    for i in mens:
        n= dic[i]
        mens_cif+=str(n)
    mens_cif_extra=""
    for i in range (len(mens_cif)):
        if(i %3 == 0):
            mens_cif_extra+=" "
            mens_cif_extra+=mens_cif[i]
        else:
            mens_cif_extra+=mens_cif[i]
    mens_cif_extra_s=mens_cif_extra.replace(" ","")

    while len(mens_cif_extra_s)%3!=0:
        mens_cif_extra+= str(randint(1,9))
        mens_cif_extra_s=mens_cif_extra.replace(" ","")
    return mens_cif_extra



def desencriptar(dic_cif):
    mens_encriptado= str(input("Escribe el mensaje a desencriptar: "))
    mens_encriptado= mens_encriptado.replace(" ","")
    mens_desencriptado=""
    while len(mens_encriptado)%2 != 0:
        mens_encriptado = mens_encriptado[:len(mens_encriptado)-1]
    for i in range(0, len(mens_encriptado), 2):
        num= int(mens_encriptado[i:i+2])
        clave = next((k for k, v in dic_cif.items() if v == num), None)
        mens_desencriptado+=clave


    print(mens_desencriptado)




if __name__== "__main__":
    main()
else:
    print("Soy un módulo cargado")
