package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearProductoRestauranteView extends JFrame {

    private Hotel hotel;

    private JTextField nombreField;
    private JTextField tipoProductoField;
    private JTextField rangoHorasField;
    private JTextField precioField;

    public CrearProductoRestauranteView(Hotel hotel) {
        this.hotel = hotel;

        // Configuración de la ventana
        setTitle("Crear Producto de Restaurante");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Creación de los componentes
        JLabel nombreLabel = new JLabel("Nombre del Producto:");
        nombreField = new JTextField(10);

        JLabel tipoProductoLabel = new JLabel("Tipo de Producto (comida/bebida):");
        tipoProductoField = new JTextField(10);

        JLabel rangoHorasLabel = new JLabel("Rango de Horas (HH:mm-HH:mm):");
        rangoHorasField = new JTextField(10);

        JLabel precioLabel = new JLabel("Precio:");
        precioField = new JTextField(10);

        JButton crearProductoButton = new JButton("Crear Producto");
        crearProductoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearProductoRestaurante();
            }
        });

        // Configuración del diseño de la ventana
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2));
        mainPanel.add(nombreLabel);
        mainPanel.add(nombreField);
        mainPanel.add(tipoProductoLabel);
        mainPanel.add(tipoProductoField);
        mainPanel.add(rangoHorasLabel);
        mainPanel.add(rangoHorasField);
        mainPanel.add(precioLabel);
        mainPanel.add(precioField);
        mainPanel.add(crearProductoButton);

        setContentPane(mainPanel);
        pack();
    }

    private void crearProductoRestaurante() {
        String nombre = nombreField.getText();
        String tipoProducto = tipoProductoField.getText();
        String rangoHoras = rangoHorasField.getText();
        double precio = Double.parseDouble(precioField.getText());

        hotel.getControladorServicios().crearProductoRestaurante(nombre, tipoProducto, rangoHoras, precio);
        JOptionPane.showMessageDialog(null, "El producto se creó exitosamente.");

        // Cerrar la ventana
        dispose();
    }
}
