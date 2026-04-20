import random

num = random.randint(int(5000000), int(20000000))
primo = True
    while primo:
        num = random.randint(int(5000000), int(20000000))
        for i in range(2,int(num/2)):
            if num%i!=0:
                primo = False

