print("═" * 35)
print("Calcular el area de un rectangulo")
print("═" * 35)
valido = False
while valido == False:
    try:
        base = float(input("Base: "))
        altura = float(input("Altura: "))
        area = round(base * altura, 2)
        print("┌" + "─" * len(str(area)) + "─" * 10 + "┐")
        print("│  Area:", area, " │")
        print("└" + "─" * len(str(area)) + "─" * 10 + "┘")
        valido = True
    except ValueError:
        print("Solo se admiten números")