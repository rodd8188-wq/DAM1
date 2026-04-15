package OtroCentroMedico;

import java.util.ArrayList;

public class OtroPool {

	public static void main(String[] args) {
		
		Especialidad esp1 = new Especialidad("cardiología");
		Especialidad esp2 = new Especialidad("ginecología");
		Especialidad esp3 = new Especialidad("urología");
		
		Medico m1 = new Medico("Javier Garcia",esp1);
		Medico m2 = new Medico("Paula Briceño",esp2);
		
		Paciente p1 = new Paciente("Daniel Rodriguez");
		Paciente p2 = new Paciente("Laura Moreno");
		
		p1.pedirCita(esp1);
		
		
		
		
		}
}
