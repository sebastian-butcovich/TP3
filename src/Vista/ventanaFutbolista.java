package Vista;

import Controlador.conexion;
import PatronDAO.FutbolistaDAO;
import PatronDAO.FutbolistaJDBC;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class ventanaFutbolista extends JFrame {
    private JButton boton1,boton2,boton3,boton4,boton5;
    private JPanel panel,panel1,panel2;
    private JScrollPane js;
    private JTable tabla;
    private TableModel modelo;
    private JLabel titulo;
    ResultSetMetaData rsmd;
    private static int id;
    ResultSet rs;
    private  String[] nombreColumnas= {"ID", "Nombre", "Apellido", "DNI", "Telefono", "Email", "Id Pais", " ","  "};
    private JButton editar, eliminar;
    public ventanaFutbolista()
    {
        this.setTitle("FUTBOLISTA");
        this.setSize(800,500);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        acomodarComponentes();


        this.setVisible(true);
    }
    public void acomodarComponentes()
    {
        panel = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        titulo = new JLabel("Deportistas");
        js = new JScrollPane();
        titulo.setFont(new Font("",Font.BOLD,16));
        panel.setLayout(new GridBagLayout());
        panel1.setLayout(new GridBagLayout());
        panel2.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        boton1 = new JButton("Nuevo");
        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaNuevoFutbolista.getNuevoFutbolista();


            }
        });
        boton2 = new JButton("Exportar CSV");
        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedWriter br= new BufferedWriter(new FileWriter("jugadores.csv"));
                    TableModel modelo = tabla.getModel();
                    for(int i =0; i<modelo.getColumnCount()-2;i++)
                    {
                        br.write(modelo.getColumnName(i)+",");
                    }
                    br.write("\n");
                    for(int k=0; k<modelo.getRowCount();k++)
                    {
                        for(int j=0; j<modelo.getColumnCount()-2;j++)
                        {
                            br.write( modelo.getValueAt(k,j)+",");
                        }
                        br.write("\n");
                    }
                    br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        boton3 = new JButton("Volver");
        boton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        constructorTabla();

        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.gridwidth = 1;
        g.insets = new Insets(10,10,10,10);
        panel1.add(boton1,g);
        g.gridx = 1;
        g.gridy = 0;
        panel1.add(boton2,g);
        g.gridx = 2;
        g.gridy = 0;
        panel1.add(boton3,g);
        g.gridx = 1;
        g.gridy = 0;
        g.weighty = 0.25;
        g.weightx = 1;
        g.anchor = GridBagConstraints.EAST;
        panel.add(panel1,g);
        g.gridx = 1;
        g.gridy = 1;
        g.insets = new Insets(0, 0, 0, 0);
        g.anchor = GridBagConstraints.CENTER;
        g.weightx = 1.0;
        g.weighty = 0.10;
        g.fill = GridBagConstraints.BOTH;
        panel2.setBackground(Color.lightGray);
        panel2.add(titulo);
        panel.add(panel2, g);
        g.anchor = GridBagConstraints.CENTER;
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 3;
        g.weightx = 1.0;
        g.weighty = 1.0;
        g.fill = GridBagConstraints.BOTH;
        panel.add(new JScrollPane(tabla),g);
        this.add(panel);
    }
    public void constructorTabla()
    {
        tabla = new JTable();
        boton4 = new JButton("Modificar");
        boton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   VentanaEditarFutbolista.getEditarFutbolista();
            }
        });
        boton5 = new JButton("Eliminar");
        boton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FutbolistaDAO f = new FutbolistaJDBC();
                int rta = JOptionPane.showConfirmDialog(null,"Desea eliminar esta entrada?","Confirmacion",JOptionPane.YES_NO_OPTION);
                if(rta==0) {
                    f.eliminarFutbolista(id);
                }
                else{
                    JOptionPane.showMessageDialog(null,"No se eliminara esta entrada");
                }
            }
        });
        parametrosTabla();
        tabla.setModel(new miModelo(rs,rsmd,nombreColumnas));
        tabla.getColumn(" ").setCellRenderer(new renderer("Modificar"));
        tabla.getColumn(" ").setCellEditor(new btnEditor(new JCheckBox(),boton4));
        tabla.getColumn("  ").setCellEditor(new btnEditor(new JCheckBox(),boton5));
        tabla.getColumn("  ").setCellRenderer(new renderer("Eliminar"));
        tabla.setRowHeight(30);
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = e.getY()/tabla.getRowHeight();
                int column =  tabla.getColumnModel().getColumnIndexAtX(e.getX());
                Object value;
                if(row >= 0 && row < tabla.getRowCount() && column >= 0 && column< tabla.getColumnCount())
                {
                    TableModel tm = tabla.getModel();
                    id = (int) tm.getValueAt(row,0);
                    System.out.println(id);
                }
            }
        });

    }
    public static int ID()
    {
        return id;
    }



    public void parametrosTabla()
    {
            Connection c = conexion.GetConexion("mundial_futbol_2022","root","Sebastian667");
            try {
                String query = "select * from futbolista order by(nombre)";
                Statement st = c.createStatement();
                st = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs = st.executeQuery(query);
                rsmd = rs.getMetaData();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

 class miModelo extends AbstractTableModel {

    private ResultSet rs;
    private ResultSetMetaData rsmd;
    private String[]nombres;
    public miModelo(ResultSet rs, ResultSetMetaData rsmd,String[]nombres)
    {
        this.rs = rs;
        this.rsmd = rsmd;
        this.nombres = nombres;
    }
    @Override
    public int getRowCount() {
        try{
            rs.last();
            return rs.getRow();
        }catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        try{
            return rsmd.getColumnCount()+2;
        }catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try{
            rs.absolute(rowIndex + 1);
            if(columnIndex< getColumnCount()-2) {
                return rs.getObject(columnIndex + 1);
            }else
                return null;
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getColumnName(int column){
       return nombres[column];
    }
    @Override
     public boolean isCellEditable(int row,int column)
     {
         return true;
     }
}
class renderer extends JButton implements TableCellRenderer{
    private String nombre;
    public renderer(String nombre)
    {
        this.nombre = nombre;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
       setText( (value == null) ? nombre : value.toString());
       return this;
    }


}
class btnEditor extends DefaultCellEditor{
    private String label;
    private final JButton btn;
    public btnEditor(JCheckBox checkBox,JButton btn) {
        super(checkBox);
        this.btn = btn;
    }
    public Component getTableCellEditorComponent(JTable tabla,Object value,boolean isSelected,int row,int column)
    {
        label =(value==null) ? btn.getText() : value.toString();
        btn.setText(label);
        return btn;
    }
    public Object getCellEditorValue() {
        return new String(label);
    }
}






