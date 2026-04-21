from operator import truediv

print('Codigoscifradosinador versión 1.0')
w = input('Introducir palabra para el mensaje cifrado: ')
num = int(input('Introducir un clave numérica: '))
w_cif = ''
i = len(w)-1
w_min = w.lower()
while i>=0:
    if w_min[i] == 'a' or w_min[i] == 'e' or w_min[i] == 'i' or w_min[i] == 'o' or w_min[i] == 'u':
        w_cif = w_cif + str(num)
    else:
        w_cif = w_cif + w[i]
    i-=1
print('Primer mensaje cifrado: ', w_cif)

if num%2==0:
    w_cif = w_cif.upper()
else:
    w_cif = w_cif.lower()
w_div1 = w_cif[:num]
w_div2 = w_cif[num:]
print('Segundo mensaje cifrado: ', '\n', w_div1, '\n',w_div2)
input('Presoine ENTER para terminar: ')