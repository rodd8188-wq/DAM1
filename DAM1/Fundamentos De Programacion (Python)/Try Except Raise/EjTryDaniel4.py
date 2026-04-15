saldo = 1000

try:
    cuantia = float(input("¿Cuánto deseas retirar?: "))
    if cuantia > saldo:
        raise Exception("Fondos insuficientes.")
    saldo -= cuantia
except ValueError:
    print("Debes ingresar un número válido.")
except Exception as e:
    print(e)
else:
    print("Nuevo saldo:", saldo)
finally:
    print("Retiro exitoso.")