package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {


	static String URI = "jdbc:postgresql://localhost:5432/poo2";
	static String usuario = "euripedes";
	static String senha = "euripedes07";
	static Connection conexao = null;

	
	public static Connection retornaConexecao() throws SQLException {
		
		if(conexao == null)
			return DriverManager.getConnection(URI, usuario,senha);
		else
			return conexao;
	}
}
