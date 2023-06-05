package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class CargarTarifaHabitacionView extends JFrame {

    private Hotel hotel;

    private JComboBox<String> tipoHabitacionComboBox;
    private JTextField valorTarifaField;
    private JTextField fechaInicialField;
    private JTextField fechaFinalField;
    private JTextField diasField;

    public CargarTarifaHabitacionView(Hotel hotel) {
        this.hotel = hotel;

        // Configuración de la ventana
        setTitle("Cargar Tarifa de Habitación");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Creación de los componentes
        JLabel tipoHabitacionLabel = new JLabel("Tipo de Habitación:");
        tipoHabitacionComboBox = new JComboBox<>(new String[]{"Estándar", "Doble", "Básica", "Básica Doble", "Suite", "Suite Doble"});
        

        JLabel valorTarifaLabel = new JLabel("Valor de la Tarifa:");
        valorTarifaField = new JTextField(10);

        JLabel fechaInicialLabel = new JLabel("Fecha Inicial (yyyy-mm-dd):");
        fechaInicialField = new JTextField(10);

        JLabel fechaFinalLabel = new JLabel("Fecha Final (yyyy-mm-dd):");
        fechaFinalField = new JTextField(10);

        JLabel diasLabel = new JLabel("Días de Aplicación (L:M:X:J:V:S:D):");
        diasField = new JTextField(10);

        JButton cargarTarifaButton = new JButton("Cargar Tarifa");
        cargarTarifaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    cargarTarifaHabitacion();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Error al parsear las fechas.");
                }
            }
        });

        // Configuración del diseño de la ventana
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2));
        mainPanel.add(tipoHabitacionLabel);
        mainPanel.add(tipoHabitacionComboBox);
        mainPanel.add(valorTarifaLabel);
        mainPanel.add(valorTarifaField);
        mainPanel.add(fechaInicialLabel);
        mainPanel.add(fechaInicialField);
        mainPanel.add(fechaFinalLabel);
        mainPanel.add(fechaFinalField);
        mainPanel.add(diasLabel);
        mainPanel.add(diasField);
        mainPanel.add(cargarTarifaButton);

        setContentPane(mainPanel);
        pack();
    }

    private void cargarTarifaHabitacion() throws ParseException {
    	String tipoHabitacion = (String) tipoHabitacionComboBox.getSelectedItem();
        double valorTarifa = Double.parseDouble(valorTarifaField.getText());
        String fechaInicial = fechaInicialField.getText();
        String fechaFinal = fechaFinalField.getText();
        String dias = diasField.getText();

        hotel.cargarTarifaServicio(tipoHabitacion, valorTarifa, fechaInicial, fechaFinal, dias);
        JOptionPane.showMessageDialog(null, "La tarifa se cargó exitosamente.");

        // Cerrar la ventana
        dispose();
    }
}
