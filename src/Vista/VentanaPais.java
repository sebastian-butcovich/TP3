package Vista;

import Controlador.conexion;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class VentanaPais extends JFrame {
    private JButton nuevo,volver,modificar,eliminar;
    private JPanel panel,paneLabel,panelBotones,panelTabla,interno ;
    private JTable tabla;
    private JLabel nombreTabla;
    private String[] nombre={"ID","Nombre","Idioma"," ","  "};
    public VentanaPais()
    {
        this.setTitle("Pais");
        this.setSize(800,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        posicionarComponentes();
        this.add(panel);
        this.setVisible(true);
    }
    public void posicionarComponentes()
    {
        /*-------------------------------Inicializar Componentes ------------------------------------------*/
        GridBagConstraints g = new GridBagConstraints();
        nuevo = new JButton("Nuevo");
        volver = new JButton("Volver");
        modificar = new JButton("Modificar");
        eliminar = new JButton("Eliminar");
        panel = new JPanel();
        paneLabel = new JPanel();
        panelBotones = new JPanel();
        panelTabla = new JPanel();
        tabla = new JTable();
        tabla.setRowHeight(30);
        nombreTabla = new JLabel("Pais");
        ResultSet rs;
        ResultSetMetaData rsmd;
        try{
            Connection c= conexion.GetConexion("mundial_futbol_2022","root","Sebastian667");
            Statement st = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("select * from pais");
            rsmd = rs.getMetaData();
            tabla.setModel(new miModelo(rs,rsmd,nombre));
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        tabla.getColumn(" ").setCellRenderer(new renderer("Modificar"));
        tabla.getColumn(" ").setCellEditor(new btnEditor(new JCheckBox(),modificar));
        tabla.getColumn("  ").setCellRenderer(new renderer("Eliminar"));
        tabla.getColumn("  ").setCellEditor(new btnEditor(new JCheckBox(),eliminar));
        /*------------------------------- Posicionar Componentes ------------------------------------------*/

        acomodarPaneles();

    }
    public void acomodarPaneles()
    {
        GridBagConstraints g = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());
        panelBotones.setBackground(new Color(14,100,100));
        paneLabel.setBackground(new Color(100,100,100));
        panel.setBackground(Color.BLACK);
        g.fill = GridBagConstraints.BOTH;
        g.weighty = .70;
        g.weightx = .70;
        g.gridx = 0;
        g.gridy = 0;
        panel.add(panelBotones,g);
        g.weighty = .1;
        g.weightx = .1;
        g.gridy = 1;
        panel.add(paneLabel,g);
        g.weighty = 1.0;
        g.weightx = 1.0;
        g.gridy = 2;
        panel.add(panelTabla,g);
        acomodarBotones();
        acomodarLabel();
        acomodarTabla();
    }
    public void acomodarBotones()
    {
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        interno = new JPanel();
        panel1.setBackground(new Color(14,100,100));
        panel2.setBackground(new Color(14,100,100));
        panel3.setBackground(new Color(14,100,100));
        interno.setBackground(new Color(14,100,100));
        panelBotones.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.BOTH;
        g.weightx = 1.0;
        g.weighty = 1.0;
        g.gridx = 0;
        g.gridy = 0;
        panelBotones.add(panel1,g);
        g.gridx = 2;
        panelBotones.add(panel2,g);
        g.weightx = 1.0;
        g.weighty = 1.0;
        g.gridx = 1;
        g.gridy = 1;
        panelBotones.add(interno,g);
        g.gridy = 2;
        g.weightx = 1.0;
        g.weighty = 1.0;
        panelBotones.add(panel3,g);
        acomodarPanelInterno();
    }
    public void acomodarPanelInterno()
    {
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(14,100,100));
        interno.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.weightx = 1.0;
        g.weighty = 1.0;
        g.fill = GridBagConstraints.BOTH;
        g.insets = new Insets(10,10,10,10);
        g.gridx = 0;
        g.gridy = 1;
        interno.add(panel1,g);
        g.gridx = 1;
        g.gridy = 1;
        interno.add(nuevo,g);
        g.gridx = 2;
        interno.add(volver,g);
    }
    public void acomodarLabel()
    {
        paneLabel.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        nombreTabla = new JLabel("Pais");
        nombreTabla.setFont(new Font("",Font.ITALIC,30));
        g.anchor = GridBagConstraints.CENTER;
        paneLabel.add(nombreTabla,g);
    }
    public void acomodarTabla()
    {
        GridBagConstraints g = new GridBagConstraints();
        panelTabla.setLayout(new GridBagLayout());
        g.weightx = 1.0;
        g.weighty = 1.0;
        g.fill = GridBagConstraints.BOTH;
        panelTabla.add(new JScrollPane(tabla),g);
    }
    public static void main(String[]args)
    {
        new VentanaPais();
    }

}

