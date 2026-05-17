from enum import Enum
from datetime import date

class TipoUsuario(Enum):
    INVITADO = "Invitado"
    ESTUDIANTE = "Estudiante"
    INSTRUCTOR = "Instructor"

class EstadoSuscripcion(Enum):
    ACTIVA = "Activa"
    EXPIRADA = "Expirada"
    NINGUNA = "Ninguna"

class Usuario:
    def __init__(self, nombre, tipo, edad, estadoSuscripcion=EstadoSuscripcion.NINGUNA):
        self.nombre = nombre
        self.tipo = tipo
        self.edad = edad
        self.estadoSuscripcion = estadoSuscripcion

    def suscripcionActiva(self):
        return self.estadoSuscripcion == EstadoSuscripcion.ACTIVA

class Contenido:
    def __init__(self, titulo, edad_minima=0):
        self.titulo = titulo
        self.edad_minima = edad_minima

class Reproducir:
    def ejecutar(usuario: Usuario, contenido: Contenido):
        print("")
        print(f"Usuario: {usuario.nombre}")
        print(f"Intentando reproducir: {contenido.titulo}")

        if usuario.tipo == TipoUsuario.INVITADO:
            return "Los invitados solo pueden ver catálogo y tráilers."

        if usuario.tipo == TipoUsuario.ESTUDIANTE:
            if not usuario.suscripcionActiva():
                return "Suscripción expirada"

        if usuario.edad < contenido.edad_minima:
            return "Contenido restringido por edad."

        return f"Reproduciendo '{contenido.titulo}'..."

if __name__ == "__main__":

    contenido = Contenido("Curso de Python", edad_minima=18)

    invitado = Usuario("Daniel", TipoUsuario.INVITADO, edad=19)
    estudiante_activo = Usuario("Paula", TipoUsuario.ESTUDIANTE, edad=19)
    estudiante_expirado = Usuario("Nick", TipoUsuario.ESTUDIANTE, edad=44)
    menor_edad = Usuario("Constante", TipoUsuario.ESTUDIANTE, edad=33)

    print(Reproducir.ejecutar(invitado, contenido))
    print(Reproducir.ejecutar(estudiante_activo, contenido))
    print(Reproducir.ejecutar(estudiante_expirado, contenido))
    print(Reproducir.ejecutar(menor_edad, contenido))