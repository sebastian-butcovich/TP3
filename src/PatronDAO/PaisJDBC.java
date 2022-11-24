package PatronDAO;

import Controlador.conexion;
import Modelo.Pais;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PaisJDBC implements PaisDAO{
    @Override
    public  boolean agregarPais(Pais p)
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
    @Override
    public  boolean actualizarPais(int id,Pais p)
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
    @Override
    public  boolean eliminarPais(int p) {
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
    private boolean eliminarEnCascada(int p)
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
    @Override
    public  void mostrarTablaPais()
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
    public  boolean verificarQueElPaisNoExista(int nombrePais)
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
}
