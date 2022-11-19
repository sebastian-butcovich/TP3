package Vista;
import Controlador.consultas;
import modelo.Futbolista;

import  javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaNuevoFutbolista extends JFrame{
   private JLabel nombre, apellido, email, telefono, pais,documento;
   private JTextField textNombre, textApellido, textEmail, textTelefono, textPais,textDocumento;
   private JButton guardar;
   private JPanel panel,panel1,panel2,panel3,panel4,panel5,panel6,panel7,panelIzq, panelDer;
   private static Futbolista f;
   private static VentanaNuevoFutbolista nf;
   private boolean estado = false;
   private VentanaNuevoFutbolista()
   {

        super("Nuevo Futbolista");
        this.setSize(800,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
        acomodarComponentes();
        this.setVisible(true);

   }
   public static VentanaNuevoFutbolista getNuevoFutbolista()
   {
       if(nf == null)
       {
          return  new VentanaNuevoFutbolista();
       }
       else
       {
           return nf;
       }
   }

   public void acomodarComponentes()
   {
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
       nombre.setFont(new Font("Arial",Font.ITALIC,20));
       apellido = new JLabel("Apellido");
       apellido.setFont(new Font("Arial",Font.PLAIN,20));
       email = new JLabel("E-mail");
       email.setFont(new Font("Arial",Font.PLAIN,20));
       pais = new JLabel("Pais");
       pais.setFont(new Font("Arial",Font.PLAIN,20));
       telefono = new JLabel("Telefono");
       telefono.setFont(new Font("Arial",Font.PLAIN,20));
       documento = new JLabel("Documento");
       documento.setFont(new Font("Arial",Font.PLAIN,20));
       guardar = new JButton("Guardar");
       /*----------------------------------------------------------------*/
       textNombre = new JTextField(20);
       textNombre.setFont(new Font("Letra",Font.PLAIN,20));
       textApellido = new JTextField(20);
       textApellido.setFont(new Font("Letra",Font.PLAIN,20));
       textEmail = new JTextField(20);
       textEmail.setFont(new Font("Letra",Font.PLAIN,20));
       textPais = new JTextField(20);
       textPais.setFont(new Font("Letra",Font.PLAIN,20));
       textTelefono = new JTextField(20);
       textTelefono.setFont(new Font("Letra",Font.PLAIN,20));
       textDocumento = new JTextField(20);
       textDocumento.setFont(new Font("Letra",Font.PLAIN,20));
       /*----------------------------------------------------------------*/
       //Funccion para que el boton guarde los datos
       guardar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                f = new Futbolista();
               f.setNombre(textNombre.getText());
               f.setApellido(textApellido.getText());
               f.setDocIdentidad(textDocumento.getText());
               f.setEmail(textEmail.getText());
               f.setTelefono(textTelefono.getText());
               String s = textPais.getText();
               f.setPais(Integer.parseInt(s));
               estado = true;
               consultas.modificarFutbolista(ventanaFutbolista.ID(),f);
           }
       });

       /*----------------------------------------------------------------*/
       //Interiores de los paneles
       // Panel 6
       g.weightx = 1.0;
       g.weighty = 1.0;
       g.fill = GridBagConstraints.BOTH;
       g.gridx = 0;
       g.gridy = 0;
       g.gridheight = 2;
       panel6.add(new JPanel(),g);
       g.gridheight = 1;
       g.gridx = 1;
       g.gridy = 1;
       panel6.add(guardar,g);
       g.gridy = 0;
       g.gridx = 2;
       panel6.add(new JPanel(),g);
       g.gridx = 0;
       g.gridy = 2;
       g.weighty = 0.5;
       g.gridwidth = 3;
       panel6.add(new JPanel(),g);
       g.gridwidth = 1;
       g.weighty = 1;
       //panel 1
       g.gridx = 0;
       g.gridy = 0;
       g.gridheight = 1;
       panel1.add(new JPanel(),g);
       g.gridx = 1;
       g.gridy = 1;
       panel1.add(nombre,g);
       g.gridx = 2;
       panel1.add(textNombre,g);
       g.gridx = 3;
       g.gridy = 0;
       panel1.add(new JPanel(),g);
       g.gridx = 0;
       g.gridy = 2;
       g.gridwidth = 3;
       panel1.add(new JPanel(),g);
       g.gridwidth = 1;
       //Panel 2
       g.weightx = 1.0;
       g.weighty = 1.0;
       g.gridx = 0;
       g.gridy = 0;
       g.gridheight = 1;
       g.gridwidth = 1;
       panel2.add(new JPanel(),g);
       g.weightx = 1.0;
       g.gridx = 1;
       g.gridy = 1;
       panel2.add(apellido,g);
       g.gridx = 2;
       panel2.add(textApellido,g);
       g.gridx = 3;
       g.gridy = 0;
       g.weightx = 1.0;
       panel2.add(new JPanel(),g);
       g.weightx = 1.0;
       g.gridx = 0;
       g.gridy = 2;
       g.gridwidth = 3;
       panel2.add(new JPanel(),g);
       g.gridwidth = 1;
       //Panel 3
       g.weightx = 1.0;
       g.weighty = 1.0;
       g.gridx = 0;
       g.gridy = 0;
       panel3.add(new JPanel(),g);
       g.gridx = 1;
       g.gridy = 1;
       panel3.add(email,g);
       g.gridx = 2;
       panel3.add(textEmail,g);
       g.gridx = 3;
       g.gridy = 0;
       panel3.add(new JPanel(),g);
       g.gridx = 0;
       g.gridy = 2;
       panel3.add(new JPanel(),g);
       /*----------------------------------------------------------------*/
       // Panel 4
       g.gridx = 0;
       g.gridy = 0;
       panel4.add(new JPanel(),g);
       g.gridy = 1;
       g.gridx = 1;
       panel4.add(telefono,g);
       g.gridx =2;
       panel4.add(textTelefono,g);
       g.gridx = 3;
       g.gridy = 0;
       panel4.add(new JPanel(),g);
       g.gridx = 0;
       g.gridy = 2;
       panel4.add(new JPanel(),g);

       //Panel 5
       g.gridx = 0;
       g.gridy = 0;
       panel5.add(new JPanel(),g);
       g.gridwidth = 1;
       g.gridx = 1;
       g.gridy = 1;
       panel5.add(pais,g);
       g.gridx = 2;
       panel5.add(textPais,g);
       g.gridx =3;
       panel5.add(new JPanel(),g);
       g.gridy = 2;
       panel5.add(new JPanel(),g);
       //Panel 7
       g.gridx = 0;
       g.gridy = 0;
       g.gridwidth = 1;
       g.gridheight = 1;
       panel7.add(new JPanel(),g);
       g.gridx = 3;
       panel7.add(new JPanel(),g);
       g.gridx = 1;
       g.gridy = 1;
       panel7.add(documento,g);
       g.gridx = 2;
       panel7.add(textDocumento,g);
       g.gridy = 2;
       panel7.add(new JPanel(),g);
       /*----------------------------------------------------------------*/

        //Paneles
        g.insets = new Insets(5,5,5,5);
        g.weightx = .5;
        g.weighty = .5;
        g.fill = GridBagConstraints.BOTH;
        g.gridy = 0;
        g.gridx = 0;
        g.gridheight = 7;
        panel.add(panelIzq,g);
        g.gridx = 2;
        g.gridy =0;
        panel.add(panelDer,g);
       g.weightx = 1.0;
       g.weighty = 1.0;
        g.gridheight = 1;
        g.gridx = 1;
        g.gridy = 0;
        panel.add(panel1,g);
        g.gridy = 1;
        panel.add(panel2,g);
        g.gridy = 2;
        panel.add(panel3,g);
        g.gridy = 3;
        panel.add(panel7,g);
        g.gridy = 4;
        panel.add(panel4,g);
        g.gridy = 5;
        panel.add(panel5,g);
        g.gridy = 6;
        panel.add(panel6,g);
    this.add(panel);
   }
    public static  Futbolista getFutbolista()
    {
        return f;
    }

   public static void main(String [] args){
       new VentanaNuevoFutbolista();

   }

}
