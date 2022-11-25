package Vista;


import javax.swing.JOptionPane;

public class VentanaError {
    public static void error (String infoMessage)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "Error en la carga de datos", JOptionPane.INFORMATION_MESSAGE);
    }
}