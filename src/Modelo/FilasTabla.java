package Modelo;

import Controlador.LeerCSV;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FilasTabla {
    private int ubicacionLoca, ubicacionVisitante;
    private static Ubicacion ubi;

    public FilasTabla() {

    }

    public static ArrayList<EstructuraTabla> agregarElemento(ArrayList<ModeloCSV> listaCSV) {
        ArrayList<EstructuraTabla> lista = new ArrayList<EstructuraTabla>();
        ubi = new Ubicacion();
        ModeloCSV m;
        EstructuraTabla local = null;
        EstructuraTabla visitante = null;
        for (int i = 0; i < listaCSV.size(); i++) {
            m = listaCSV.get(i);
            estaEnLaTabla(m, lista);
            if (!ubi.estaLocal) {
                local = new EstructuraTabla();
                local.setNombrePais(m.getLocal());
                lista.add(local);
            } else {
                local = lista.get(ubi.ubicacionLocal);
            }
            if (!ubi.estaVisitante) {
                visitante = new EstructuraTabla();
                visitante.setNombrePais(m.getVisitante());
                lista.add(visitante);
            } else {
                visitante = lista.get(ubi.ubicacionVisitante);
            }
            if (ubi.getEmpate()) {
                local.setCantPartidosEmpatados(local.getCantPartidosEmpatados() + 1);
                visitante.setCantPartidosEmpatados(visitante.getCantPartidosEmpatados() + 1);
            } else if (ubi.getGanoLocal()) {
                local.setCantPartidosGanados(local.getCantPartidosGanados() + 1);
                visitante.setCantPartidosPerdidos(visitante.getCantPartidosPerdidos() + 1);
            } else {
                visitante.setCantPartidosGanados(visitante.getCantPartidosGanados() + 1);
                local.setCantPartidosPerdidos(local.getCantPartidosPerdidos() + 1);
            }
            if (!m.getTorneo().equals("Friendly")) {
                if (!local.getTorneosJugados().contains(m.getTorneo())){
                    local.getTorneosJugados().add(m.getTorneo());

                }
                if(!visitante.getTorneosJugados().contains(m.getTorneo()))
                {
                    visitante.getTorneosJugados().add(m.getTorneo());
                }
            }
            ubi.setEstaVisitante(false);
            ubi.setEstaLocal(false);
        }
        return lista;

    }

    public static void estaEnLaTabla(ModeloCSV m, ArrayList<EstructuraTabla> lista) {
        for (int k = 0; k < lista.size(); k++) {
            if (m.getLocal().equals(lista.get(k).getNombrePais())) {
                ubi.setEstaLocal(true);
                ubi.setUbicacionLocal(k);
            }
            if (m.getVisitante().equals(lista.get(k).getNombrePais())) {
                ubi.setEstaVisitante(true);
                ubi.setUbicacionVisitante(k);

            }
        }

        if (m.getGolesLocales() == m.getGolesVisitantes()) {
            ubi.setEmpate(true);
        } else if (m.getGolesLocales() > m.getGolesVisitantes()) {
            ubi.setGanoLocal(true);
            ubi.setEmpate(false);
        } else {
            ubi.setGanoLocal(false);
            ubi.setEmpate(false);
        }
    }


    private static class Ubicacion {
        private boolean estaLocal;
        private boolean estaVisitante;
        private int ubicacionLocal;
        private int ubicacionVisitante;
        private boolean empate;
        private boolean ganoLocal;
        private ArrayList<String> torneosJugados;

        public Ubicacion() {
        }

        public Ubicacion(boolean estaLocal, boolean estaVisitante, int posicionLocal, int posicionVisitante, boolean empate, boolean ganoLocal, String[] torneosJugados) {
            this.estaLocal = estaLocal;
            this.estaVisitante = estaVisitante;
            this.ubicacionLocal = posicionLocal;
            this.ubicacionVisitante = posicionVisitante;
            this.empate = empate;
            this.ganoLocal = ganoLocal;

        }

        public void setEstaLocal(boolean esta) {
            this.estaLocal = esta;
        }

        public boolean getEstaLocal() {
            return estaLocal;
        }

        public void setEstaVisitante(boolean esta) {
            this.estaVisitante = esta;
        }

        public boolean getEstaVisitante() {
            return estaVisitante;
        }

        public void setUbicacionLocal(int local) {
            this.ubicacionLocal = local;
        }

        public int getUbicacionLocal() {
            return ubicacionLocal;
        }

        public void setUbicacionVisitante(int visitante) {
            this.ubicacionVisitante = visitante;
        }

        public int getUbicacionVisitante() {
            return ubicacionVisitante;
        }

        public void setEmpate(boolean empate) {
            this.empate = empate;
        }

        public boolean getEmpate() {
            return empate;
        }

        public void setGanoLocal(boolean ganoLocal) {
            this.ganoLocal = ganoLocal;
        }

        public boolean getGanoLocal() {
            return ganoLocal;
        }
        public ArrayList<String> getTorneosJugados()
        {
            return torneosJugados;
        }

        public void agregarTorneo(String nombre) {
            if (!torneosJugados.contains(nombre)) {
                torneosJugados.add(nombre);
            }
        }
    }
}
