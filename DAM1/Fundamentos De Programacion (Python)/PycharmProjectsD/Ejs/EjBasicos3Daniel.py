from enum import Enum
import random

class Prioridad(Enum):
    P1 = 1
    P2 = 2
    P3 = 3
    P4 = 4
    P5 = 5

class Paciente:
    def __init__(self, nombre, tieneHistorial):
        self.nombre = nombre
        self.tieneHistorial = tieneHistorial
        self.prioridad = None
        self.diagnostico = None

class Administrativo:
    def registrarPaciente(self, nombre):
        tiene_historial = random.choice([True, False])
        paciente = Paciente(nombre, tiene_historial)
        print(f"Administrativo: Paciente {nombre} registrado.")
        return paciente


class SistemaHospital:
    def recuperarHistorial(self, paciente):
        if paciente.tiene_historial:
            print("Historial recuperado automáticamente.")
        else:
            print("Paciente sin historial previo.")

    def alertarEquipoMedico(self):
        print("Emergencia vital. Equipo médico notificado.")


class EnfermeroTriage:
    def evaluar(self, paciente):
        prioridad_valor = random.randint(1, 5)
        paciente.prioridad = Prioridad(prioridad_valor)
        print(f"Enfermero: Prioridad = {paciente.prioridad.name}")
        return paciente.prioridad


class Medico:
    def __init__(self, disponible=True):
        self.disponible = disponible

    def atender(self, paciente):
        if not self.disponible:
            print("No hay médicos disponibles.")
            return False

        paciente.diagnostico = "Diagnóstico general"
        print(f"Médico: Paciente atendido. {paciente.diagnostico}")
        print("Médico: Se ordenan exámenes o se da el alta.")
        return True

def flujo_urgencias(nombrePaciente):

    administrativo = Administrativo()
    sistema = SistemaHospital()
    enfermero = EnfermeroTriage()
    medico = Medico(disponible=random.choice([True, False]))

    paciente = administrativo.registrarPaciente(nombrePaciente)

    sistema.recuperarHistorial(paciente)

    prioridad = enfermero.evaluar(paciente)

    if prioridad == Prioridad.P1:
        sistema.alertarEquipoMedico()

    atendido = medico.atender(paciente)

    if not atendido:
        print("Regla de negocio: paciente queda en espera prioritaria o se deriva.")




if __name__ == "__main__":
    flujo_urgencias("Juan Pérez")