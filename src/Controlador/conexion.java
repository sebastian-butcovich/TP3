package Controlador;
import	java.sql.*;
public class conexion {
	private static Connection conexion;
	private conexion()
	{
	}
	public  static Connection GetConexion(String nombreBase,String userName,String password)
	{
		String url = "jdbc:mysql://localhost:3306/"+nombreBase;
		if (conexion != null)
		{
			return conexion;
		}
		else {
			try {
			conexion = DriverManager.getConnection(url,userName,password);
			}catch(Exception e)
			{
				e.getStackTrace();
			}
		}		
		return conexion;
	}
	
	public static Connection getConexion()
	{
		return conexion;
	}
	public static boolean cerrarConexion()
	{
		try {
			conexion.close();
			return true;
		}catch(SQLException e)
		{
			e.getStackTrace();
		}
		return false;
	}
	
}
