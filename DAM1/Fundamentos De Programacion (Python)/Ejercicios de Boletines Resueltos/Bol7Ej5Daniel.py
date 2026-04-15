def compararFechas(fecha1, fecha2):
    def sirve(fecha):
        try:
            parte = fecha.split("/")
            if len(partes) != 3:
                return None
            dia, mes, anio = int(parte[0]), int(parte[1]), int(parte[2])
            if mes < 1 or mes > 12 or anio < 1:
                return None
            diasEnMeses = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
            if (anio % 4 == 0 and anio % 100 != 0) or (anio % 400 == 0):
                diasEnMeses[1] = 29
            if dia < 1 or dia > diasEnMeses[mes - 1]:
                return None
            return (anio, mes, dia)
        except ValueError:
            return None
    fecha1 = sirve(fecha1)
    fecha2 = sirve(fecha2)
    if fecha1 is None or fecha2 is None:
        return "Las fechas estan mal."
    if fecha2 < fecha1:
        return f"{fecha2} es mayor que {fecha1}."
    elif fecha1 < fecha2:
        return f"{fecha1} es mayor que {fecha2}."

    else:
        return "Las dos fechas son la misma."
