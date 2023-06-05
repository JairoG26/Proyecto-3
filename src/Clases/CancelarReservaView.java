package Clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.*;

public class CancelarReservaView extends JFrame {
    private Hotel hotel;
    private ControladorReservas controlador;
    public CancelarReservaView(Hotel hotel) {
        this.hotel = hotel;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Cancelar Reserva");
        setSize(400, 200);
        setLocationRelativeTo(null);

        JLabel labelReservas = new JLabel("Reservas:");
        JTextArea textAreaReservas = new JTextArea();
        textAreaReservas.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textAreaReservas);

        JButton buttonCancelar = new JButton("Cancelar Reserva");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelReservas)
                        .addComponent(scrollPane)
                        .addComponent(buttonCancelar))
                .addContainerGap()
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelReservas)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCancelar)
                .addContainerGap()
        );

        // Cargar las reservas en el área de texto
        controlador = this.hotel.getControladorReservas();        
        ControladorServicios controladorS = new ControladorServicios();
        controladorS = this.hotel.getControladorServicios();
        try {
			controladorS.cargarServiciosYMenu(new File("Proyecto1Entrega3/Datos/Servicios.txt"),new File("Proyecto1Entrega3/Datos/MenuRestaurante.txt"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        ControladorHuespedes controladorHu = new ControladorHuespedes();
        controladorHu = this.hotel.getControladorHuespedes();
        try {
			controladorHu.cargarHuespedes();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        ControladorHabitaciones controladorHa = new ControladorHabitaciones();
        controladorHa = this.hotel.getControladorHabitaciones();
        try {
			controladorHa.cargarArchivoHabitaciones(new File("Proyecto1Entrega3/Datos/Habitaciones.txt"), new File("Proyecto1Entrega3/Datos/Camas.txt"));
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        try {
			controlador.cargarReservas(controladorHa, controladorHu, controladorS);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        textAreaReservas.setText(controlador.mostrarReservas());

        buttonCancelar.addActionListener(e -> {
            int idReserva = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la reserva a cancelar"));
            
            boolean cancelada = controlador.cancelarReserva(idReserva);
            if (cancelada) {
                JOptionPane.showMessageDialog(this, "La reserva se canceló con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "No se puede cancelar la reserva porque faltan menos de 48 horas para su inicio.");
            }
        });
    }
}
