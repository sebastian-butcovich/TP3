package PatronDAO;

import Controlador.conexion;
import Modelo.Futbolista;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FutbolistaJDBC implements FutbolistaDAO {
    @Override
    public  void mostrarTablaFutbolista()
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
        }catch(SQLException e)
        {
            e.getStackTrace();
        }
    }
    @Override
    public  boolean agregarFutbolista(Futbolista f) {
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
    @Override
    public  boolean actualizarFutbolista(int id, Futbolista f)
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
    @Override
    public  boolean  eliminarFutbolista(int f)
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
}

