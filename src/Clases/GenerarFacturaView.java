package Clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.*;

public class GenerarFacturaView extends JFrame {
    private Hotel hotel;
    public GenerarFacturaView(Hotel hotel) {
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
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Generar Factura");
        setSize(400, 200);
        setLocationRelativeTo(null);

        JLabel labelReservas = new JLabel("Reservas:");
        JTextArea textAreaReservas = new JTextArea();
        textAreaReservas.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textAreaReservas);

        JButton buttonGenerarFactura = new JButton("Generar Factura");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelReservas)
                        .addComponent(scrollPane)
                        .addComponent(buttonGenerarFactura))
                .addContainerGap()
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelReservas)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonGenerarFactura)
                .addContainerGap()
        );

        // Cargar las reservas en el área de texto
        
        textAreaReservas.setText(hotel.getControladorReservas().mostrarReservas());

        buttonGenerarFactura.addActionListener(e -> {
            int indexReserva = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el índice de la reserva deseada"));
            
            hotel.GenerarFacturaReserva(hotel.getControladorReservas().getReservaId(indexReserva + 1));
            JOptionPane.showMessageDialog(this, "Factura generada exitosamente.");
        });
    }
}

