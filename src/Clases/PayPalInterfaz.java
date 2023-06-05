package Clases;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class PayPalInterfaz extends JFrame {
	private Hotel hotel;
    private JLabel lblReserva;
    private JLabel lblNombre;
    private JLabel lblNumCuenta;
    private JLabel lblClave;
    private DefaultComboBoxModel<String> reservasComboBoxModel;
    private JComboBox<String> reservasComboBox;
    private JTextField txtID;
    private JTextField txtNombre;
    private JTextField txtNumCuenta;
    private JPasswordField txtClave;
    private JButton btnPagar;

    public PayPalInterfaz(Hotel hotel) {
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
    	
        setTitle("Realizar Pago");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));
        
        
        reservasComboBoxModel = new DefaultComboBoxModel<>();
        reservasComboBox = new JComboBox<>(reservasComboBoxModel);
        JScrollPane reservasScrollPane = new JScrollPane(reservasComboBox);
        lblReserva = new JLabel("info de reserva:");
        lblNombre = new JLabel("Nombre del Dueño:");
        lblNumCuenta = new JLabel("Número de Cuenta:");
        lblClave = new JLabel("Clave:");
        txtNombre = new JTextField();
        txtID = new JTextField();
        txtNumCuenta = new JTextField();
        txtClave = new JPasswordField();
        btnPagar = new JButton("Pagar");

        btnPagar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int idReserva = Integer.parseInt(txtID.getText());
                String nombre = txtNombre.getText();
                String numCuenta = txtNumCuenta.getText();
                String clave = new String(txtClave.getPassword());
                Reserva reserva = hotel.getControladorReservas().getReservaId(idReserva+1);
                double monto = hotel.GenerarFacturaReserva2(reserva);

                CobroPayPal cobro = new CobroPayPal();
                boolean pagoExitoso = cobro.realizarCobro(nombre, numCuenta, clave, monto);

                if (pagoExitoso) {
                    JOptionPane.showMessageDialog(null, "Pago exitoso");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo realizar el pago. Verifique los datos ingresados.");
                }
            }
        });

        
        add(lblReserva);
        add(txtID);
        add(lblNombre);
        add(txtNombre);
        add(lblNumCuenta);
        add(txtNumCuenta);
        add(lblClave);
        add(txtClave);
        add(reservasScrollPane);
        add(new JLabel()); // Espacio en blanco para alinear botón
        add(btnPagar);

        pack();
        
        cargarReservas();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void cargarReservas() {
        ArrayList<Reserva> reservas = hotel.getControladorReservas().getReservas();
        for (Reserva reserva : reservas) {
            String reservaStr = "Id: " + reserva.getIdReserva() + ", Fechas: " + reserva.getRangoFecha();
            reservasComboBoxModel.addElement(reservaStr);
        }
    }
}
