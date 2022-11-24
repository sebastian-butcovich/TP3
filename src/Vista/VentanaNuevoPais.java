package Vista;
import Modelo.Pais;
import PatronDAO.PaisDAO;
import PatronDAO.PaisJDBC;

import  javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaNuevoPais extends JFrame{
    private JLabel labelNombre,labelIdioma;
    private JTextField textNombre,textIdioma;
    private JButton guardar;
    private JPanel panel,panel1,panel2,panelBoton,fila1,fila2;
    public VentanaNuevoPais()
    {
        this.setTitle("Nuevo Pais");
        this.setSize(800,500);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        inicializarComponentes();
        this.setVisible(true);
    }
    public void inicializarComponentes()
    {
        labelIdioma = new JLabel("Idioma");
        labelIdioma.setFont(new Font("",Font.ITALIC,25));
        labelNombre = new JLabel("Nombre");
        labelNombre.setFont(new Font("",Font.ITALIC,25));
        textIdioma = new JTextField(20);
        textNombre = new JTextField(20);
        textIdioma.setFont(new Font("",Font.PLAIN,20));
        textNombre.setFont(new Font("",Font.PLAIN,20));
        guardar = new JButton("guardar");
        panel = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panelBoton = new JPanel();
        fila1 = new JPanel();
        fila2 = new JPanel();
        acomodarPaneles();
        guardar.addActionListener(new funcionBoton());
        this.add(panel);

    }
    public void acomodarPaneles()
    {
        GridBagConstraints g = new GridBagConstraints();
        g.weighty = .7;
        g.weightx = .7;
        g.fill=GridBagConstraints.BOTH;
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.BLACK);
        acomodarPanelFila1();
        panel.add(new JPanel(),g);
        g.gridy = 1;
        panel.add(panel1,g);
        g.gridy = 2;
        panel.add(new JPanel(),g);
        g.gridy = 3;
        g.weighty = 1.0;
        g.weightx = 1.0;
        panel.add(panel2,g);

    }
    public void acomodarPanelFila1()
    {
        GridBagConstraints g = new GridBagConstraints();
      panel1.setLayout(new GridBagLayout());
      g.fill = GridBagConstraints.BOTH;
      g.gridx = 0;
      g.gridy = 0;
      g.gridheight = 2;
      g.weightx = 0.5;
      g.weighty = 0.5;
      panel1.add(new JPanel(),g);
      g.gridheight = 1;
      g.weightx = 1.0;
      g.weighty = 1.0;
      g.gridx = 1;
      panel1.add(fila1,g);
      g.gridy = 1;
      panel1.add(fila2,g);
      g.gridx=2;
      g.gridy=0;
      g.gridheight =2;
      g.weightx = .4;
      g.weighty = .4;
      panel1.add(new JPanel(),g);
      acomodarFilas();
    }
    public void acomodarFilas()
    {
        GridBagConstraints g = new GridBagConstraints();
        fila1.setLayout(new GridBagLayout());
        //fila1.setBackground(Color.BLUE);
        g.weightx = 1.0;
        g.weighty = 1.0;
        g.fill = GridBagConstraints.BOTH;
        g.gridx = 0;
        g.gridy = 0;
        g.gridheight =2;
        fila1.add(new JPanel(),g);
        g.gridx = 1;
        g.gridheight = 1;
        g.gridwidth = 2;
        fila1.add(new JPanel(),g);
        g.gridwidth = 1;
        g.gridy = 1;
        fila1.add(labelNombre,g);
        g.gridx = 2;
        fila1.add(textNombre,g);
        g.gridy = 2;
        g.gridx = 1;
        g.gridwidth = 2;
        g.gridwidth = 1;
        g.gridx = 3;
        fila1.add(new JPanel(),g);

       /*--------------------------------------------------------------*/
        fila2.setLayout(new GridBagLayout());
        //fila1.setBackground(Color.BLUE);
        g.weightx = 1.0;
        g.weighty = 1.0;
        g.fill = GridBagConstraints.BOTH;
        g.gridx = 0;
        g.gridy = 0;
        g.gridheight =2;
        fila2.add(new JPanel(),g);
        g.gridx = 1;
        g.gridheight = 1;
        g.gridwidth = 2;
        fila2.add(new JPanel(),g);
        g.gridwidth = 1;
        g.gridy = 1;
        fila2.add(labelIdioma,g);
        g.gridx = 2;
        fila2.add(textIdioma,g);
        g.gridy = 2;
        g.gridx = 1;
        g.gridwidth = 2;
        fila2.add(new JPanel(),g);
        g.gridwidth = 1;
        g.gridx = 3;
        fila2.add(new JPanel(),g);
        acomodarBoton();
    }
    public void acomodarBoton()
    {
        GridBagConstraints g = new GridBagConstraints();
        panel2.setLayout(new GridBagLayout());
        g.weighty = 1.0;
        g.weightx = 1.0;
        g.fill = GridBagConstraints.BOTH;
        g.gridx =0;
        g.gridy = 0;
        g.gridheight = 2;
        panel2.add(new JPanel(),g);
        g.gridx =3;
        panel2.add(new JPanel(),g);
        g.gridx = 2;
        g.gridheight = 1;
        panel2.add(new JPanel(),g);
        g.gridy = 1;
        g.weighty = .5;
        g.weightx = .5;
        panel2.add(guardar,g);
        g.gridx = 1;
        panel2.add(new JPanel(),g);
        g.weighty = 1.0;
        g.weightx = 1.0;
        g.gridy = 2;
        g.gridx = 2;
        panel2.add(new JPanel(),g);


    }


   private  class funcionBoton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            Pais p = new Pais();
            PaisDAO pd = new PaisJDBC();
            p.setNombre(textNombre.getText());
            p.setIdioma(textIdioma.getText());
            pd.agregarPais(p);
            dispose();
        }

    }
}