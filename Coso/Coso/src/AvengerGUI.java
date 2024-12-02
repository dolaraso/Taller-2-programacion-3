import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AvengerGUI {
    private JPanel pGeneral;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextArea textArea;
    private JButton agregarButton;
    private JButton modificarButton;
    private JButton listarButton;
    private ListaAvengers listaAvengers;

    public AvengerGUI() {
        listaAvengers = new ListaAvengers();



        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String id = textField1.getText();
                String nombre = textField2.getText();
                String mision = textField3.getText();
                int nivelPeligrosidad = Integer.parseInt(textField4.getText());
                double pagoMensual = Double.parseDouble(textField5.getText());

                Avenger avenger = new Avenger(id, nombre, mision, nivelPeligrosidad, pagoMensual);
                listaAvengers.agregarAvenger(avenger);
                JOptionPane.showMessageDialog(null, "Avenger agregado exitosamente.");
                limpiarCampos();
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField1.getText();
                String nombre = textField2.getText();
                String mision = textField3.getText();
                int nivelPeligrosidad = Integer.parseInt(textField4.getText());
                double pagoMensual = Double.parseDouble(textField5.getText());

                boolean modificado = listaAvengers.modificarAvenger(id, nombre, mision, nivelPeligrosidad, pagoMensual);
                if (modificado) {
                    JOptionPane.showMessageDialog(null, "Avenger modificado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el Avenger con ID: " + id);
                }
                limpiarCampos();
            }
        });

        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lista = listaAvengers.listarAvengers();
                textArea.setText(lista.isEmpty() ? "No hay Avengers registrados." : lista);
            }
        });
    }

    private void limpiarCampos() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Avengers");
        frame.setContentPane(new AvengerGUI().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}