print('NoterindasSaitamainador versión 1.0')
contador = 0
nota = input('Vamos saitama, dame las notas que tienes el Fundamentos de Programación:\nDame la primera o escribe END: ')
notas = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
list(notas)
total_notas = 0
while nota != 'END':
    notas[contador] = int(nota)
    contador +=1
    nota = input('Dame otra: ')
for i in notas:
    if i< contador:

        total_notas = total_notas + notas[i]
print(notas)
print(total_notas)
