package Boletin27Daniel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		
		recuperarPokemons();
		
	}
	
	public static void recuperarPokemons() {
		//Datos de la Base de Datos	
		String usuarioDB = "admin";
		String passwordDB = "1234";
		String serverDB = "jdbc:mysql://localhost:3306/pokemondb";
		
		try(Connection conexion = DriverManager.getConnection(serverDB, usuarioDB, passwordDB)) {
			System.out.println("Conexión realizada con éxito");
			
			Statement query = conexion.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			String consulta = "SELECT * FROM pokemon";
			ResultSet resultado = query.executeQuery(consulta);
			//Recorrer la consulta
			resultado.beforeFirst();
			while(resultado.next()) {
				new Pokemon(resultado.getInt("numero_pokedex"),
						resultado.getString("nombre"),
						resultado.getDouble("peso"),
						resultado.getDouble("altura"),
						"");
			}
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
