package Vista;

import Controlador.conexion;
import PatronDAO.PaisDAO;
import PatronDAO.PaisJDBC;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class VentanaPais extends JFrame {
    private JButton nuevo,volver,modificar,eliminar;
    private JPanel panel,paneLabel,panelBotones,panelTabla,interno ;
    private JTable tabla;
    private JLabel nombreTabla;
    private static int  id;
    private String[] nombre={"ID","Nombre","Idioma"," ","  "};
    public VentanaPais()
    {
        this.setTitle("Pais");
        this.setSize(800,500);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        posicionarComponentes();
        this.add(panel);
        this.setVisible(true);
    }
    public static int getId()
    {
        return id;
    }
    public void posicionarComponentes()
    {
        /*-------------------------------Inicializar Componentes ------------------------------------------*/
        GridBagConstraints g = new GridBagConstraints();
        nuevo = new JButton("Nuevo");
        nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaNuevoPais();
            }
        });
        volver = new JButton("Volver");
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        modificar = new JButton("Modificar");
        modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaModificarPais();
            }
        });
        eliminar = new JButton("Eliminar");
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaisDAO pd = new PaisJDBC();
                int rta = JOptionPane.showConfirmDialog(null,"Desea eliminar esta entrada?","Confirmacion",JOptionPane.YES_NO_OPTION);
                if(rta==0) {
                    pd.eliminarPais(id);
                }
                else{
                    JOptionPane.showMessageDialog(null,"No se eliminara esta entrada");
                }
            }
        });
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
            Connection c= conexion.getConexion();
            Statement st = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("select * from pais order by(nombre)");
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

}

