package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentGUI extends JFrame {
	
	private Hotel hotel = new Hotel();

    private JComboBox<String> gatewayComboBox;

    public PaymentGUI() {
        setTitle("Selección de Pasarela de Pago");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setResizable(false);
        setLocationRelativeTo(null);

        // Crear una lista de opciones de pasarelas de pago
        gatewayComboBox = new JComboBox<>();
        gatewayComboBox.addItem("PayU");
        gatewayComboBox.addItem("PayPal");

        // Crear el botón de selección de pasarela
        JButton selectButton = new JButton("Seleccionar");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGatewaySelection((String) gatewayComboBox.getSelectedItem());
            }
        });

        // Configurar el diseño de la ventana
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.add(gatewayComboBox);
        panel.add(selectButton);

        add(panel);
    }

    private void handleGatewaySelection(String selectedGateway) {
        if (selectedGateway == "PayU") {
        	
        	PayUInterfaz payUInterfaz = new PayUInterfaz(hotel);
        	payUInterfaz.setVisible(true);
        	
        }
        else if (selectedGateway == "PayPal") {
        	
        	PayPalInterfaz paypalInterfaz = new PayPalInterfaz(hotel);
        	paypalInterfaz.setVisible(true);
        	
        }
        
        
        
        // Por ejemplo, puedes cerrar la ventana de selección
        dispose();
    }
}
