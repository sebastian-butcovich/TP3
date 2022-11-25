package PatronDAO;

import Modelo.Pais;

public interface PaisDAO {
    public boolean agregarPais(Pais p);
    public boolean eliminarPais(int id);
    public boolean actualizarPais(int id,Pais p);
    public void mostrarTablaPais();
    public  boolean verificarRepetido(String nombrePais);
}
