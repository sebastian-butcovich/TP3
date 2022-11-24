package PatronDAO;

import Controlador.conexion;
import Modelo.Sede;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SedeJDBC implements SedeDAO {
    @Override
    public  boolean agregarSede(Sede s )
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
    @Override
    public  boolean eliminarSede(int s)
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
    @Override
    public  boolean actualizarSede(Sede sede,int id) {

        PreparedStatement ps = null;
        try {
            ps = conexion.getConexion().prepareStatement("update sede set nombre = ?, capacidad = ?, idpais = ? where nombre = ? ");
            ps.setString(1, sede.getNombre());
            ps.setInt(2, sede.getCapacidad());
            ps.setInt(3, sede.getPais());
            ps.setInt(4, id);
            ps.execute();
            return true;
        }catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public  void mostrarTablaSede()
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
    public  boolean verificarQueLaSedeNoExista(String nombre)
    {
        Statement st = null;
        ResultSet rs = null;
        String actual = "";
        boolean respuesta = true;
        try {
            st = conexion.getConexion().createStatement();
            rs = st.executeQuery("select idpais from sede");
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
}
