try:
    a = float(input("Primer número: "))
    b = float(input("Segundo número: "))

    resultado = a / b
    print("Resultado:", resultado)

except ZeroDivisionError:
    print("No se puede dividir por cero.")
except ValueError:
    print("Debes ingresar números válidos.")