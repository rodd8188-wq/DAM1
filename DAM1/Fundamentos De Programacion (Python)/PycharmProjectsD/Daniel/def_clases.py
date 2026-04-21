import csv          # muy importante

numero = 10.0

print(type(numero))

class Libro:
    def __init__(self, titulo):
        self.titulo = titulo
    @staticmethod
    def descripcion():
        return "descripcion"
    #Equivalente al toString
    def __str__(self):
        return "{titulo: " + self.titulo + ";}"

print(Libro.descripcion())
print(Libro)
#instanciación de un objeto de la clase libro.
libro = Libro("Al faro")
print(libro.descripcion())
print(libro)

class Punto:
    def __init__(self, codigo:str, x:float, y:float, z:float):
        self._codigo = codigo
        self.x = float(x)
        self.y = float(y)
        self.z = float(z)
    @property
    def codigo(self):
        return self._codigo
    @codigo.setter
    def codigo(self, codigo):
        print("No se puede modificar.")
    @property
    def posicion(self):
        return (self.x, self.y, self.z)
    def __str__(self):
        return f"codigo: {self._codigo}\n\tx: {self.x},y: {self.y},z: {self.z}"

puntoA = Punto("A",1.0,3.5,-7)
print("-"*5)
print(puntoA.x)
puntoA.x = 10.0
print(puntoA.x)
print(puntoA.codigo)
puntoA.codigo = "B"
print(puntoA.codigo)
print(puntoA.posicion)

nuevo_punto = Punto("patata",5.0,5,3.0)
print(nuevo_punto)