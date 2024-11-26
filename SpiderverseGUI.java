import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpiderverseGUI {
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JComboBox<Integer> comboBox3;
    private JTable table1;
    private JButton button1;
    private JTextField textField3;
    private JButton buscarButton;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JComboBox<String> comboBox4;
    private JButton filtrarButton;
    private JTable table2;
    private JButton conteoButton;
    private JTextArea textArea1;

    private Spiderverse heroesList = new Spiderverse();

    public SpiderverseGUI() {
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[]{"Sentido Arácnido", "Trepa Muros", "Fuerza Sobrehumana", "Agilidad Mejorada", "Tejido de Telaraña"}));
        comboBox2.setModel(new DefaultComboBoxModel<>(new String[]{"Tierra-616", "Tierra-1610", "Tierra-12041", "Tierra-90214", "Tierra-138"}));
        comboBox3.setModel(new DefaultComboBoxModel<>(new Integer[]{1, 2, 3, 4, 5}));
        comboBox4.setModel(new DefaultComboBoxModel<>(new String[]{"Sentido Arácnido", "Trepa Muros", "Fuerza Sobrehumana", "Agilidad Mejorada", "Tejido de Telaraña"}));


        DefaultTableModel tableModel1 = new DefaultTableModel(new String[]{"Código", "Nombre", "Poder Especial", "Universo", "Nivel de Experiencia"}, 0);
        table1.setModel(tableModel1);

        DefaultTableModel tableModel2 = new DefaultTableModel(new String[]{"Código", "Nombre", "Poder Especial", "Universo", "Nivel de Experiencia"}, 0);
        table2.setModel(tableModel2);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(textField1.getText());
                String nombre = textField2.getText();
                String poderEspecial = (String) comboBox1.getSelectedItem();
                String universo = (String) comboBox2.getSelectedItem();
                int nivelExperiencia = (int) comboBox3.getSelectedItem();

                if (heroesList.buscarPorCodigo(codigo) == null) {
                    heroesList.agregarHeroe(new SpiderverseHero(codigo, nombre, poderEspecial, universo, nivelExperiencia));
                    actualizarTabla(tableModel1);
                } else {
                    JOptionPane.showMessageDialog(null, "El código ya existe. No se puede registrar.");
                }
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(textField3.getText());
                SpiderverseHero heroe = heroesList.buscarPorCodigo(codigo);

                if (heroe != null) {
                    textField4.setText(String.valueOf(heroe.getCodigo()));
                    textField5.setText(heroe.getNombre());
                    textField6.setText(heroe.getPoderEspecial());
                    textField7.setText(heroe.getUniverso());
                    textField8.setText(String.valueOf(heroe.getNivelExperiencia()));
                } else {
                    JOptionPane.showMessageDialog(null, "Héroe no encontrado.");
                }
            }
        });

        filtrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String poderSeleccionado = (String) comboBox4.getSelectedItem();


                if (poderSeleccionado != null && !poderSeleccionado.isEmpty()) {

                    Spiderverse filtrados = heroesList.filtrarPorPoder(poderSeleccionado);
                    filtrados.ordenarPorNivel();


                    actualizarTabla(filtrados, tableModel2);
                } else {

                    JOptionPane.showMessageDialog(null, "Por favor, selecciona un poder para filtrar.");
                }
            }
        });




        conteoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
                String[] universos = {"Tierra-616", "Tierra-1610", "Tierra-12041", "Tierra-90214", "Tierra-138"};
                for (String universo : universos) {
                    int conteo = heroesList.contarPorUniverso(universo);
                    textArea1.append(universo + ": " + conteo + " héroes\n");
                }
            }
        });
    }

    private void actualizarTabla(DefaultTableModel model) {
        model.setRowCount(0);
        for (SpiderverseHero heroe : heroesList.obtenerTodos()) {
            model.addRow(new Object[]{heroe.getCodigo(), heroe.getNombre(), heroe.getPoderEspecial(), heroe.getUniverso(), heroe.getNivelExperiencia()});
        }
    }

    private void actualizarTabla(Spiderverse lista, DefaultTableModel model) {
        model.setRowCount(0);
        for (SpiderverseHero heroe : lista.obtenerTodos()) {
            model.addRow(new Object[]{
                    heroe.getCodigo(),
                    heroe.getNombre(),
                    heroe.getPoderEspecial(),
                    heroe.getUniverso(),
                    heroe.getNivelExperiencia()
            });
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Spiderverse");
        frame.setContentPane(new SpiderverseGUI().tabbedPane1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

