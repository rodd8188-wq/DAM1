


def ejercicio1 ():
    print('ejercicio 1\nnumerosordenadosinador versión 1.0')
    n1 = int(input('Por favor, introduce tres números\nPrimer número: '))
    n2 = int(input('Segundo número: '))
    n3 = int(input('Tercer número: '))
    if n1>n2:
        temp=n1
        n1=n2
        n2=temp
    if n1>n3:
        temp=n1
        n1=n3
        n3=temp
    if n2>n3:
        temp=n2
        n2=n3
        n3=temp
    print('El orden de los números de menor a mayor es:\n', n1,', ',n2,', ',n3,'.')
    input('Fin del programa, presione ENTER para terminar: ')

def ejercicio2():
    print('ejercicio 2\nApellidosordenadosinador versión 1.0')
    lista=[]
    n1 = str(input('Por favor, introduce tres apellidos\nPrimer apellido: '))
    n2 = input('Segundo apellido: ')
    n3 = input('Tercer apellido: ')
    lista.append(n1)
    lista.append(n2)
    lista.append(n3)
    lista.sort()
    print('El orden de los apellidos alfabéticamente es:\n',lista)
    input('Fin del programa, presione ENTER para terminar: ')

def ejercicio8():
    print("Comprobadordeclavesinador versión 1.0")
    coincidencia = False
    while not coincidencia:
        pasw1= input("Por favor, introduce la contraseña: ")
        pasw2= input("Repite la contraseña: ")
        if pasw1 in pasw2:
            print("Las contraseñas coinciden.")
            coincidencia=True
        else:
            print("Las contraseñas no coinciden\nVuelve a intentarlo...")
    input("Presione ENTER para terminar: ")





if __name__=='__main__':
    #ejercicio1()
    ejercicio2()
    #ejercicio8()
