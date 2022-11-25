package Vista;

import Controlador.LeerCSV;
import Modelo.EstructuraTabla;
import Modelo.FilasTabla;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Estadisticas extends JFrame {
    private JTable tabla;
    private JButton importarDatos;
    private JPanel panel,panelSuperior,panelInferior,panelBoton;

    public Estadisticas()
    {
        this.setSize(800,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        inicializarComponentes();
        this.add(panel);
        this.setVisible(true);
    }
    public void inicializarComponentes()
    {
        panel = new JPanel();

        tabla = new JTable();
        tabla.setModel(new modeloEstadisticas());
        panelInferior = new JPanel();
        panelSuperior = new JPanel();
        panelBoton = new JPanel();
        importarDatos = new JButton("Importar Datos");
        importarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabla.isVisible())
                {
                    tabla.setVisible(false);
                }
                else
                {
                    tabla.setVisible(true);
                }
            }
        });
        acomodar();
    }
    public void acomodar()
    {
        GridBagConstraints g = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());
        g.insets = new Insets(10,10,10,10);
        g.weightx = 1.0;
        g.weighty = 1.0;
        g.fill = GridBagConstraints.BOTH;
        panel.add(panelSuperior,g);
        g.weightx = .5;
        g.weighty = .5;
        g.gridy = 1;
        panel.add(panelInferior,g);
        acomodarPanelSuperior();
        acomodarPanelInferior();
    }
    public void acomodarPanelSuperior()
    {
        GridBagConstraints g = new GridBagConstraints();
        panelSuperior.setLayout(new GridBagLayout());
        g.weightx = 1.0;
        g.weighty = 1.0;
        g.fill = GridBagConstraints.BOTH;
        panelSuperior.add(new JScrollPane(tabla),g);
    }
    public void acomodarPanelInferior()
    {
        GridBagConstraints g = new GridBagConstraints();
        panelInferior.setLayout(new GridBagLayout());
        g.weighty = 1.0;
        g.weightx = 1.0;
        g.fill = GridBagConstraints.BOTH;
        g.gridx = 0;
        g.gridy = 0;
        g.gridheight = 2;
        panelInferior.add(new JPanel(),g);
        g.gridheight = 1;
        g.gridx = 1;
        panelInferior.add(new JPanel(),g);
        g.gridy = 1;
        panelInferior.add(panelBoton,g);
        g.gridx = 2;
        g.gridheight = 2;
        panelInferior.add(new JPanel(),g);
        g.gridx = 1;
        g.gridy = 2;
        g.gridheight = 1;
        panelInferior.add(new JPanel(),g);
        acomodarPanelBoton();
    }
    public void acomodarPanelBoton()
    {
        panelBoton.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.weighty = 1.0;
        g.weightx = 1.0;
        g.fill = GridBagConstraints.BOTH;
        g.gridx = 0;
        g.gridy = 0;
        g.gridheight = 2;
        panelBoton.add(new JPanel(),g);
        g.gridheight = 1;
        g.gridx = 1;
        panelBoton.add(new JPanel(),g);
        g.gridy = 1;
        panelBoton.add(importarDatos,g);
        g.gridx = 2;
        g.gridheight = 2;
        panelBoton.add(new JPanel(),g);
        g.gridx = 1;
        g.gridy = 2;
        g.gridheight = 1;
        panelBoton.add(new JPanel(),g);

    }


}
class modeloEstadisticas extends AbstractTableModel{
    private ArrayList<EstructuraTabla>lista;
    private String[] nombresColumnas = {"Pais","Partidos Ganados","Partidos Empatados","Partidos Perdidos","Cant Torneos"};
    public modeloEstadisticas()
    {
        lista = FilasTabla.agregarElemento(LeerCSV.listaCSV());
    }

    @Override
    public int getRowCount() {
        return  lista.size();
    }

    @Override
    public int getColumnCount() {
        return nombresColumnas.length ;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0)
        {
            return lista.get(rowIndex).getNombrePais();
        }
        if(columnIndex == 1)
        {
            return lista.get(rowIndex).getCantPartidosGanados();
        }
        if(columnIndex == 2)
        {
            return lista.get(rowIndex).getCantPartidosPerdidos();
        }
        if(columnIndex == 3)
        {
           return lista.get(rowIndex).getCantPartidosEmpatados();
        }
        if(columnIndex == 4)
        {
           return lista.get(rowIndex).getCantTorneos();
        }
        return null;
    }
    @Override
    public String getColumnName(int colum)
    {
        return nombresColumnas[colum];
    }
}
