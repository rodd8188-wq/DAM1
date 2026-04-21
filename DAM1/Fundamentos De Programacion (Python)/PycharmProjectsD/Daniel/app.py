import csv

class Contacto:
    def __init__(self, nombre, telefono, email):
        self.nombre = nombre
        self.telefono = telefono
        self.email = email

    # El __str__ es para que TÚ lo leas bien en consola
    def __str__(self):
        return f"Nombre: {self.nombre} | Tel: {self.telefono} | Email: {self.email}"

# Esta función es la que genera la línea para el archivo
def ContactoTOcsv(contacto):
    return f"{contacto.nombre};{contacto.telefono};{contacto.email}\n"

def CsvTOcontacto(lineaCSV):
    # Corregido: usamos 'lineaCSV' que es el parámetro de la función
    datos = lineaCSV.strip().split(";")
    if len(datos) == 3:
        return Contacto(datos[0], datos[1], datos[2])
    return None

def estaVacio(ruta):
    with open(ruta, "r", encoding="utf-8") as f:
        lineas = f.readlines()
        if len(lineas) <= 0:
            return True
        return False

def guardar(nuevoContacto):
    with open("contactos.csv", "a", encoding="utf-8") as contactos:
        if estaVacio("contactos.csv"):
            contactos.write(ContactoTOcsv(Contacto("Nombre", "Telefono", "Email")))
        contactos.write(ContactoTOcsv(nuevoContacto))

# --- PRUEBA ---
# nuevoContacto = Contacto("Nuevo", "86553656", "Prueba@gmail.com")
# guardar(nuevoContacto)

# Lectura
#with open("contactos.csv", "r", encoding="utf-8") as contactos:
 #   for linea in contactos:
        # Usamos la función que creamos para convertir la línea en objeto
  #      contactoLeido = CsvTOcontacto(linea)
   #     if contactoLeido:
    #        print(contactoLeido) # Ahora el print llamará al __str__ bonito

def obtenerTodo():
    listaContactos = []
    with open("contactos.csv", "r", encoding="utf-8") as contactos:
        for linea in contactos:
            if not linea.startswith("Nombre"):
                listaContactos.append(CsvTOcontacto(linea))
    return listaContactos

nuevoContacto = Contacto("Nuevo", "687956231", "prueba@prueba.tk")
guardar(nuevoContacto)

for contacto in obtenerTodo():
    print(contacto)