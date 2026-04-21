import random

def ejercicio1():
    print('ejercicio 1\nnumerosdelunoal10inador versión 1.0')
    a=10
    b=1
    while b <=a:
        print(b)
        b+=1
    for i in range (11):
        print (i, end= ' ')

def ejercicio2():
    print('Ejercicio 2\n50numerosparesinador version 1.0')
    num=2
    numfor= 2
    a=0
    b=50
    while a<b:
        print(num)
        a+=1
        num+=2
    for i in range(b):
        if i==0:
            print (numfor, end='')
        else:
            print(', ', numfor, end="")
        numfor+=2
    '''for i in 50:
        print(num)'''

def ejercicio3():
    print('Ejercicio 3\n5multiplosdeunodadinador version 1.0')
    num = int(input('Introduce un número: '))
    print('Los números múltiplos de', num, 'son:')
    for i in range (7):
        if i>1:
            print(num*i)

def ejercicio4():
    print('Ejercicio4\nnumerosdivisiblespor7menoresa1000inador versión 1.0\nLos números divisibles para 7 menores a 100 son: ')
    a = 7
    while a<1000:
        if a%7==0:
            if a==7:
                print(a, end="")
            else:
                print(',',a,end="")

        a+=1

def ejercicio7():
    print('Calculadordeivainador versión 1.0')
    num = float(input('Por favor introduce el precio: '))
    print('El precio más el IVA(21%) es:', num*1.21)

def ejercicio12():
    print('Loteríaprimitivainador version 1.0')
    for i in range(6):
        num = random.randint(1,49)
        print(num)
    input('Presione ENTER para terminar: ')

def ejercicio16():
    print('Ejercicio 16\nNumeroprimorandomentre1e7y5e7inador version 1.0')
    primo = True
    while primo:
        num=random.randint(int(1e7),int(5e7))
        for i in range(2, int(num/2)):
            if num%i !=0:
                primo=False
    print(num)

if __name__ == '__main__':
    #ejercicio1()
    #ejercicio2()
    #ejercicio3()
    #ejercicio4()
    #ejercicio7()
    #ejercicio12()
    ejercicio16()

