def fraccion(str1):
    parte = str1.split("/")
    if len(parte) != 2:
        return 0
    try:
        numerador = int(parte[0])
        denominador = int(parte[1])
        if denominador == 0:
            return 0
        return numerador / denominador
    except ValueError:
        return 0

print(fraccion("25/10"))
print(fraccion("a/10"))
print(fraccion("//10"))
print(fraccion("10"))