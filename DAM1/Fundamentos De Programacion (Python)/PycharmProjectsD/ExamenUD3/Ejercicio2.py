palabra = str(input("Escribe la palabra: "))
clave = int(input("Escribe la clave numérica: "))
reves = ""
for i in range(0, len(palabra)):
    print(i-len(palabra)+1)
    posicion = i-len(palabra)+1


if clave%2==0:
    reves = palabra.upper()
else:
    reves = palabra.lower()
