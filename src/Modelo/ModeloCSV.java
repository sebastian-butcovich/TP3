package Modelo;

public class ModeloCSV implements Comparable<ModeloCSV> {
    private String fecha;
    private String local;
    private String visitante;
    private int golesLocales;
    private int golesVisitantes;
    private String torneo;
    private String ciudad;
    private String pais;
    private boolean neutral;

    public ModeloCSV()
    {

    }
    public ModeloCSV(String f,String local,String visitante,int golesLocales,int golesVisitantes,String torneo,String ciudad,String pais,boolean neutral)
    {
        this.fecha = f;
        this.local = local;
        this.visitante = visitante;
        this.golesLocales = golesLocales;
        this.golesVisitantes = golesVisitantes;
        this.torneo = torneo;
        this.ciudad = ciudad;
        this.pais = pais;
        this.neutral = neutral;
    }

    public void setFecha(String f)
    {
        this.fecha = f;
    }
    public String getFecha()
    {
        return this.fecha;
    }
    public void setLoca(String local)
    {
        this.local = local;
    }
    public String getLocal()
    {
        return this.local;
    }
    public void setVisitante(String visitante)
    {
        this.visitante = visitante;
    }
    public String getVisitante()
    {
        return this.visitante;
    }
    public void setGolesLocales(int golesLocales)
    {
        this.golesLocales = golesLocales;
    }
    public int getGolesLocales()
    {
        return golesLocales;
    }
    public void setGolesVisitantes(int golesVisitantes)
    {
        this.golesVisitantes = golesVisitantes;
    }
    public int getGolesVisitantes()
    {
        return this.golesVisitantes;
    }
    public void setTorneo(String torneo)
    {
        this.torneo = torneo;
    }
    public String getTorneo()
    {
        return this.torneo;
    }
    public void setCiudad(String ciudad)
    {
        this.ciudad = ciudad;
    }
    public String getCiudad()
    {
        return this.torneo;
    }
    public void setPais(String pais)
    {
        this.pais = pais;
    }
    public String getPais()
    {
        return this.pais;
    }
    public void setNeutral(boolean neutral)
    {
        this.neutral = neutral;
    }
    public boolean getNeutral()
    {
        return this.neutral;
    }
    public int compareTo(ModeloCSV o)
    {
        return this.getLocal().compareTo(o.getLocal());
    }









}
