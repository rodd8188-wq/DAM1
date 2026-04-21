from random import randint

primitiva= []
for i in range(6):
    random = randint(1,49)
    '''if random not in primitiva:'''
    '''if primitiva.count(random) == 0:
        primitiva.append(random)'''

    while primitiva.count(random)!=0:
        random = randint(1,49)
    primitiva.append(random)

primitiva.sort()
print("Primitiva ", primitiva)
print("Complementario: ", randint(0,9))
