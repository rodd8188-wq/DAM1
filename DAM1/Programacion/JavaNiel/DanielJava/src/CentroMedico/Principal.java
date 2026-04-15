package CentroMedico;

public class Principal {

	public static void main(String[] args) {
		CentroMedico cen1 = new CentroMedico("Moratalaz","CM-5634");
		CentroMedico cen2 = new CentroMedico("Vicalvaro","CM-9052");
		Medico med1 = new Medico("Javier","Montoya",cen1,"Ginecologia",345789);
		Medico med2 = new Medico("Raul","Perez",cen2,"Neurologia",765987);
		Medico med3 = new Medico("Francisco","Rivas",cen1,"Urologia",890567);
		Medico med4 = new Medico("Marcos","Ortas",cen2,"Pediatria",456123);
		Paciente pac1 = new Paciente("Paula","Briceño",cen1,"58734061G",657456092);
		Paciente pac2 = new Paciente("Nicolas","Fernandez",cen2,"59678429B",673873421);
		Paciente pac3 = new Paciente("Lucia","Garcia",cen1,"52754645C",686459865);
		Paciente pac4 = new Paciente("Daniel","Rodriguez",cen2,"66456456H",607564534);
		new Consulta(pac1,med1,"10-02-2026","Mal estar general","Tomar más cafeina");
		new Consulta(pac2,med2,"01-010-2026","Migrañas leves","Pensar menos");
		new Consulta(pac1,med2,"12-01-2025","Dolor en el abdomen","Ejercitarse más");
		new Consulta(pac3,med1,"01-14-2026","Infección leve","Un paracetamol al día durante 1 semana");
		new Consulta(pac4,med3,"01-16-2026","Ser demasiado bueno en Java","Relajarse un poco");
		
		//pac1.cambiaCentro(cen2);
		//cen1.listarPacientes();
		//cen2.listarPacientes();
		
		med1.listarConsultas();
		System.out.println();
		pac1.listarConsultas();
		
		
		
		
	}
}
