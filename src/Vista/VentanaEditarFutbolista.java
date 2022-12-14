package Vista;

import Controlador.conexion;
import Modelo.Futbolista;
import PatronDAO.FutbolistaDAO;
import PatronDAO.FutbolistaJDBC;

import  javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class VentanaEditarFutbolista extends JFrame {
    private JLabel nombre, apellido, email, telefono, pais, documento;
    private JTextField textNombre, textApellido, textEmail, textTelefono, textPais, textDocumento;
    private JButton guardar;
    private JPanel panel, panel1, panel2, panel3, panel4, panel5, panel6, panel7, panelIzq, panelDer;
    private static Futbolista f;
    private static VentanaEditarFutbolista nf;
    private JList<String>list;

    private VentanaEditarFutbolista() {

        super("Editar Futbolista");
        this.setSize(800, 500);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
        acomodarComponentes();
        this.setVisible(true);

    }


    public static VentanaEditarFutbolista getEditarFutbolista() {
        if (nf == null) {
            return new VentanaEditarFutbolista();
        } else {
            return nf;
        }
    }

    public void acomodarComponentes() {
        /*----------------------------------------------------------------*/
        panel = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        panel7 = new JPanel();
        panelIzq = new JPanel();
        panelDer = new JPanel();


        /*----------------------------------------------------------------*/
        panel.setLayout(new GridBagLayout());
        panel1.setLayout(new GridBagLayout());
        panel2.setLayout(new GridBagLayout());
        panel3.setLayout(new GridBagLayout());
        panel4.setLayout(new GridBagLayout());
        panel5.setLayout(new GridBagLayout());
        panel6.setLayout(new GridBagLayout());
        panel7.setLayout(new GridBagLayout());

        /*----------------------------------------------------------------*/
        GridBagConstraints g = new GridBagConstraints();

        /*----------------------------------------------------------------*/
        nombre = new JLabel("Nombre");
        nombre.setFont(new Font("Arial", Font.ITALIC, 20));
        apellido = new JLabel("Apellido");
        apellido.setFont(new Font("Arial", Font.PLAIN, 20));
        email = new JLabel("E-mail");
        email.setFont(new Font("Arial", Font.PLAIN, 20));
        pais = new JLabel("Pais");
        pais.setFont(new Font("Arial", Font.PLAIN, 20));
        telefono = new JLabel("Telefono");
        telefono.setFont(new Font("Arial", Font.PLAIN, 20));
        documento = new JLabel("Documento");
        documento.setFont(new Font("Arial", Font.PLAIN, 20));
        guardar = new JButton("Guardar");
        /*----------------------------------------------------------------*/
        textNombre = new JTextField(20);
        textNombre.setFont(new Font("Letra", Font.PLAIN, 20));
        textApellido = new JTextField(20);
        textApellido.setFont(new Font("Letra", Font.PLAIN, 20));
        textEmail = new JTextField(20);
        textEmail.setFont(new Font("Letra", Font.PLAIN, 20));
        textPais = new JTextField(20);
        textPais.setFont(new Font("Letra", Font.PLAIN, 20));
        textTelefono = new JTextField(20);
        textTelefono.setFont(new Font("Letra", Font.PLAIN, 20));
        textDocumento = new JTextField(20);
        textDocumento.setFont(new Font("Letra", Font.PLAIN, 20));
        /*----------------------------------------------------------------*/
        //Funccion para que el boton guarde los datos
        guardar.addActionListener(new VentanaEditarFutbolista.miAction());
        Connection c = conexion.getConexion();
        try{
            Statement st =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs2 =st.executeQuery("select idpais from pais");
            rs2.last();
            String[] idletra = new String[rs2.getRow()];
            rs2.close();
            ResultSet rs =st.executeQuery("select idpais,nombre from pais");
            int i=0;
            while(rs.next())
            {
                idletra[i]= rs.getInt("idpais")+" "+rs.getString("nombre");

                i++;

            }
            list = new JList<String>(idletra);
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        /*----------------------------------------------------------------*/
        //Interiores de los paneles
        // Panel 6
        g.weightx = 1.0;
        g.weighty = 1.0;
        g.fill = GridBagConstraints.BOTH;
        g.gridx = 0;
        g.gridy = 0;
        g.gridheight = 2;
        panel6.add(new JPanel(), g);
        g.gridheight = 1;
        g.gridx = 1;
        g.gridy = 1;
        panel6.add(guardar, g);
        g.gridy = 0;
        g.gridx = 2;
        panel6.add(new JPanel(), g);
        g.gridx = 0;
        g.gridy = 2;
        g.weighty = 0.5;
        g.gridwidth = 3;
        panel6.add(new JPanel(), g);
        g.gridwidth = 1;
        g.weighty = 1;
        //panel 1
        g.gridx = 0;
        g.gridy = 0;
        g.gridheight = 1;
        panel1.add(new JPanel(), g);
        g.gridx = 1;
        g.gridy = 1;
        panel1.add(nombre, g);
        g.gridx = 2;
        panel1.add(textNombre, g);
        g.gridx = 3;
        g.gridy = 0;
        panel1.add(new JPanel(), g);
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 3;
        panel1.add(new JPanel(), g);
        g.gridwidth = 1;
        //Panel 2
        g.weightx = 1.0;
        g.weighty = 1.0;
        g.gridx = 0;
        g.gridy = 0;
        g.gridheight = 1;
        g.gridwidth = 1;
        panel2.add(new JPanel(), g);
        g.weightx = 1.0;
        g.gridx = 1;
        g.gridy = 1;
        panel2.add(apellido, g);
        g.gridx = 2;
        panel2.add(textApellido, g);
        g.gridx = 3;
        g.gridy = 0;
        g.weightx = 1.0;
        panel2.add(new JPanel(), g);
        g.weightx = 1.0;
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 3;
        panel2.add(new JPanel(), g);
        g.gridwidth = 1;
        //Panel 3
        g.weightx = 1.0;
        g.weighty = 1.0;
        g.gridx = 0;
        g.gridy = 0;
        panel3.add(new JPanel(), g);
        g.gridx = 1;
        g.gridy = 1;
        panel3.add(email, g);
        g.gridx = 2;
        panel3.add(textEmail, g);
        g.gridx = 3;
        g.gridy = 0;
        panel3.add(new JPanel(), g);
        g.gridx = 0;
        g.gridy = 2;
        panel3.add(new JPanel(), g);
        /*----------------------------------------------------------------*/
        // Panel 4
        g.gridx = 0;
        g.gridy = 0;
        panel4.add(new JPanel(), g);
        g.gridy = 1;
        g.gridx = 1;
        panel4.add(telefono, g);
        g.gridx = 2;
        panel4.add(textTelefono, g);
        g.gridx = 3;
        g.gridy = 0;
        panel4.add(new JPanel(), g);
        g.gridx = 0;
        g.gridy = 2;
        panel4.add(new JPanel(), g);

        //Panel 5
        g.gridx = 0;
        g.gridy = 0;
        panel5.add(new JPanel(), g);
        g.gridwidth = 1;
        g.gridx = 1;
        g.gridy = 1;
        panel5.add(pais, g);
        g.gridx = 2;
        panel5.add(list, g);
        g.gridx = 3;
        panel5.add(new JPanel(), g);
        g.gridy = 2;
        panel5.add(new JPanel(), g);
        //Panel 7
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.gridheight = 1;
        panel7.add(new JPanel(), g);
        g.gridx = 3;
        panel7.add(new JPanel(), g);
        g.gridx = 1;
        g.gridy = 1;
        panel7.add(documento, g);
        g.gridx = 2;
        panel7.add(textDocumento, g);
        g.gridy = 2;
        panel7.add(new JPanel(), g);
        /*----------------------------------------------------------------*/

        //Paneles
        g.insets = new Insets(5, 5, 5, 5);
        g.weightx = .5;
        g.weighty = .5;
        g.fill = GridBagConstraints.BOTH;
        g.gridy = 0;
        g.gridx = 0;
        g.gridheight = 7;
        panel.add(panelIzq, g);
        g.gridx = 2;
        g.gridy = 0;
        panel.add(panelDer, g);
        g.weightx = 1.0;
        g.weighty = 1.0;
        g.gridheight = 1;
        g.gridx = 1;
        g.gridy = 0;
        panel.add(panel1, g);
        g.gridy = 1;
        panel.add(panel2, g);
        g.gridy = 2;
        panel.add(panel3, g);
        g.gridy = 3;
        panel.add(panel7, g);
        g.gridy = 4;
        panel.add(panel4, g);
        g.gridy = 5;
        panel.add(panel5, g);
        g.gridy = 6;
        panel.add(panel6, g);
        this.add(panel);
    }

    public static Futbolista getFutbolista() {
        return f;
    }


    private class CamposException extends Exception{
        public CamposException() {
        }
    }
    private class LetrasException extends Exception{
        public LetrasException() {
        }
    }
    private class EmailException extends Exception{
        public EmailException() {
        }
    }
    private class NumTelefonoException extends Exception{
        public NumTelefonoException() {
        }
    }
    private class miAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                f = new Futbolista();
                FutbolistaDAO f2 = new FutbolistaJDBC();
                int campos = 0;
                if (textNombre.getText().matches("[a-zA-Z \s]+")) {
                    f.setNombre(textNombre.getText().replaceAll("\s", ""));
                    campos++;
                } else
                    throw new LetrasException();
                if (textApellido.getText().matches("[a-zA-Z \s]+")) {
                    f.setApellido(textApellido.getText().replaceAll("\s", ""));
                    campos++;
                } else
                    throw new LetrasException();
                f.setDocIdentidad(textDocumento.getText());
                campos++;
                if (textEmail.getText().contains("@")) {
                    f.setEmail(textEmail.getText());
                    campos++;
                } else
                    throw new EmailException();
                if (textTelefono.getText().matches("[0-9]+")) {
                    f.setTelefono(textTelefono.getText());
                    campos++;
                } else
                    throw new NumTelefonoException();
                String[] s = list.getSelectedValue().split(" ");
                f.setPais(Integer.parseInt(s[0]));
                campos++;
                if (campos != 5) {
                    throw new CamposException();
                }
                f2.actualizarFutbolista(ventanaFutbolista.ID(), f);
                dispose();
            } catch (
                   CamposException e1) {
                String mensaje = "Numero incorrecto de campos ingresados";
                VentanaError.error(mensaje);
            } catch (
                    LetrasException e2) {
                String mensaje = "Caracteres incorrectos en nombre o apellido";
                VentanaError.error(mensaje);
            } catch (
                    EmailException e3) {
                String mensaje = "El email no tiene @";
                VentanaError.error(mensaje);
            } catch (
                   NumTelefonoException e4) {
                String mensaje = "Caracteres incorrectos en el numero de telefono";
                VentanaError.error(mensaje);
            }
        }
    }
}




