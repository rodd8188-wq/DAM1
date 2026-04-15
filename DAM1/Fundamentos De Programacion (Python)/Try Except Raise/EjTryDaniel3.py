colores = ["rojo", "verde", "azul", "amarillo", "rosa"]

try:
    cosa = int(input("Ingresa un índice del 0 al 4: "))
    print("Color:", colores[cosa])
except IndexError:
    print("Fuera de rango.")
except ValueError:
    print("Número inválido.")