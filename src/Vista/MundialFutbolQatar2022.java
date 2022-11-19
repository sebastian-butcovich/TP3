package Vista;
import  javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MundialFutbolQatar2022 extends JFrame {
        private JButton boton1,boton2,boton3,boton4,boton5,boton6,boton7,boton8;
        private JPanel panel,panel1,panel2,panel3,panel4;

        public MundialFutbolQatar2022()
        {
            super("Mundial de Futbol - Qatar 2022");
            this.setSize(1000,500);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);
            inicializarComponentes();
            acomodarPaneles();
            //this.pack();
            this.setVisible(true);
        }
        public void inicializarComponentes()
        {
            panel = new JPanel(new GridBagLayout());
            panel1 = new JPanel(new GridBagLayout());
            panel2 = new JPanel(new GridBagLayout());
            panel3 = new JPanel(new GridBagLayout());
            panel4 = new JPanel(new GridBagLayout());
            boton1 = new JButton("Boton Futbolista");
            boton2 = new JButton("Boton Pais");
            boton3 = new JButton("Sin Definir");
            boton4 = new JButton("Sin Definir");
            boton5 = new JButton("Sin Definir");
            boton6 = new JButton("Sin Definir");
            boton7 = new JButton("Logo 1");
            boton8 = new JButton("Logo 2");
            boton1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ventanaFutbolista();
                }
            });
        }
        public void acomodarPaneles()
        {
            GridBagConstraints g = new GridBagConstraints();
            g.gridx = 1;
            g.gridy = 0;
            g.gridwidth = 1;
            g.gridheight = 1;
            g.weightx = 1.0;
            g.weighty = 1.0;
            g.fill = GridBagConstraints.BOTH;
            panel3.add(panel1,g);
            g.gridx = 0;
            g.gridy = 0;
            g.gridwidth = 1;
            g.gridheight = 1;
            g.weightx = 0.8;
            g.weighty = 0.3;
            g.fill = GridBagConstraints.BOTH;
            panel.add(panel4,g);
            acomodarBotonesSuperiores();
            g.gridx = 1;
            g.gridy = 0;
            g.gridwidth = 1;
            g.gridheight = 1;
            g.weightx = 0.5;
            g.weighty = 0.3;
            g.fill = GridBagConstraints.BOTH;
            panel.add(panel3,g);
            g.gridx = 0;
            g.gridy = 2;
            g.gridwidth =2;
            g.gridheight = 1;
            g.weightx = 1.0;
            g.weighty = 1.0;
            acomodarBotonesCentrales();
            panel.add(panel2,g);
            this.add(panel);
        }
    
        public void acomodarBotonesSuperiores()
        {
            GridBagConstraints g2 = new GridBagConstraints();
            g2.gridx = 0;
            g2.gridy = 0;
            g2.gridwidth = 1;
            g2.gridheight = 1;
            g2.weightx = 0.5;
            g2.weighty = 0.5;
            g2.insets = new Insets(10,10,10,10);
            g2.fill = GridBagConstraints.BOTH;
            panel1.add(boton7,g2);
            g2.gridx = 1;
            g2.gridy = 0;
            g2.gridheight = 1;
            g2.gridwidth = 1;
            g2.weightx = 0.5;
            g2.weighty = 0.5;
            panel1.add(boton8,g2);
        }
        public void acomodarBotonesCentrales()
        {
            GridBagConstraints g3 = new GridBagConstraints();
            g3.gridx = 0;
            g3.gridy =0;
            g3.gridwidth = 1;
            g3.gridheight = 1;
            g3.weightx = 1.0;
            g3.weighty = 1.0;
            g3.fill = GridBagConstraints.BOTH;
            g3.insets = new Insets(50,50,50,50);
            panel2.add(boton1,g3);
            g3.gridx = 1;
            g3.gridy =0;
            g3.gridwidth = 1;
            g3.gridheight = 1;
            panel2.add(boton2,g3);
            g3.gridx = 2;
            g3.gridy =0;
            g3.gridwidth = 1;
            g3.gridheight = 1;
            panel2.add(boton3,g3);
            g3.gridx = 0;
            g3.gridy = 1;
            g3.gridwidth = 1;
            g3.gridheight = 1;
            panel2.add(boton4,g3);
            g3.gridx = 1;
            g3.gridy =1;
            g3.gridwidth = 1;
            g3.gridheight = 1;
            panel2.add(boton5,g3);
            g3.gridx = 2;
            g3.gridy =1;
            g3.gridwidth = 1;
            g3.gridheight = 1;
            panel2.add(boton6,g3);
        }
        public static void main(String[]args)
        {
            new MundialFutbolQatar2022();
        }
}
