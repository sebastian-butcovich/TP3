package PatronDAO;

import Modelo.Sede;

public interface SedeDAO {
    public boolean agregarSede(Sede s);
    public boolean eliminarSede(int id);
    public boolean actualizarSede(Sede s,int id);
    public void mostrarTablaSede();

    public boolean verificarQueLaSedeNoExista(String nombre);
}
