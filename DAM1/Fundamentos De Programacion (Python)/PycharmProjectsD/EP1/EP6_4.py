import re
def main():
    print("Fraccionadorinador versión 1.0.2")
    print(fraccion("25/10"))
    print(fraccion("a/10"))
    print(fraccion("25/a"))
    print(fraccion("//10"))
    print(fraccion("10"))
    print("\n\n")
    print(fraccion2("25/10"))
    print(fraccion2("a/10"))
    print(fraccion2("25/a"))
    print(fraccion2("//10"))
    print(fraccion2("10"))


def fraccion(frasion):
    if not frasion.count("/") != 1:
        numerador, denominador = frasion.split("/")
        if not numerador.isnumeric() or not denominador.isnumeric():
            resultado=0
        else:
            resultado=int(numerador)/int(denominador)
    else:
        resultado=0
    return resultado

def fraccion2(frasion):
    resultado=0.0
    try:
        numerador, denominador = frasion.split("/")
        resultado=int(numerador)/int(denominador)
    except ZeroDivisionError:
        print("casomalo")
    finally:
        return resultado


if __name__== "__main__":
    main()
else:
    print("Soy un módulo cargado")
