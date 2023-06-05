package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CargarTarifaServicioView extends JFrame {

    private Hotel hotel;

    private JComboBox<String> decisionBox;
    private JTextField idField;
    private JTextField nuevaTarifaField;
    private JButton cargarTarifaButton;

    public CargarTarifaServicioView(Hotel hotel) {
        this.hotel = hotel;

        try {
			hotel.cargarArchivoHabitaciones();
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        // Configuración de la ventana
        setTitle("Cargar Tarifa de Servicio");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Creación de los componentes
        JLabel decisionLabel = new JLabel("Seleccione el tipo de tarifa:");
        decisionBox = new JComboBox<>(new String[]{"Producto Menú", "Servicio"});;

        JLabel idLabel = new JLabel("Seleccione el servicio o producto:");
        idField = new JTextField(10);

        JLabel nuevaTarifaLabel = new JLabel("Ingrese el nuevo valor:");
        nuevaTarifaField = new JTextField(10);

        cargarTarifaButton = new JButton("Cargar Tarifa");
        cargarTarifaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					cargarTarifaServicio();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

        // Configuración del diseño de la ventana
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2));
        mainPanel.add(decisionLabel);
        mainPanel.add(decisionBox);
        mainPanel.add(idLabel);
        mainPanel.add(idField);
        mainPanel.add(nuevaTarifaLabel);
        mainPanel.add(nuevaTarifaField);
        mainPanel.add(cargarTarifaButton);

        setContentPane(mainPanel);
        pack();
    }

    private void cargarTarifaServicio() throws IOException {
        String decision = (String) decisionBox.getSelectedItem();
        ControladorServicios controlador = new ControladorServicios();
    	controlador = this.hotel.getControladorServicios();
    	controlador.cargarServiciosYMenu(new File("Proyecto1Entrega3/Datos/Servicios.txt"),new File("Proyecto1Entrega3/Datos/MenuRestaurante.txt"));
        if (decision.equals("Servicio")) {
            int id = Integer.parseInt(idField.getText());
            Servicio servicio = controlador.getServicioId(id-2);
            double nuevaTarifa = Double.parseDouble(nuevaTarifaField.getText());
            servicio.setPrecio(nuevaTarifa);
            hotel.getControladorServicios().cambiarPrecio("Servicios", servicio.getNombreServicio(), nuevaTarifa);
        } else {
            int id = Integer.parseInt(idField.getText());
            ProductoRestaurante producto = controlador.getMenuId(id-2);
            double nuevaTarifa = Double.parseDouble(nuevaTarifaField.getText());
            producto.setPrecio(nuevaTarifa);
            hotel.getControladorServicios().cambiarPrecio("MenuRestaurante", producto.getNombreServicio(), nuevaTarifa);
        }

        JOptionPane.showMessageDialog(null, "La tarifa se cargó exitosamente.");

        // Cerrar la ventana
        dispose();
    }
}
