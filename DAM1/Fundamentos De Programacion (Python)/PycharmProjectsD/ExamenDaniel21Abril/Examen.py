# Ejercicio 1
class Tripulante:
    def __init__(self, nombre, rango, planeta_origen, anyos_experiencia):
        self.nombre = nombre
        self.rango = rango
        self.planeta_origen = planeta_origen
        self.anyos_experiencia = anyos_experiencia

    def __str__(self):
        return f"Nombre: {self.nombre} | Rnago: {self.rango} | Planeta origen: {self.planeta_origen} | Años de experiencia: {self.anyos_experiencia}"

def csv_A_tripulante(row):
    datos = row.strip().split(",")
    # print(datos[0])
    if len(datos) == 4:
        return Tripulante(datos[0], datos[1], datos[2], datos[3])
    return None

def cargar_tripulacion(ruta):
    arrayTripulantes = []
    try:
        with open(ruta, "r", encoding="utf-8") as tripulantes:
            for row in tripulantes:
                if not row.startswith("Nombre"):
                    arrayTripulantes.append(csv_A_tripulante(row))
        return arrayTripulantes
    except FileNotFoundError:
        arrayTripulantes = []
        return arrayTripulantes

def tripulante_A_csv(tripulante):
    return f"{tripulante.nombre},{tripulante.rango},{tripulante.planeta_origen},{tripulante.anyos_experiencia}\n"

def agregar_tripulante(ruta, tripulante):
    try:
        with open(ruta, "a", encoding="utf-8") as tripulantes:
            tripulantes.write(tripulante_A_csv(tripulante))
    except FileNotFoundError:
        print("No se pudo agregar el tripulante")

# Ejercicio 2
for t in cargar_tripulacion("tripulacion.csv"):
    print(t)

#Ejercicio 3
agregar_tripulante("tripulacion.csv",Tripulante("Daniel", "Estudiante", "Tierra", 1))
