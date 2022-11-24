package PatronDAO;
import  Modelo.*;
public interface FutbolistaDAO {
    public boolean agregarFutbolista(Futbolista f);
    public boolean eliminarFutbolista(int id);
    public boolean actualizarFutbolista(int id,Futbolista f);
    public void mostrarTablaFutbolista();
}
