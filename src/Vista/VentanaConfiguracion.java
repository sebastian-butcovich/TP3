package Vista;

import Controlador.conexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class VentanaConfiguracion extends JFrame {
    private JLabel label1,label2;
    private JTextField text1,text2;
    private JButton boton;
    private JPanel panel;
    public VentanaConfiguracion()
    {
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        acomodarComponentes();
        this.setVisible(true);
    }
    public void acomodarComponentes()
    {
        //Inicializacion
        label1 = new JLabel("User");
        label2 = new JLabel("Password");
        text1 = new JTextField(20);
        text2 = new JTextField(20);
        boton = new JButton("Conectar");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(text1.getText());
                 Connection g=conexion.GetConexion("mundial_futbol_2022",text1.getText(),text2.getText());
                 if(g!=null)
                 {
                     JOptionPane.showMessageDialog(null,"Conexion con exito");
                     new MundialFutbolQatar2022();
                     cambiarVisibilidad();

                 }else {
                     JOptionPane.showMessageDialog(null,"Ingrese los datos nuevamente");
                 }
            }
        });
        panel = new JPanel();
        GridBagConstraints g = new GridBagConstraints();

        // Acomodar los componentes
        panel.setLayout(new GridBagLayout());
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.gridheight = 1;
        g.insets = new Insets(10,10,10,10);
        panel.add(label1,g);
        g.gridx = 2;
        g.gridy = 0;
        g.gridwidth = 1;
        g.gridheight = 1;
        panel.add(text1,g);
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 1;
        g.gridheight = 1;
        panel.add(label2,g);
        g.gridx = 2;
        g.gridy = 1;
        g.gridheight = 1;
        g.gridwidth = 1;
        panel.add(text2,g);
        g.gridx = 2;
        g.gridy = 2;
        g.gridwidth = 1;
        g.gridheight = 1;
        panel.add(boton,g);

        this.add(panel);
    }
   public void cambiarVisibilidad()
   {
       this.dispose();
   }

    public static void main(String[]args)
    {
        new VentanaConfiguracion();
    }
}
