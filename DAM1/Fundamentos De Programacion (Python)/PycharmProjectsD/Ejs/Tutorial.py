class Cliente:
    def __init__(self, nombre, apellido, edad, telefono):
        self.nombre = nombre
        self.apellido = apellido
        self.edad = edad
        self.telefono = telefono
        self.animales_adoptados = []

    def puede_adoptar(self, animal):
        # Regla 1: No más de 4 animales en total
        if len(self.animales_adoptados) >= 4:
            return False, "Cupo total de 4 animales alcanzado."

        # Contadores por tipo
        perros = sum(1 for a in self.animales_adoptados if isinstance(a, Perro))
        gatos = sum(1 for a in self.animales_adoptados if isinstance(a, Gato))
        tortugas = sum(1 for a in self.animales_adoptados if isinstance(a, Tortuga))

        # Regla 2: Límites específicos
        if isinstance(animal, Perro) and perros >= 2:
            return False, "Ya tiene 2 perros."
        if isinstance(animal, Gato) and gatos >= 3:
            return False, "Ya tiene 3 gatos."
        if isinstance(animal, Tortuga) and tortugas >= 1:
            return False, "Ya tiene 1 tortuga."

        return True, "OK"

    def adoptar(self, animal):
        permitido, mensaje = self.puede_adoptar(animal)
        if permitido:
            animal.adoptado = True
            self.animales_adoptados.append(animal)
            print(f"¡Éxito! {self.nombre} ha adoptado a un {type(animal).__name__}.")
        else:
            print(f"Adopción denegada: {mensaje}")

class Animal:
    def __init__(self, anio_nacimiento, nombre=None):
        self.anio_nacimiento = anio_nacimiento
        self.nombre = nombre if nombre else "Sin nombre"
        self.adoptado = False

class MascotaVacunable(Animal):
    def __init__(self, anio_nacimiento, vacunado, nombre=None):
        super().__init__(anio_nacimiento, nombre)
        self.vacunado = vacunado

class Perro(MascotaVacunable):
    pass

class Gato(MascotaVacunable):
    pass

class Tortuga(Animal):
    pass