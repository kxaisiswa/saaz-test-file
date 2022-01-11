package database;

import java.sql.*;

public class DatabaseConnection {
	static Connection connect;

//	Localhost Database Connection [ORACLE]
//	private static final String DB_DRIVER = "oracle.jdbc.OracleDriver";
//	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
//	private static final String DB_USER = "saazdbproject";
//	private static final String DB_PASSWORD = "system";

//	Localhost Database Connection [MySQL]
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/saaz";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";

//	Cloud Database Connection [ClearDB/Heroku]
//	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
//	private static final String DB_CONNECTION = "jdbc:mysql://us-cdbr-east-04.cleardb.com:3306/heroku_13ed534030c3d23";
//	private static final String DB_USER = "b1abf5379fdd30";
//	private static final String DB_PASSWORD = "4d65d9bc";
	
	public static Connection getConnection() {
		try {
			Class.forName(DB_DRIVER);
			try {
				connect = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
				System.out.println("Connection Success");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connect;
	}
}