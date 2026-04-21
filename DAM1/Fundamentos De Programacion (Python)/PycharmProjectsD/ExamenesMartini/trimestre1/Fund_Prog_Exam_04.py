import random
print('Pistoladeportalesinador versiòn 1.0')
primo = False
ran = 0
while not primo:
    ran = random.randint(5000000, 20000000)
    if ran%2!=0:
        for i in ran , 3, 2:
            if ran%i==0:
                primo = True
print(ran)
input('Presione ENTER para terminar: ')




