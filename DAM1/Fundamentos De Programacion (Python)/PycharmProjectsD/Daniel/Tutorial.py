Habilita
las
notificaciones
de
escritorio
para
Gmail.Aceptar
No, gracias
4
de
1.285
Apuntes
Python
20
feb
26
Recibidos
Dan
Pop

Archivos
adjuntosvie, 20
feb, 12: 24(hace
4
días)

Dan
Pop < rodd8188 @ gmail.com >

Archivos
adjuntosvie, 20
feb, 12: 33(hace
4
días)


para
aarias10112 @ gmail.com, nickmaverick07072007

---------- Forwarded
message - --------
De: Dan
Pop < rodd8188 @ gmail.com >
Date: vie, 20
feb
2026
a
las
12: 24
Subject: Apuntes
Python
20
feb
26
To: Dan
Pop < rodd8188 @ gmail.com >

Un
archivo
adjunto  •  Analizados
por
Gmail


def SlicingStrings():
    b = "Hello, World"
    print(b[2:5])


def ModifyStrings():
    a = " Hello, World "
    # A maysculas
    print(a.upper())
    # A minusculas
    print(a.lower())
    # Quita los espacios en blanco que este al principio y al final
    print(a.strip())


def ConcatenateStrings():
    a = "Hello"
    b = " "
    c = "World"
    d = a + b + c
    print(d)


def FormatStrings():
    name = "Daniel"
    age = 36
    txt = "My name is " + name + ". I am " + str(age) + " years old."
    print(txt)


def MethodsStrings():
    txt = "Hola mundo"
    # El primer caracter a mayscula.
    print(txt.capitalize())
    # Lo transforma a minusculas.
    print(txt.casefold())
    # Devuelve el texto centrado entre 20 caracteres y relleno con -.
    print(txt.center(20, "-"))
    # Cuenta cuantas veces se encuentra un caracter.
    print(txt.count("o"))
    # Te devuelve la primera poscición en la que se encuentre el caracter.
    print(txt.find("a"))
    # Inserta el formato en el lugar del formato
    txtFormat = "For only {price:.2f} dollars!"
    print(txtFormat.format(price=49))
    # Devuelve la posición en la lista en la que se encuentra
    txtIndex = ["texto1", "texto2", "texto3"]
    print(txtIndex.index("texto3"))
    # Devuelve true si todos los caracteres son alfanumericos
    print(txt.isnumeric())
    # Devuelve true si todos los caracteres son alfabeticos
    print(txt.isalpha())
    # Devuelve true si todos los caracteres estan en minuscula
    print(txt.islower())
    # Devuelve true si todos los caracteres estan en mayuscula
    print(txt.isupper())
    # Devuelve true si todos los caracteres son numericos
    print(txt.isnumeric())
    # Devuelve true si todos los caracteres son espacios en blanco
    print(txt.isspace())
    # Reemplaza un valor por otro
    print(txt.replace("Hola", "Adios"))
    # Te devuelve la ultima poscición en la que se encuentre el caracter.
    print(txt.rfind("a"))
    # Te devuelve true si la cadena empieza por ese caracter
    print(txt.startswith("H"))
    # Te devuelve true si la cadena termina por ese caracter
    print(txt.endswith("o"))
    # Cambia el primer caracter de cada palabra a mayuscula
    print(txt.title())
    # Rellena la cadena con ceros hasta que llegue a 20 caracteres en total
    print(txt.zfill(20))
    #
    x = ["apple", "banana"]
    y = ["apple", "banana"]
    print(x is not y)
    #
    lista = ["apple", "banana", "cherry"]
    print(len(lista))
    # Te devuelve el valor de la posición del valor especificado
    print(lista[-1])
    # Devuelve los valores de los que se encuentran entre esas posiciones sin incluirlas
    listaAccess = ["apple", "banana", "cherry", "orange", "kiwi", "melon", "mango"]
    print(listaAccess[2:5])
    # Sustituir una o varias posiciones de la lista por otras
    thislist = ["apple", "banana", "cherry", "orange", "kiwi", "mango"]
    thislist[1:3] = ["blackcurrant", "watermelon"]
    print(thislist)
    # Añade un valor al final de la lista
    listaAppend = ["apple", "banana", "cherry"]
    listaAppend.append("orange")
    print(listaAppend)
    # Añade el elemento en la lista en la posición dada
    listaInsert = ["apple", "banana", "cherry"]
    listaInsert.insert(1, "orange")
    print(listaInsert)
    # Elimina el elemento de la lista
    listaRemove = ["apple", "banana", "cherry"]
    listaRemove.remove("banana")
    print(listaRemove)
    # Elimina el elemento de la lista en la posición dada
    listaPop = ["apple", "banana", "cherry"]
    listaPop.pop(1)
    print(listaPop)
    # Elimina el elemento de la lista en la posición dada
    delLista = ["apple", "banana", "cherry"]
    del delLista[0]
    print(delLista)
    # Imprimir la lista uno a uno
    forLista = ["apple", "banana", "cherry"]
    for io in forLista:
        print(io, end=" ")
    # Imprimir la lista uno a uno utilizando la longitud de la lista
    listaRange = ["apple", "banana", "cherry"]
    for i in range(len(listaRange)):
        print(listaRange[i])
    # Ordena la lista de forma numerica o alfabetica de menor a mayor
    listaSort = ["orange", "mango", "kiwi", "pineapple", "banana"]
    listaSort.sort()
    print(listaSort)
    # Ordena la lista de forma numerica o alfabetica de mayor a menor
    listaSortR = [1, 2, 3, 4, 5, 6, 7]
    listaSortR.sort(reverse=True)
    print(listaSortR)
    # Copiar una lista dentro de otra lista
    listaCopy = ["apple", "banana", "cherry"]
    nListaCopy = listaCopy.copy()
    print(nListaCopy)
    # Une dos o mas listas en una nueva lista
    list1 = ["a", "b", "c"]
    list2 = [1, 2, 3]
    list3 = list1 + list2
    print(list3)
    # Otra forma para unir dos o mas listas
    list1 = ["a", "b", "c"]
    list2 = [1, 2, 3]
    for oio in list2:
        list1.append(oio)
    print(list1)
    # Elimina todos los elementos de la lista
    listaClear = ["apple", "banana", "cherry"]
    listaClear.clear()
    print(listaClear)
    # Añade los elementos de cars al final de la lista fruits
    fruits = ['apple', 'banana', 'cherry']
    cars = ['Ford', 'BMW', 'Volvo']
    fruits.extend(cars)
    print(fruits)
    # Le da la vuelta a la lista
    listaReverse = ["apple", "banana", "cherry"]
    listaReverse.reverse()
    print(listaReverse)
    # Crear una tupla
    tupla = ("apple", "banana", "cherry")
    print(tupla)
    # Cambiar una tupla a una lista (Funciona tambien al reves con .tuple)
    tuplaNormal = ("apple", "banana", "cherry")
    listaNormal = list(tuplaNormal)
    print(listaNormal)
    # Crear otra tupla para añadir un nuevo elemento al final de la otra
    tuplaZ = ("apple", "banana", "cherry")
    Z = ("orange",)
    tuplaZ += Z
    print(tuplaZ)
    # Empacar y desempacar tuplas
    fruits = ("apple", "banana", "cherry")
    (green, yellow, red) = fruits
    print(green, yellow, red)
    # Recorrer una tupla
    tuplaFor = ("apple", "banana", "cherry")
    for ioio in tuplaFor:
        print(ioio, end=" ")
    print()
    # Unir dos o mas tuplas
    tupla1 = ("a", "b", "c")
    tupla2 = (1, 2, 3)
    tupla3 = tupla1 + tupla2
    print(tupla3)
    # Cuenta cuantas veces se encuentra ese valor en la tupla
    tuplaCount = (1, 2, 3, 4, 5, 5, 5, 6, 7, 8)
    print(tuplaCount.count(5))
    # Devuelve la primera posición en la que se encuentra ese valor
    thistuple = (1, 3, 7, 8, 7, 5, 4, 6, 8, 5)
    print(thistuple.index(8))
    # Crear un set
    set = {"apple", "banana", "cherry"}
    # Comprobar si un elemeneto esta en el set
    setIn = {"apple", "banana", "cherry"}
    print("banana" in setIn)
    # Comprobar si un elemeneto no esta en el set
    setNotIn = {"apple", "banana", "cherry"}
    print("banana" not in setNotIn)
    # Añade un elemento al set
    setAdd = {"apple", "banana", "cherry"}
    setAdd.add("orange")
    print(setAdd)
    # Añade los elementos de un set a otro   (Tambien funciona para añadir a un set los elementos de una lista)
    set1 = {"apple", "banana", "cherry"}
    set2 = {"pineapple", "mango", "papaya"}
    set1.update(set2)
    print(set1)
    # Elimina un elemento del set
    setRemove = {"apple", "banana", "cherry"}
    setRemove.remove("banana")
    print(setRemove)
    # Elimina un elemento del set
    setDiscard = {"apple", "banana", "cherry"}
    setDiscard.discard("banana")
    print(setDiscard)
    # Elimina un elemento al azar del set
    setPop = {"apple", "banana", "cherry"}
    x = setPop.pop()
    print(setPop)
    # Recorre el set
    setFor = {"apple", "banana", "cherry"}
    for xio in setFor:
        print(xio, end=" ")
    print()
    # Une dos o mas sets en un set aparte (Excluyendo los duplicados)
    set1 = {"a", "b", "c"}
    set2 = {1, 2, 3}
    set3 = set1.union(set2)
    print(set3)
    # Une dos o mas sets en un set añadiendolos al primer set (Excluyendo los duplicados)
    set11 = {"a", "b", "c"}
    set22 = {1, 2, 3}
    set11.update(set22)
    print(set11)

    # The union() and update() methods joins all items from both sets.
    '''union()  update()'''
    # The intersection() method keeps ONLY the duplicates.
    '''intersection()'''
    # The difference() method keeps the items from the first set that are not in the other set(s).
    '''difference()'''
    # The symmetric_difference() method keeps all items EXCEPT the duplicates.
    '''symmetric_difference()'''
    # Crea un set inmutable (No se puede cambiar ni añadir ni eliminar elementos)
    x = frozenset({"apple", "banana", "cherry"})

    # Eliminar un elemento del set
    setDiscard = {"a", "b", "c"}
    setDiscard.discard("a")
    print(setDiscard)


def Dictionaries():
    # Crear un diccionario
    diccionario = {
        "brand": "Ford",
        "model": "Mustang",
        "year": 1964
    }
    print(diccionario)
    print(diccionario["brand"])
    # Si una clave es duplicada se sobrescribira la anterior
    diccionarioDup = {
        "brand": "Ford",
        "model": "Mustang",
        "year": 1964,
        "year": 2020
    }
    print(diccionarioDup)
    # Devuelve el numero de elementos en el diccionario
    print(len(diccionario))
    # Crear un diccionario con una lista dentro
    diccionarioLista = {
        "brand": "Ford",
        "electric": False,
        "year": 1964,
        "colors": ["red", "white", "blue"]
    }
    print(diccionarioLista)
    # Crear un diccionario con dict()
    diccionarioDict = dict(name="John", age=36, country="Norway")
    print(diccionarioDict)
    # Conseguir un valor de una clave
    diccionarioClave = {
        "brand": "Ford",
        "model": "Mustang",
        "year": 1964
    }
    modelo = diccionarioClave["model"]
    print(modelo)
    # Conseguir un valor de una clave
    modeloGet = diccionarioClave.get("model")
    print(modeloGet)
    # Devuelve las claves de un diccionario
    diccionario = {
        "brand": "Ford",
        "model": "Mustang",
        "year": 1964
    }
    print(diccionario.keys())
    # Añadir un elemento al diccionario
    car = {
        "brand": "Ford",
        "model": "Mustang",
        "year": 1964
    }
    car["color"] = "white"
    print(car.keys())
    # Devuelve los valores de un diccionario
    print(car.values())
    # Cambiar el valor de un elemento dentro del diccionario
    car["color"] = "red"
    print(car)
    # Devuelve true si la clave se encuentra en el diccionario
    if "color" in car:
        print("true")
    else:
        print("false")
    # Actualizar el diccionario con cambios en los valores y si no existe lo añade
    diccionarioUpdate = {
        "brand": "Ford",
        "model": "Mustang",
        "year": 1964
    }
    diccionarioUpdate.update({"year": 2020})
    diccionarioUpdate.update({"color": "red"})
    print(diccionarioUpdate)
    # Eliminar una clave:valor de un diccionario
    diccionarioPop = {
        "brand": "Ford",
        "model": "Mustang",
        "year": 1964
    }
    diccionarioPop.pop("model")
    print(diccionarioPop)
    # Elimina la ultima clave:valor añadida al diccionario
    diccionarioPopitem = {
        "brand": "Ford",
        "model": "Mustang",
        "year": 1964
    }
    diccionarioPopitem.popitem()
    print(diccionarioPopitem)
    #
    diccionarioDel = {
        "brand": "Ford",
        "model": "Mustang",
        "year": 1964
    }
    del diccionarioDel["model"]
    print(diccionarioDel)
    # Eliminar el diccionario
    del diccionarioDel
    # Elimina todos los elementos del diccionario
    diccionarioClear = {
        "brand": "Ford",
        "model": "Mustang",
        "year": 1964
    }
    diccionarioClear.clear()
    print(diccionarioClear)
    # Recorre el diccionario e imprime las claves
    for x in car:
        print(x, end=" ")
    print()
    # Recorre el diccionario e imprime los valores
    for x in car:
        print(car[x], end=" ")
    print()
    # Recorre el diccionario y da valor a x como las claves
    for x in car.keys():
        print(x, end=" ")
    print()
    # Recorre el diccionario y da valor a x como los valores
    for x in car.values():
        print(x, end=" ")
    print()
    ## Recorre el diccionario y da valor a x como los clave:valor
    for x in car.items():
        print(x, end=" ")
    print()
    # Copiar un diccionaro en otro
    diccionarioCopy = {
        "brand": "Ford",
        "model": "Mustang",
        "year": 1964
    }
    diccionarioCopyN = diccionarioCopy.copy()
    print(diccionarioCopyN)
    # Copiar un diccionario en otro con el metodo dict()
    diccionarioCopyN = dict(diccionarioCopy)
    print(diccionarioCopyN)
    # Crear un diccionario dentro de otro
    myfamily = {
        "child1": {
            "name": "Emil",
            "year": 2004
        },
        "child2": {
            "name": "Tobias",
            "year": 2007
        },
        "child3": {
            "name": "Linus",
            "year": 2011
        }
    }
    # Crear varios diccionarios y luego crear otro para contenerlos
    child1 = {
        "name": "Emil",
        "year": 2004
    }
    child2 = {
        "name": "Tobias",
        "year": 2007
    }
    child3 = {
        "name": "Linus",
        "year": 2011
    }
    myfamily = {
        "child1": child1,
        "child2": child2,
        "child3": child3
    }
    # Imprimir el valor de un diccionario dentro de otro diccionario
    print(myfamily["child2"]["name"])
    # Recorrer los diccionarios anidados dentro del otro diccionario
    for x, obj in myfamily.items():
        print(x)
        for y in obj:
            print(y + ':', obj[y])
    # Crear un diccionario y asignarle el valor a todos los elementos
    xi = ('key1', 'key2', 'key3')
    yi = 0
    diccionario = dict.fromkeys(xi, yi)
    print(diccionario)
    # Devuelve el valor de la clave y si no existe devuelve el otro valor dado y lo añade al diccionario
    car = {
        "brand": "Ford",
        "model": "Mustang",
        "year": 1964
    }
    xpo = car.setdefault("model", "Bronco")
    print(xpo)
    # Match
    day = 1
    month = 1
    match day:
        case 1 if month == 1:
            print("Lunes y Enero ")
        case 1:
            print("Monday")
        case 2:
            print("Tuesday")
        case 3:
            print("Wednesday")
        case 4:
            print("Thursday")
        case 5:
            print("Friday")
        case 6 | 7:
            print("Es finde")
        case _:
            print("No ese ninguno de esos casos")
    # Imprime el mensaje si la condición es falsa
    i = 1
    while i < 6:
        print(i)
        i += 1
    else:
        print("i is no longer less than 6")
    # Crea un rango de 5
    print(list(range(5)))
    # Crea un rango del 1 al 6
    print(list(range(1, 6)))
    # Crea un rango del 5 al 20 contando de 5 en 5
    print(list(range(5, 20, 5)))
    # Comprueva si un numero esta en un rango creado
    r = range(0, 10, 2)
    print(6 in r)
    print(7 in r)
    # Recorre una tupla con iteradores
    tupla = ("apple", "banana", "cherry")
    iterador = iter(tupla)
    print(next(iterador), end=" ")
    print(next(iterador), end=" ")
    print(next(iterador))


def Excepciones():
    # Try y Except
    try:
        print(hola)
    except:
        print("An exception occurred")
    # Comprueba si x es una variable
    try:
        print(x)
    except NameError:
        print("Variable x is not defined")
    except:
        print("Something else went wrong")
    # Si no hay excepcion se cumple el else
    try:
        print("Hello")
    except:
        print("Something went wrong")
    else:
        print("Nothing went wrong")
    # El finally se cumple siempre, como si da error como si no lo da
    try:
        print(x)
    except:
        print("Something went wrong")
    finally:
        print("The 'try except' is finished")
    # Comprobar varios try
    try:
        f = open("demofile.txt")
        try:
            f.write("Lorum Ipsum")
        except:
            print("Something went wrong when writing to the file")
        finally:
            f.close()
    except:
        print("Something went wrong when opening the file")
    # Crear una excepcion
    x = -1
    if x < 0:
        raise Exception("Sorry, no numbers below zero")
    # Raise a TypeError if x is not an integer
    x = "hello"
    if not type(x) is int:
        raise TypeError("Only integers are allowed")


def FStrings():
    # Imprimir con formatos
    price = 59
    txt = f"The price is {price} dollars"
    print(txt)
    # Con 2 decimales
    price = 59
    txt = f"The price is {price:.2f} dollars"
    print(txt)
    # Imprimer el valor 95 con 2 decimales
    txt = f"The price is {95:.2f} dollars"
    print(txt)
    # Realiza la operacion matematica
    txt = f"The price is {20 * 59} dollars"
    print(txt)
    # Otra operacion mas
    price = 59
    tax = 0.25
    txt = f"The price is {price + (price * tax)} dollars"
    print(txt)
    # Si se cumple la condicion
    price = 49
    txt = f"It is very {'Expensive' if price > 50 else 'Cheap'}"
    print(txt)
    # Usa una "," como separacion
    price = 59000
    txt = f"The price is {price:,} dollars"
    print(txt)
    '''
    Formatting Types
        :< 	
        Left aligns the result (within the available space)
        :> 	
        Right aligns the result (within the available space)
        :^ 	
        Center aligns the result (within the available space)
        := 	
        Places the sign to the left most position
        :+ 	
        Use a plus sign to indicate if the result is positive or negative
        :- 	
        Use a minus sign for negative values only
        :  	
        Use a space to insert an extra space before positive numbers (and a minus sign before negative numbers)
        :, 	
        Use a comma as a thousand separator
        :_ 	
        Use a underscore as a thousand separator
        :b 	
        Binary format
        :c 		Converts the value into the corresponding Unicode character
        :d 	
        Decimal format
        :e 	
        Scientific format, with a lower case e
        :E 	
        Scientific format, with an upper case E
        :f 	
        Fix point number format
        :F 	
        Fix point number format, in uppercase format (show inf and nan as INF and NAN)
        :g 		General format
        :G 		General format (using a upper case E for scientific notations)
        :o 	
        Octal format
        :x 	
        Hex format, lower case
        :X 	
        Hex format, upper case
        :n 		Number format
        :% 	
        Percentage format
    '''
    # Usando format() para darle valor a un String
    price = 49
    txt = "The price is {} dollars"
    print(txt.format(price))
    # Hace q el precio tengo 2 decimales
    price = 19
    txt = "The price is {:.2f} dollars"
    print(txt.format(price))
    # Usar varios formatos a la vez
    quantity = 3
    itemno = 567
    price = 49
    myorder = "I want {} pieces of item number {} for {:.2f} dollars."
    print(myorder.format(quantity, itemno, price))
    # Darle valor a los formatos en el print
    myorder = "I have a {carname}, it is a {model}."
    print(myorder.format(carname="Ford", model="Mustang"))
    # None es igual a Null
    x = None
    print(x)
    print(type(x))
    # Comprobar si en nulo o none
    result = None
    if result is None:
        print("No result yet")
    else:
        print("Result is ready")


FStrings()