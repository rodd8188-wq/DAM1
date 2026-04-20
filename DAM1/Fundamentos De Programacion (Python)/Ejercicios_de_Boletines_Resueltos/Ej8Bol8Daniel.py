
def descifrar_mensaje(mensj, dicc):
    texto = ""
    for i in range(0, len(mensj) - 2, 3):
        bloque = mensj[i:i + 3]
        if bloque in dicc:
            texto += dicc[bloque]
    return texto

dicc = {"112": "D", "133": "A", "478": "N", "522": "I"}
mensj = "112133478522"
resultado = descifrar_mensaje(mensj, dicc)
print(resultado)