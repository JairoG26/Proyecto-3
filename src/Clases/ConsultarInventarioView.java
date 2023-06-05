package Clases;

import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class ConsultarInventarioView extends JFrame {
    private Hotel hotel;
    private ControladorHabitaciones controladorHa;
    public ConsultarInventarioView(Hotel hotel) {
        this.hotel = hotel;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Consultar Inventario");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JTextArea textAreaInventario = new JTextArea();
        textAreaInventario.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textAreaInventario);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane)
                .addContainerGap()
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane)
                .addContainerGap()
        );

        // Consultar el inventario y mostrarlo en el área de texto
        this.controladorHa = new ControladorHabitaciones();
        try {
			controladorHa.cargarArchivoHabitaciones(new File("Proyecto1Entrega3/Datos/Habitaciones.txt"), new File("Proyecto1Entrega3/Datos/Camas.txt"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
        
        textAreaInventario.setText(this.controladorHa.consultarInventario());
    }
}