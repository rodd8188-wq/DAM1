import re
def main():
    print("comparadordechasinador versión 1.0")
    print(validadorFechas("01/01/20"))
    print(validadorFechas("10/12/2020"))
    print(validadorFechas("s0-12-2020"))
    print(validadorFechas("-12-2020"))
    print(validadorFechas("ayuda"))

def validadorFechas(fecha):
    si= False
    patron="[0-3]{1}[0-9]{1}/[0-1]{1}[0-9]/[0-9]*"
    if re.match(patron, fecha):
        dia, mes, anyo = fecha.split("/")
        dia = int(dia)
        mes = int(mes)
        anyo = int(anyo)
    else:
        return si


if __name__== "__main__":
    main()
else:
    print("Soy un módulo cargado")