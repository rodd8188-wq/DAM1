package EjercicioLogin;

import java.security.SecureRandom;
import java.util.Base64;

public class Login {

	public static void main(String[] arGs) {
		
		String user = "Daniel";
		String password = "abc123";
		
		// Generar SALT
		
		SecureRandom azar = new SecureRandom();
		byte[] salt = new byte[16];
		azar.nextBytes(salt);
		
		String cadenaSalt = Base64.getEncoder().encodeToString(salt);
		System.out.println(cadenaSalt);
		
		String passwordConSalt = cadenaSalt + password;
		System.out.println(passwordConSalt);
	}

}
