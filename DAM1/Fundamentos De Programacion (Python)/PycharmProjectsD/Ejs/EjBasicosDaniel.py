class Usuario:
    def __init__(self, nombre, activo=True, multas=0):
        self.nombre = nombre
        self.activo = activo
        self.multas = multas

class Libro:
    def __init__(self, titulo, disponible=True):
        self.titulo = titulo
        self.disponible = disponible

class Biblioteca:
    def realizar_prestamo(self, usuario, libro):

        if not usuario.activo:
            print("Usuario no activo")
            return
        print("")
        print(f"Usuario: {usuario.nombre}")
        print(f"Libro: {libro.titulo}")
        if usuario.multas > 0:
            print(f"Préstamo rechazado. Multa pendiente: ${usuario.multas}")
            return
        if not libro.disponible:
            print("Libro no disponible")
            return
        libro.disponible = False
        print("Préstamo realizado correctamente")



usuario1 = Usuario("Daniel", activo=True, multas=0)
usuario2 = Usuario("Paula", activo=True, multas=50)
libro1 = Libro("Kamasutra", disponible=True)
libro2 = Libro("El Quijote", disponible=False)

biblio = Biblioteca()

biblio.realizar_prestamo(usuario1, libro1)
biblio.realizar_prestamo(usuario2, libro2)