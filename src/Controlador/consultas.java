package Controlador;
import	java.sql.*;
import	Modelo.*;
import	Controlador.conexion;
public class consultas {
	
	
	public static boolean establecerConexion(String url,String userName,String password)
	{
		System.out.println("Se esta estableciendo la conexion... ");
		 conexion.GetConexion(url,userName, password);
		if(conexion.getConexion()!=null)
		{
			System.out.println("Conexion exitosa");
			return true;
		}else
		{
			System.out.println("Fallo de conexion");
			return false;
		}
	}
	public static ResultSet obtenerTablaFutbolista()
	{
		Statement ps=null;
		ResultSet rs=null;
		String sentencia = "select * from futbolista";
		try {
			ps = conexion.getConexion().createStatement();
			rs=ps.executeQuery(sentencia);
			System.out.printf("%10s","idfutbolista\t");
			System.out.printf("%10s","nombre\t");
			System.out.printf("%10s","apellido\t");
			System.out.printf("%10s","docIdentidad\t");
			System.out.printf("%10s","telefono\t");
			System.out.printf("%10s","idPais\t");
			System.out.printf("%10s","email\t");
			System.out.println();
			while(rs.next())
			{
				System.out.printf("%10d\t", rs.getInt("idfutbolista"));
				System.out.printf("%10s\t",rs.getString("nombre"));
				System.out.printf("%10s\t",rs.getString("apellido"));		
				System.out.printf("%10s\t",rs.getString("docIdentidad"));
				System.out.printf("%10s\t",rs.getString("telefono"));
				System.out.printf("%10d\t",rs.getInt("idPais"));
				System.out.printf("%20s\t",rs.getString("email"));
				System.out.println();
			}
			return rs;
		}catch(SQLException e)
		{
			e.getStackTrace();
		}
		return rs;
	}
	public static boolean ingresarFutbolista(modelo.Futbolista f) {
		PreparedStatement ps =null;
		try {
			ps = conexion.getConexion().prepareStatement("insert into futbolista(nombre,apellido,docIdentidad,telefono,email,idpais) values(?,?,?,?,?,?)");
			ps.setString(1, f.getNombre());
			ps.setString(2, f.getApellido());
			ps.setString(3, f.getDocIdentidad());
			ps.setString(4, f.getTelefono());
			ps.setString(5, f.getEmail());
			ps.setInt(6,f.getPais());
			ps.execute();
			return true;
			
		}catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		return false;
	}
	public static boolean modificarFutbolista(int id, modelo.Futbolista f)
	{
		PreparedStatement ps = null;
		try{
			System.out.println(id);
			ps = conexion.getConexion().prepareStatement("update futbolista set nombre = ?, apellido = ?, docIdentidad = ?, telefono = ?, email = ?, idpais = ? where idfutbolista = ? ");
			ps.setString(1,f.getNombre());
			ps.setString(2,f.getApellido());
			ps.setString(3,f.getDocIdentidad());
			ps.setString(4,f.getTelefono());
			ps.setString(5,f.getEmail());
			ps.setInt(6,f.getPais());
			ps.setInt(7,id);
			ps.execute();
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public static boolean ingresarSede(Sede s )
	{
		PreparedStatement ps =null;
		try {
			ps = conexion.getConexion().prepareStatement("insert into sede(nombre,capacidad,idpais) values (?,?,?)");
			ps.setString(1, s.getNombre());
			ps.setInt(2, s.getCapacidad());
			ps.setInt(3, s.getPais());
			ps.execute();
			return true;
		}catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public static boolean ingresarPais(Pais p)
	{
		PreparedStatement ps = null;
		try {
			ps = conexion.getConexion().prepareStatement("insert into pais(nombre,idioma) values (?,?)");
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getIdioma());
			ps.execute();
			return true;
		}catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public static boolean modificarPais(int id,Pais p)
	{
		PreparedStatement ps =null;
		String sql = "update pais set nombre = ?, idioma = ? where idpais = ?";
		try{
			ps = conexion.getConexion().prepareStatement(sql);
			ps.setString(1,p.getNombre());
			ps.setString(2,p.getIdioma());
			ps.setInt(3,id);
			ps.execute();
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public static boolean  eliminarFutbolista(int f)
	{
		PreparedStatement st =null;
		try {
			st =conexion.getConexion().prepareStatement("delete from futbolista where idfutbolista =?");
			st.setInt(1, f);
			st.execute();
			return true;
		}catch(SQLException s)
		{
			s.printStackTrace();
			return false;
		}
	}
	public static boolean eliminarSede(int s)
	{
		PreparedStatement st = null;
		try {
			st = conexion.getConexion().prepareStatement("delete from sede where (idsede = ?)");
			st.setInt(1,s);
			st.execute();
			return true;
		}catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public static boolean actualizarSede(String sedeNueva,int capacidadNueva,int idPaisNuevo,String sedeVieja) {
		
		PreparedStatement ps = null;
		try {
			ps = conexion.getConexion().prepareStatement("update sede set nombre = ?, capacidad = ?, idpais = ? where nombre = ? ");
			ps.setString(1, sedeNueva);
			ps.setInt(2, capacidadNueva);
			ps.setInt(3, idPaisNuevo);
			ps.setString(4, sedeVieja);
			ps.execute();
			return true;
		}catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public static boolean eliminarPais(int p) {
		PreparedStatement ps = null;
		try {
			eliminarEnCascada(p);
			ps = conexion.getConexion().prepareStatement("delete from pais where (idPais = ?)");
			ps.setInt(1, p);
			ps.execute();
			return true;
		}catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public static boolean eliminarEnCascada(int p)
	{
		PreparedStatement ps = null;
		try {
			ps = conexion.getConexion().prepareStatement("delete from futbolista where(idpais = ?)");
			ps.setInt(1, p);
			ps.execute();
			ps = conexion.getConexion().prepareStatement("delete from sede where(idpais = ?)");
			ps.setInt(1, p);
			ps.execute();
			return true;
		}catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public static void obtenerTablaPais()
	{
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conexion.getConexion().createStatement();
			rs = st.executeQuery("select * from pais");
			System.out.print(" nombre \t");
			System.out.print(" idPais \t");
			System.out.print(" idioma \t");
			System.out.println();
			while(rs.next())
			{
				System.out.printf("%10s",rs.getString("nombre"));
				System.out.printf("%10d",+rs.getInt("idPais"));
				System.out.printf("%20s",rs.getString("idioma"));
				System.out.println();
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	//Mostrar Tabla de Sede
	public static void obtenerTablaSede()
	{
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conexion.getConexion().createStatement();
			rs = st.executeQuery("select * from sede");
			System.out.printf("%-10s"," idSede\t\t");
			System.out.printf("%-15s"," nombre\t");
			System.out.printf("%-10s"," idPais\t");
			System.out.printf("%-10s"," capacidad \t");
			System.out.println();
			while(rs.next())
			{
				System.out.format("%-5d\t\t   %-20s   %-5d   %-5d \n", rs.getInt("idsede"),rs.getString("nombre"),rs.getInt("idPais"),rs.getInt("capacidad"));
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static boolean verificarQueElPaisNoExista(String nombrePais)
	{
		Statement ps = null;
		ResultSet rs = null;
		String actual = "";
		boolean resultado = true;
		try {
			ps = conexion.getConexion().createStatement();
			rs = ps.executeQuery("select * from pais");
			while(rs.next())
			{
				actual = rs.getString("nombre");
				if(actual.equals(nombrePais))
					resultado = false;
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return resultado;
	}
	public static boolean verificarQueLaSedeNoExista(String nombre)
	{
		Statement st = null;
		ResultSet rs = null;
		String actual = "";
		boolean respuesta = true;
		try {
			st = conexion.getConexion().createStatement();
			rs = st.executeQuery("select * from sede");
			while(rs.next())
			{
				actual = rs.getString("nombre");
				if(actual.equals(nombre))
				{
					respuesta = false;
				}
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return respuesta;
	}
	public static boolean cerrarConexion()
	{
		try {
			conexion.getConexion().close();
			return true;
		}catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
}
