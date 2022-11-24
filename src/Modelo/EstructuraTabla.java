package Modelo;

import java.util.ArrayList;

public class EstructuraTabla {
    private String nombrePais;
    private int cantPartidosGanados;
    private int cantPartidosPerdidos;
    private int cantPartidosEmpatados;
    private int cantTorneos;
    private ArrayList<String> torneosJugados;

    public EstructuraTabla(String nombre,int cantG,int cantP,int cantE,int cantT)
    {
        this.nombrePais = nombre;
        this.cantPartidosGanados = cantG;
        this.cantPartidosPerdidos = cantP;
        this.cantPartidosEmpatados = cantE;
        this.cantTorneos = cantT;
    }
    public EstructuraTabla(){
        torneosJugados = new ArrayList<String>();
    }
    public void setNombrePais(String nombrePais)
    {
        this.nombrePais = nombrePais;
    }
    public String getNombrePais()
    {
        return nombrePais;
    }
    public void setCantPartidosGanados(int cantp)
    {
        this.cantPartidosGanados = cantp;
    }
    public int getCantPartidosGanados(){return this.cantPartidosGanados;}
    public void setCantPartidosPerdidos(int partidosPerdidos){this.cantPartidosPerdidos = partidosPerdidos;}
    public int getCantPartidosPerdidos(){return cantPartidosPerdidos;}
    public void setCantPartidosEmpatados(int partidosEmpatados){this.cantPartidosEmpatados = partidosEmpatados;}
    public int getCantPartidosEmpatados(){return this.cantPartidosEmpatados;}
    public void setCantTorneos(int torneosEmpatados){this.cantPartidosEmpatados = torneosEmpatados;}
    public int getCantTorneos(){return this.torneosJugados.size();}
    public void setTorneosJugados(ArrayList<String>s)
    {
        torneosJugados = s;
    }
    public ArrayList<String> getTorneosJugados(){return torneosJugados;}

}
