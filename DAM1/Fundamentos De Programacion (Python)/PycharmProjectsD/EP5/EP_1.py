import random

def ejercicio1():
    list= []
    for i in range(0,10):
        num = random.randint(1,500)
        list.append(num)
    no = False
    print (list)
    list.sort();
    num_introducido=int(input("Escribe un número: "))
    for i in range(0,10):
        if list[i]==num_introducido:
            print("Adivinaste uno de los números de la lista!")
            a = i

        elif list[i]>num_introducido:
            no= True
            a=i
    if no ==True:
        print("No es el número elegido")
    print("Hay", a ,"números mayores al que has elegido")


def ejercicio2():
    lista = []
    num=0
    while num>=0:
        num =int(input('Introduce un numero: '))
        lista.append(num)
    lista.sort()
    print(lista)
    lista.sort(reverse=True)
    print(lista)


def ejercicio4():
    print("Calindarioinador versión 1.0")
    mes_correcto=False
    while mes_correcto==False:
        num_mes =int(input("Introduce el número de mes(entre 1-12):"))
        if num_mes<=12 and num_mes>=1:
            mes_correcto=True
        else:
            print("Entrada incorrecta, Vuelve a intentarlo")

    num_ano=int(input("Introduce el año: "))
    calendario= [["Enero",31 ],["Febrero",28 ],["Marzo",31 ],["Abril",30 ],["Mayo",31 ],["Junio",30 ],["Julio",31 ],["Agosto",31 ],["Septiembre",30 ],["Octubre",31 ],["Noviembre",30 ],["Diciembre",31 ]]
    if num_ano%4==0:
        calendario[1][1]=29
    print("Has seleccionado el mes de",calendario[num_mes-1][0],"y tiene",calendario[num_mes-1][1], "días")
    input("Presione ENTER para terminar: ")


def ejercicio5():
    print("Loteriadesiempreinador versión 1.0")
    print("Los números ganadores son:")
    lista=[]
    while(len(lista)<6):
        num = random.randint(1, 49)
        if num not in lista:
            'if lista.count(num)==0:'
            lista.append(num)
    reintegro= random.randint(0,9)
    lista.sort()
    print(lista,"\nY el número de reintegro será: ",reintegro)



if __name__ == '__main__':
    #ejercicio1()#corrige mrd
    #ejercicio2()
    #ejercicio3()
    #ejercicio4()
    ejercicio5()
    #ejercicio7()
    #ejercicio12()
    #ejercicio16()