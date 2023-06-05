package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EmployeeView extends JFrame {
	private Hotel hotel = new Hotel();
    private JButton btnRegistrarConsumo;
    private JButton btnCrearHabitacion;
    private JButton btnCargarTarifa;
    private JButton btnEstablecerTarifaServicio;
    private JButton btnCrearProducto;
    private JButton btnCrearReserva;
    private JButton btnGenerarFactura;
    private JButton btnConsultarInventario;
    private JButton btnCancelarReserva;
    private JButton btnGenerarArchivoLog;
    private JButton btnGenerarCheckOut;
    private JButton btnCerrar;

    public EmployeeView() {
        setTitle("Menú Principal - Empleado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 550);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(12, 1));

        
        btnCrearHabitacion = new JButton("1. Crear habitación en el inventario");
        btnCargarTarifa = new JButton("2. Cargar tarifa para un tipo de habitación");
        btnEstablecerTarifaServicio = new JButton("3. Establecer o cambiar tarifa para un servicio");
        btnCrearProducto = new JButton("4. Crear producto de restaurante");
        btnRegistrarConsumo = new JButton("5. Registrar el consumo de una habitación");
        btnCrearReserva = new JButton("6. Crear reserva a nombre de uno o varios huéspedes");
        btnGenerarFactura = new JButton("7. Generar factura para una reserva");
        btnConsultarInventario = new JButton("8. Consultar inventario de habitaciones");
        btnCancelarReserva = new JButton("9. Cancelar Reserva");
        btnGenerarArchivoLog = new JButton("10. Generar archivo log de uno o más huéspedes");
        btnGenerarCheckOut = new JButton("11. Realizar check-out");
        btnCerrar = new JButton("0. Cerrar");
        
        
        btnCrearHabitacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CreateRoomView createRoomView = new CreateRoomView(hotel);
            	createRoomView.setVisible(true);
            }
        });

        btnCargarTarifa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CargarTarifaHabitacionView cargarTarifa = new CargarTarifaHabitacionView(hotel);
            	cargarTarifa.setVisible(true);
            }
        });

        btnEstablecerTarifaServicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CargarTarifaServicioView cargarTarifaservicio = new CargarTarifaServicioView(hotel);
            	cargarTarifaservicio.setVisible(true);
            }
        });

        btnCrearProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CrearProductoRestauranteView crearProductoView = new CrearProductoRestauranteView(hotel);
            	crearProductoView.setVisible(true);

            }
        });
        
        btnRegistrarConsumo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	RegistrarConsumoView registrarConsumoView = new RegistrarConsumoView(hotel);
            	registrarConsumoView.setVisible(true);
            }
        });
        
        btnCrearReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CrearReservaView reservaView = new CrearReservaView(hotel);
            	reservaView.setVisible(true);
            }
        });

        btnGenerarFactura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	GenerarFacturaView generarFacturaView = new GenerarFacturaView(hotel);
            	generarFacturaView.setVisible(true);
            }
        });

        btnConsultarInventario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ConsultarInventarioView consultarInventarioView = new ConsultarInventarioView(hotel);
            	consultarInventarioView.setVisible(true);
            }
        });

        btnCancelarReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CancelarReservaView cancelarReservaView = new CancelarReservaView(hotel);
            	cancelarReservaView.setVisible(true);
            }
        });

        btnGenerarArchivoLog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ArchivoLogView archivoLogView = new ArchivoLogView(hotel);
            	archivoLogView.setVisible(true);
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
        panel.add(btnCrearHabitacion);
        panel.add(btnCargarTarifa);
        panel.add(btnEstablecerTarifaServicio);
        panel.add(btnCrearProducto);
        panel.add(btnRegistrarConsumo);
        panel.add(btnCrearReserva);
        panel.add(btnGenerarFactura);
        panel.add(btnConsultarInventario);
        panel.add(btnCancelarReserva);
        panel.add(btnGenerarArchivoLog);
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
