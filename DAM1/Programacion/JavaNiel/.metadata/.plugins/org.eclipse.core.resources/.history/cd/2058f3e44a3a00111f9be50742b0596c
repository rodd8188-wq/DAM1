package EjercicioLogin;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.Scanner;

public class Registro {
	
	static String
	usuario = "admin",
	password = "1234",
	server = "jdbc:mysql://localhost:3306/agenda";
	
	public static void registro() {
		try {
			boolean usuarioYaExistente = false;
			do {
				Scanner tcl = new Scanner(System.in);
				System.out.print("Usuario: ");
				String user = tcl.nextLine();
				//Comprobar si ya existe el usuario
				if(comprobarSiElUsuarioExiste(user) == true) {
					System.out.println("Ese nombre de usuario ya existe");
					usuarioYaExistente = true;
				} else {
					boolean passwordsWrong = false;
					do {
						System.out.print("Contraseña: ");
						String password = tcl.nextLine();
						System.out.print("Repite la contraseña: ");
						String passWordX2 = tcl.nextLine();
						//Comprobar contraseñas iguales
						if(password.equals(passWordX2) == false) {
							System.out.println("Las contraseñas no coinciden");
							passwordsWrong = true;
						} else {
							boolean emailGood = true;
							String email = "";
							do {
								//Comprobar correo
								System.out.print("Correo: ");
								email = tcl.nextLine();
								if(email.length()>50) {
									System.out.println("El correo debe ser más corto");
									emailGood = false;
								}
							} while(emailGood == false);
							String salt = generarSalt();
							String hash = generarHash(salt, password);
							grabarRegistro(salt, user, hash, email);
						}
					}while(passwordsWrong == true);
				}
			}while(usuarioYaExistente == true);
			
			
		} catch (Exception e) {
			
		}
		
	}
	
	public static boolean comprobarSiElUsuarioExiste(String nombre) {
		boolean existe = false;
		try(Connection conexion = DriverManager.getConnection(Registro.server, Registro.usuario, Registro.password)) {
			Statement query = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String consulta = "SELECT * FROM usuarios";
			ResultSet resultado = query.executeQuery(consulta);
			//Recorrer la base
			resultado.beforeFirst();
			while(resultado.next() || existe == true) {
				if(nombre.equalsIgnoreCase(resultado.getString("nombre")))
					existe = true;
			}
		} catch (SQLException e) {
			
		}
		return existe;
	}
	
	public static String generarSalt() {
		SecureRandom azar = new SecureRandom();
		byte[] salt = new byte[16];
		azar.nextBytes(salt);
		String cadenaSalt = Base64.getEncoder().encodeToString(salt);
		return cadenaSalt;
	}
	
	public static String generarHash(String salt, String pass) {
		String txt = salt + pass;
		String hashTxt = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			byte[] hash = digest.digest(txt.getBytes(StandardCharsets.UTF_8));
			hashTxt = Base64.getEncoder().encodeToString(hash);
		} catch (Exception e) {
			System.out.println("El algoritmo SHA-512 no está disponible");
		}
		return hashTxt;
	}
	
	public static void grabarRegistro(String salt, String user, String hash, String email) {
		try(Connection conexion = DriverManager.getConnection(Registro.server, Registro.usuario, Registro.password)) {
			PreparedStatement query = conexion.prepareStatement("INSERT INTO usuarios VALUES(?, ?, ?, ?, ?)", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			//Asignar valores
			query.setString(1, salt);
			query.setString(2, user);
			query.setString(3, hash);
			query.setString(4, email);
			//Asignar por defecto el privilegio 1
			query.setInt(5, 1);
			//Ejecutarlo
			query.executeUpdate();
			System.out.println("Usuario creado correctamente");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
