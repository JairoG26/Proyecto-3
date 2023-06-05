package Clases;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ClientView extends JFrame {
	private Hotel hotel = new Hotel();
	private JButton btnGenerarCheckOut;
	private JButton btnConsultarInventario;
    private JButton btnCrearReserva;
    private JButton btnCerrar;
    
    public ClientView() {
    	setTitle("Menú Principal - Empleado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 550);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        
        btnCrearReserva = new JButton("1. Crear reserva a nombre de uno o varios huéspedes");
        btnConsultarInventario = new JButton("2. Consultar inventario de habitaciones");
        btnGenerarCheckOut = new JButton("3. Realizar pago de reserva");
        btnCerrar = new JButton("0. Cerrar");
        
        btnCrearReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CrearReservaView reservaView = new CrearReservaView(hotel);
            	reservaView.setVisible(true);
            }
        });
        
        btnConsultarInventario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ConsultarInventarioView consultarInventarioView = new ConsultarInventarioView(hotel);
            	consultarInventarioView.setVisible(true);
            }
        });
        
        btnGenerarCheckOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	PaymentGUI payment = new PaymentGUI();
            	payment.setVisible(true);
            }
        });

        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        panel.add(btnCrearReserva);
        panel.add(btnConsultarInventario);
        panel.add(btnGenerarCheckOut);
        panel.add(btnCerrar);

        add(panel);
    }
    public void showEmployeeView() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setVisible(true);
            }
        });
    }
}
