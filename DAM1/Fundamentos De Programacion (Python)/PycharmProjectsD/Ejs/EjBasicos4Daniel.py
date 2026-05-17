from datetime import datetime, timedelta

class PasarelaPagoExterna:
    def procesarPago(self, monto, fondosDisponibles):
        if fondosDisponibles < monto:
            return False, "Fondos insuficientes"
        return True, "Pago aprobado"

class SistemaAerolinea:
    def __init__(self):
        self.asientosDisponibles = 5
        self.reservas = []

    def verificar(self):
        return self.asientosDisponibles > 0

    def reservar(self, pasajero, monto, fondos,
                       seguro=False, vip=False):

        print("Verificando")
        if not self.verificar():
            return "No hay asientos disponibles"
        pago = PasarelaPagoExterna()
        aprobado, mensaje = pago.procesarPago(monto, fondos)
        if not aprobado:
            return f"Pago rechazado: {mensaje}"
        # Opcionales (<<extend>>)
        extras = []
        if seguro:
            extras.append("Seguro de viaje")
        if vip:
            extras.append("Asiento VIP")
        self.asientosDisponibles -= 1
        reserva = {
            "pasajero": pasajero,
            "extras": extras,
            "fecha_vuelo": datetime.now() + timedelta(days=2),
            "club_vip": pasajero.club_vip
        }
        self.reservas.append(reserva)
        return f"Reserva para {pasajero.nombre}. Extras: {extras}"

    def checkin(self, reserva):
        horasRestantes = reserva["fecha_vuelo"] - datetime.now()
        if horasRestantes > timedelta(hours=24):
            return "El check-in aún no está disponible"
        mensaje = "Check-in realizado"
        if reserva["club_vip"]:
            mensaje += " + Upgrade automático por Club VIP (si hay espacio)"
        return mensaje

class Pasajero:
    def __init__(self, nombre, club_vip=False):
        self.nombre = nombre
        self.club_vip = club_vip

sistema = SistemaAerolinea()
pasajero1 = Pasajero("Paula", club_vip=True)

resultado = sistema.reservar(
    pasajero=pasajero1,
    monto=300,
    fondos=500,
    seguro=True,
    vip=True
)

print(resultado)
if sistema.reservas:
    reserva = sistema.reservas[0]
    reserva["fecha_vuelo"] = datetime.now() + timedelta(hours=10)
    print(sistema.checkin(reserva))