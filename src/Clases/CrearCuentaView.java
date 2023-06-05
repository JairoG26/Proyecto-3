package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CrearCuentaView extends JFrame {
    private JTextField textFieldNombre;
    private JTextField textFieldUsuario;
    private JPasswordField passwordField;
    private JButton btnCrearCuenta;
    private static final String RUTA_ARCHIVO = "Proyecto1Entrega3/Datos/Usuarios.txt";
    private static final String SEPARADOR = ";";

    public CrearCuentaView() {
        initialize();
    }

    private void initialize() {
        setTitle("Crear Cuenta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(3, 2, 10, 10));
        contentPane.add(panelFormulario, BorderLayout.CENTER);

        JLabel lblNombre = new JLabel("Nombre:");
        textFieldNombre = new JTextField();
        JLabel lblUsuario = new JLabel("Usuario:");
        textFieldUsuario = new JTextField();
        JLabel lblContraseña = new JLabel("Contraseña:");
        passwordField = new JPasswordField();

        panelFormulario.add(lblNombre);
        panelFormulario.add(textFieldNombre);
        panelFormulario.add(lblUsuario);
        panelFormulario.add(textFieldUsuario);
        panelFormulario.add(lblContraseña);
        panelFormulario.add(passwordField);

        btnCrearCuenta = new JButton("Crear Cuenta");
        btnCrearCuenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = textFieldNombre.getText();
                String usuario = textFieldUsuario.getText();
                String contraseña = new String(passwordField.getPassword());
                String rol = "Cliente";


                // Aquí puedes realizar la lógica de creación de cuenta con los datos ingresados

                // Por ejemplo, mostrar un mensaje con los datos ingresados
                JOptionPane.showMessageDialog(null, "Cuenta creada con éxito:\n" +
                        "Nombre: " + nombre + "\n" +
                        "Usuario: " + usuario + "\n" +
                        "Contraseña: " + contraseña);

                // Limpiar los campos de texto después de crear la cuenta
                textFieldNombre.setText("");
                textFieldUsuario.setText("");
                passwordField.setText("");
                
                escribirInformacionCuenta(nombre, usuario, contraseña, rol);
            }
        });

        contentPane.add(btnCrearCuenta, BorderLayout.SOUTH);
    }
    
    private void escribirInformacionCuenta(String nombre, String usuario, String contraseña, String rol) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
            String linea = nombre + SEPARADOR + usuario + SEPARADOR + contraseña + SEPARADOR + rol;
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}