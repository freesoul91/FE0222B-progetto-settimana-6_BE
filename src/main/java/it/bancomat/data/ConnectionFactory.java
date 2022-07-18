package it.bancomat.data;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.SQLException;

public class ConnectionFactory {
	public static final String URL = "jdbc:postgresql://localhost:5432/bancomat";
	public static final String USER = "postgres";
	public static final String PASS = "danielec13";
	
	public static Connection getConnection() {
		Connection conn = null;
		
		//try {
			
		//} catch (ClassNotFoundException e) {			
		//	e.printStackTrace();
		//}
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Connessione riuscita");
		} catch(Exception ex) {
			System.out.println("Connessione non riuscita");
			ex.printStackTrace();
		}
		return conn;
	}

}
