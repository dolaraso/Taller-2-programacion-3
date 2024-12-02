import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ThunderboltGUI extends JFrame {
    private ListaThunderbolt listaThunderbolt;
    private JTextField txtCodigo, txtNombre;
    private JComboBox<String> comboHabilidad, comboNivel, comboMision;
    private JTable tableThunderbolts;
    private DefaultTableModel model;

    public ThunderboltGUI() {
        listaThunderbolt = new ListaThunderbolt();
        setTitle("Gestión de Thunderbolts");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        crearPanelRegistro(tabbedPane);
        crearPanelBusqueda(tabbedPane);
        crearPanelFiltrado(tabbedPane);
        crearPanelConteo(tabbedPane);

        add(tabbedPane);
        // Inicializar el modelo de la tabla
        model = new DefaultTableModel();
        tableThunderbolts.setModel(model);
    }

    private void crearPanelRegistro(JTabbedPane tabbedPane) {
        JPanel panelRegistro = new JPanel();
        panelRegistro.setLayout(new GridLayout(6, 2));

        panelRegistro.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        panelRegistro.add(txtCodigo);

        panelRegistro.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelRegistro.add(txtNombre);

        panelRegistro.add(new JLabel("Habilidad Principal:"));
        comboHabilidad = new JComboBox<>(new String[]{"Combate Cuerpo a Cuerpo", "Tiro Preciso", "Tecnología Avanzada", "Sigilo", "Supervelocidad"});
        panelRegistro.add(comboHabilidad);

        panelRegistro.add(new JLabel("Nivel de Redención:"));
        comboNivel = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
        panelRegistro.add(comboNivel);

        panelRegistro.add(new JLabel("Misión Asignada:"));
        comboMision = new JComboBox<>(new String[]{"Misión A", "Misión B", "Misión C"});
        panelRegistro.add(comboMision);

        JButton btnAgregar = new JButton("Agregar Thunderbolt");
        panelRegistro.add(btnAgregar);
        panelRegistro.add(new JLabel("")); // Espacio vacío

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(txtCodigo.getText());
                String nombre = txtNombre.getText();
                String habilidad = (String) comboHabilidad.getSelectedItem();
                int nivelRedencion = Integer.parseInt((String) comboNivel.getSelectedItem());
                String mision = (String) comboMision.getSelectedItem();

                Thunderbolt nuevo = new Thunderbolt(codigo, nombre, habilidad, nivelRedencion, mision);
                if (listaThunderbolt.agregarThunderbolt(nuevo)) {
                    JOptionPane.showMessageDialog(ThunderboltGUI.this, "Thunderbolt agregado exitosamente.");
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(ThunderboltGUI.this, "El código ya existe.");
                }
            }
        });

        tabbedPane.addTab("Registro de Thunderbolts", panelRegistro);
    }

    private void crearPanelBusqueda(JTabbedPane tabbedPane) {
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new GridLayout(2, 2));

        panelBusqueda.add(new JLabel("Código a buscar:"));
        JTextField txtCodigoBuscar = new JTextField();
        panelBusqueda.add(txtCodigoBuscar);

        JButton btnBuscar = new JButton("Buscar Thunderbolt");
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(new JLabel("")); // Espacio vacío

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(txtCodigoBuscar.getText());
                Thunderbolt t = listaThunderbolt.buscarThunderbolt(codigo);
                if (t != null) {
                    txtCodigo.setText(String.valueOf(t.getCodigo()));
                    txtNombre.setText(t.getNombre());
                    comboHabilidad.setSelectedItem(t.getHabilidadPrincipal());
                    comboNivel.setSelectedItem(String.valueOf(t.getNivelRedencion()));
                    comboMision.setSelectedItem(t.getMisionAsignada());
                } else {
                    JOptionPane.showMessageDialog(ThunderboltGUI.this, "Thunderbolt no encontrado.");
                }
            }
        });

        tabbedPane.addTab("Buscar Thunderbolt", panelBusqueda);
    }

    private void crearPanelFiltrado(JTabbedPane tabbedPane) {
        JPanel panelFiltrado = new JPanel();
        panelFiltrado.setLayout(new BorderLayout());

        JTextArea textAreaFiltrado = new JTextArea();
        panelFiltrado.add(new JScrollPane(textAreaFiltrado), BorderLayout.CENTER);

        JComboBox<String> comboHabilidadFiltrado = new JComboBox<>(new String[]{"Combate Cuerpo a Cuerpo", "Tiro Preciso", "Tecnología Avanzada", "Sigilo", "Supervelocidad"});
        panelFiltrado.add(comboHabilidadFiltrado, BorderLayout.NORTH);

        JButton btnFiltrar = new JButton("Filtrar Thunderbolts");
        panelFiltrado.add(btnFiltrar, BorderLayout.SOUTH);

        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String habilidad = (String) comboHabilidadFiltrado.getSelectedItem();
                ArrayList<Thunderbolt> filtrados = listaThunderbolt.filtrarPorHabilidad(habilidad);
                StringBuilder sb = new StringBuilder();
                for (Thunderbolt t : filtrados) {
                    sb.append("Código: ").append(t.getCodigo())
                            .append(", Nombre: ").append(t.getNombre())
                            .append(", Habilidad: ").append(t.getHabilidadPrincipal())
                            .append(", Nivel: ").append(t.getNivelRedencion())
                            .append(", Misión: ").append(t.getMisionAsignada())
                            .append("\n");
                }
                textAreaFiltrado.setText(sb.toString());
            }
        });

        tabbedPane.addTab("Filtrar Thunderbolts", panelFiltrado);
    }

    private void crearPanelConteo(JTabbedPane tabbedPane) {
        JPanel panelConteo = new JPanel();
        panelConteo.setLayout(new BorderLayout());

        JTextArea textAreaConteo = new JTextArea();
        panelConteo.add(new JScrollPane(textAreaConteo), BorderLayout.CENTER);

        JComboBox<String> comboHabilidadConteo = new JComboBox<>(new String[]{"Combate Cuerpo a Cuerpo", "Tiro Preciso", "Tecnología Avanzada", "Sigilo", "Supervelocidad"});
        panelConteo.add(comboHabilidadConteo, BorderLayout.NORTH);

        JButton btnContar = new JButton("Contar Misiones");
        panelConteo.add(btnContar, BorderLayout.SOUTH);

        btnContar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String habilidad = (String) comboHabilidadConteo.getSelectedItem();
                int totalMisiones = listaThunderbolt.contarMisionesPorHabilidad(habilidad, 0);
                textAreaConteo.setText("Total de misiones para " + habilidad + ": " + totalMisiones);
            }
        });

        tabbedPane.addTab("Conteo de Misiones", panelConteo);
    }

    private void actualizarTabla() {
        model.setRowCount(0);
        for (Thunderbolt t : listaThunderbolt.getThunderbolts()) {
            model.addRow(new Object[]{t.getCodigo(), t.getNombre(), t.getHabilidadPrincipal(), t.getNivelRedencion(), t.getMisionAsignada()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ThunderboltGUI gui = new ThunderboltGUI();
            gui.setVisible(true);
        });
    }
}