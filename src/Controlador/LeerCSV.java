package Controlador;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import Modelo.ModeloCSV;

public class LeerCSV {
    private static ArrayList<ModeloCSV> lista=null;
    public static ArrayList<ModeloCSV> listaCSV ()
    {

       FileReader file;
       String linea;
       String split = ",";
       ModeloCSV m;
       try {
         BufferedReader bf = new BufferedReader(new FileReader("results.csv"));
         lista = new ArrayList<ModeloCSV>();
         linea = bf.readLine();
        while((linea = bf.readLine())!=null)
        {
            m =armarModeloCSV(linea);
            if(m!=null) {
                lista.add(m);
                System.out.println("Listo");
            }else
            {
                System.out.println("Es nulo");
            }
        }
        return lista;
       }
       catch(IOException e)
       {
        e.printStackTrace();
        return null;
       }
    }
    public static ModeloCSV armarModeloCSV(String linea)
    {
        ModeloCSV modelo = new ModeloCSV();
        String[] lineaSeparada = linea.split(",");
        if(lineaSeparada.length !=0)
        {
            modelo.setFecha(lineaSeparada[0]);
            modelo.setLoca(lineaSeparada[1]);
            modelo.setVisitante(lineaSeparada[2]);
            modelo.setGolesLocales(Integer.parseInt(lineaSeparada[3]));
            modelo.setGolesVisitantes(Integer.parseInt(lineaSeparada[4]));
            modelo.setTorneo(lineaSeparada[5]);
            modelo.setCiudad(lineaSeparada[6]);
            modelo.setPais(lineaSeparada[7]);
            modelo.setNeutral(Boolean.parseBoolean(lineaSeparada[8]));
            return modelo;
        }else
        {
            return null;
        }

    }
}
