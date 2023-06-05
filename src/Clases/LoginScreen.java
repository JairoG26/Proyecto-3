package Clases;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginScreen {
    private static final Font titleFont = new Font("Arial", Font.BOLD, 28);
    private static final Font labelFont = new Font("Arial", Font.BOLD, 14);
    private static final Font textFieldFont = new Font("Arial", Font.PLAIN, 14);
    private static final Font buttonFont = new Font("Arial", Font.BOLD, 14);
    private static final Color bgColor = new Color(240, 240, 240);

    public static void showLoginScreen() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.getContentPane().setBackground(bgColor);
        frame.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(bgColor);
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        frame.add(titlePanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(bgColor);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(labelFont);
        formPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setFont(textFieldFont);
        formPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        formPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(textFieldFont);
        formPanel.add(passwordField);

        frame.add(formPanel, BorderLayout.CENTER);
        
        JButton SigninButton = new JButton("Sign in");
        SigninButton.setFont(buttonFont);
        SigninButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		CrearCuentaView crearCuenta = new CrearCuentaView();
        		crearCuenta.setVisible(true);
        	}
        });

        JButton loginButton = new JButton("Login");
        loginButton.setFont(buttonFont);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Hotel hotel = new Hotel();
            	try {
					hotel.cargarUsuarios();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                Usuario user = hotel.getUsuario(username, password);
        
                if (user.getRol().equals("Empleado")) {
                    EmployeeView employeeView = new EmployeeView();
                    employeeView.showEmployeeView();
                    frame.dispose();
                } else if (user.getRol().equals("Cliente")) {
                    ClientView clientView = new ClientView();
                    clientView.showEmployeeView();
                    frame.dispose();
                } 
                 else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(bgColor);
        buttonPanel.add(loginButton);
        buttonPanel.add(SigninButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
