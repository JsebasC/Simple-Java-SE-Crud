package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexion {

		public Connection getConexion() {
			Connection conexion=null;
			String driver="org.postgresql.Driver";
			String url="jdbc:postgresql://localhost:5432/alumnos";
			String user="usuario";
			String password="contraseña";
			try {
				Class.forName(driver);
				conexion=DriverManager.getConnection(url,user,password);
			
			} catch (Exception e) {
				System.out.println("Erroar");
			}
			
			return conexion;
		}
	
}
