print('CalculadordezonaSeguraParaelForniteinador versión 1.0')
pi = 3.14159265
rad = float(input('Por favor, introduce el radio de la zona segura: '))
print('Calculando...')
area = pi * (rad**2)
radFinal =  rad * 0.4
areaFinal = pi * (radFinal**2)
print('El área de la zona segura es de:', round(area, 2), '\n\nCalculando ahora el área de la zona final...', '\n\nEl área de la zona final(que se reduce en un 60%) es de:', areaFinal)
input('Presione ENTER para finalizar: ')