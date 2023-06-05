package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class CrearReservaView extends JFrame {

    private Hotel hotel;

    private JTextField numHuespedesField;
    private JTextField fechaInicialField;
    private JTextField fechaFinalField;
    private JTextField idHabitacionField;

    public CrearReservaView(Hotel hotel) {
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
        setTitle("Crear Reserva");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Creación de los componentes
        JLabel numHuespedesLabel = new JLabel("Número de Huéspedes:");
        numHuespedesField = new JTextField(10);

        JLabel fechaInicialLabel = new JLabel("Fecha de Inicio (yyyy-mm-dd):");
        fechaInicialField = new JTextField(10);

        JLabel fechaFinalLabel = new JLabel("Fecha de Finalización (yyyy-mm-dd):");
        fechaFinalField = new JTextField(10);

        JLabel idHabitacionLabel = new JLabel("ID de Habitación:");
        idHabitacionField = new JTextField(10);

        JButton crearReservaButton = new JButton("Crear Reserva");
        crearReservaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    try {
						crearReserva();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Error al parsear las fechas.");
                }
            }
        });

        // Configuración del diseño de la ventana
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2));
        mainPanel.add(numHuespedesLabel);
        mainPanel.add(numHuespedesField);
        mainPanel.add(fechaInicialLabel);
        mainPanel.add(fechaInicialField);
        mainPanel.add(fechaFinalLabel);
        mainPanel.add(fechaFinalField);
        mainPanel.add(idHabitacionLabel);
        mainPanel.add(idHabitacionField);
        mainPanel.add(crearReservaButton);

        setContentPane(mainPanel);
        pack();
    }

    private void crearReserva() throws ParseException, IOException {
        int numHuespedes = Integer.parseInt(numHuespedesField.getText());
        int noCama = 0;
        ArrayList<ArrayList<String>> infoHuespedes = new ArrayList<>();
        for (int i = 1; i <= numHuespedes; i++) {
            ArrayList<String> infoHuesped = new ArrayList<>();
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del huesped " + i);
            infoHuesped.add(nombre);
            String documento = JOptionPane.showInputDialog("Ingrese el numero de documento del huesped " + i);
            infoHuesped.add(documento);
            String email = JOptionPane.showInputDialog("Ingrese el email del huesped " + i);
            infoHuesped.add(email);
            String celular = JOptionPane.showInputDialog("Ingrese el numero de celular del huesped " + i);
            infoHuesped.add(celular);
            String necesitaCama = JOptionPane.showInputDialog("Ingrese si el huesped " + i + " necesita cama (true/false)");
            if (necesitaCama.equals("false")) {
                noCama++;
            }
            infoHuesped.add(necesitaCama);
            infoHuespedes.add(infoHuesped);
        }
        String fechaInicial = fechaInicialField.getText();
        String fechaFinal = fechaFinalField.getText();
        String idHabitacion = "";
        boolean continuar1 = true;
        boolean continuar2 = true;
        while (continuar1 || continuar2) {
            idHabitacion = JOptionPane.showInputDialog("Ingrese el id de la habitación de la reserva");
            ControladorHabitaciones controladorHa = new ControladorHabitaciones();
            controladorHa = this.hotel.getControladorHabitaciones();
            controladorHa.cargarArchivoHabitaciones(new File("Proyecto1Entrega3/Datos/Habitaciones.txt"), new File("Proyecto1Entrega3/Datos/Camas.txt"));
            Habitacion habitacion = controladorHa.getHabitacion(Integer.parseInt(idHabitacion));
            if (hotel.confirmarDisponibilidad(fechaInicial, fechaFinal, idHabitacion)) {
                continuar1 = false;
            } else {
                JOptionPane.showMessageDialog(null, "La habitación no está disponible en esa fecha.");
            }
            if ((numHuespedes - noCama) <= habitacion.getEspacio()) {
                continuar2 = false;
            } else {
                JOptionPane.showMessageDialog(null, "No hay suficiente espacio en la habitación para el número de huéspedes.");
            }
        }

        int id = this.hotel.crearReserva(infoHuespedes, fechaInicial, fechaFinal, idHabitacion);

        JOptionPane.showMessageDialog(null, "La reserva se creó exitosamente con el id " + id + ".");

        // Cerrar la ventana
        dispose();
    }
}
