package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class RegistrarConsumoView extends JFrame {

    private Hotel hotel;
    
    private DefaultComboBoxModel<String> reservasComboBoxModel;
    private JComboBox<String> reservasComboBox;
    private JTextField idReservaField;
    private JTextField tipoServicioField;
    private JTextField idServicioField;
    private JTextField pagadoField;

    public RegistrarConsumoView(Hotel hotel) {
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
        setTitle("Registrar Consumo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Creación de los componentes
        reservasComboBoxModel = new DefaultComboBoxModel<>();
        reservasComboBox = new JComboBox<>(reservasComboBoxModel);
        JScrollPane reservasScrollPane = new JScrollPane(reservasComboBox);
        
        JLabel idReservaLabel = new JLabel("Ingrese el ID de la reserva:");
        idReservaField = new JTextField(10);

        JLabel tipoServicioLabel = new JLabel("Ingrese si el consumo es de un servicio (0) o de un producto del restaurante (1):");
        tipoServicioField = new JTextField(10);

        JLabel idServicioLabel = new JLabel("Ingrese el número ID del servicio:");
        idServicioField = new JTextField(10);

        JLabel pagadoLabel = new JLabel("Ingrese si el pedido ya ha sido pagado (true/false):");
        pagadoField = new JTextField(10);

        JButton registrarConsumoButton = new JButton("Registrar Consumo");
        registrarConsumoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    registrarConsumo();
                } catch (IOException | ParseException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Configuración del diseño de la ventana
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2));
        mainPanel.add(idReservaLabel);
        mainPanel.add(idReservaField);
        mainPanel.add(tipoServicioLabel);
        mainPanel.add(tipoServicioField);
        mainPanel.add(idServicioLabel);
        mainPanel.add(idServicioField);
        mainPanel.add(pagadoLabel);
        mainPanel.add(pagadoField);
        mainPanel.add(registrarConsumoButton);
        mainPanel.add(reservasScrollPane);
        mainPanel.add(registrarConsumoButton);

        setContentPane(mainPanel);
        pack();
        
        cargarReservas();
    }
    
    private void cargarReservas() {
        ArrayList<Reserva> reservas = hotel.getControladorReservas().getReservas();
        for (Reserva reserva : reservas) {
            String reservaStr = "Id: " + reserva.getIdReserva() + ", Fechas: " + reserva.getRangoFecha();
            reservasComboBoxModel.addElement(reservaStr);
        }
    }

    private void registrarConsumo() throws IOException, ParseException {
        int idReserva = Integer.parseInt(idReservaField.getText());
        int tipoServicio = Integer.parseInt(tipoServicioField.getText());
        int idServicio = Integer.parseInt(idServicioField.getText());
        boolean pagado = Boolean.parseBoolean(pagadoField.getText());

        

        Reserva reserva = hotel.getControladorReservas().getReservaId(idReserva+1);

        if (tipoServicio == 0) {
            Servicio servicio = hotel.getControladorServicios().getServicioId(idServicio-1);
            servicio.setPagado(pagado);
            reserva.getServiciosConsumidos().add(servicio);
        } else if (tipoServicio == 1) {
            ProductoRestaurante producto = hotel.getControladorServicios().getMenuId(idServicio-1);
            producto.setPagado(pagado);
            reserva.getProductoMenuConsumido().add(producto);
        }

        hotel.cargarServicioConsumido(reserva);

        JOptionPane.showMessageDialog(null, "El consumo se ha registrado exitosamente.");

        // Cerrar la ventana
        dispose();
    }
}