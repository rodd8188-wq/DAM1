package mmm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FechasyHoras {

	public static void main(String[] args) {
		
       LocalDate hoy = LocalDate.now(); //Fecha de hoy
       System.out.println(hoy);
       
       LocalTime ahora = LocalTime.now(); //Hora al momento, con segundos precisos
       System.out.println(ahora);
       
       LocalDate cumple = LocalDate.of(1968, 10, 8); //Guardar una fecha
       System.out.println(cumple);
       
       LocalTime citaMedica = LocalTime.of(10, 15); //Guardar una hora, necesario poner hora y minuto(los segundos es opcional)
       System.out.println(citaMedica);
       
       LocalTime dentrodeUnaHora = ahora.plusHours(1); //se crea un objeto nuevo, pero el ahora no se toca, sigue siendo el mismo
       System.out.println("Dentro de una hora; " + dentrodeUnaHora);
       System.out.println(ahora);
       
       ahora.plusMinutes(15);
       ahora.plusSeconds(45);
       ahora.minusHours(3);
       ahora.minusMinutes(35);
       ahora.minusSeconds(55);
       
       LocalDate dentrodeUnAnyo = hoy.plusYears(1); //con los plus sumamos y con los minus restamos
       System.out.println(dentrodeUnAnyo);
       
       LocalDateTime fechayhora = LocalDateTime.now(); //este es un objeto que fusiona las 2 anteriores
       System.out.println(fechayhora);
       
       DateTimeFormatter formato1 = DateTimeFormatter.ofPattern("EEEE, dd-MMMM-yyyy"); //el Mes en mayusculas(porque 2 mm son los minutos), 2 MM para fecha con numeros y 4 MMMM para fecha con letras
       //con la E se pone el dia de la semana, y con la EEEE lo pone completo
       //Otro formato a probar: "HH,mm"
       String fechaConFormato = fechayhora.format(formato1);
       System.out.println(fechaConFormato);
       
       //ESTO ES LO MISMO PERO SE CAMBIA fechayhora POR hoy:
       DateTimeFormatter formato2 = DateTimeFormatter.ofPattern("EEEE, dd-MMMM-yyyy");
       String fechaConFormato2 = hoy.format(formato2);
       System.out.println(fechaConFormato2);
       
       formato1 = DateTimeFormatter.ofPattern("HH:mm");
       System.out.println(ahora.format(formato1));
       
       //Asi puedo guardar una fecha a partir de un String
       String fechaTxt = "08/10/1968"; 
       DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       LocalDate cumple2 = LocalDate.parse(fechaTxt, formato);
       System.out.println(cumple2);
       
       //Para comparar dos fechas:
       if(cumple2.isAfter(hoy))
    	   System.out.println(cumple2 + " es posterior a " + hoy);
       else
    	   System.out.println(cumple2 + " no es posterior a " + hoy);
       
       if(cumple2.isBefore(hoy))
    	   System.out.println(cumple2 + " es anterior a " + hoy);
       else
    	   System.out.println(cumple2 + " no es anterior a " + hoy);
       
       //Para comparar dos fechas iguales:
       LocalDate hoy2 = LocalDate.now();
       
       System.out.println(hoy.isEqual(hoy2));
	}

}
