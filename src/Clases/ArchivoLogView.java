package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ArchivoLogView extends JFrame {
    private Hotel hotel;
    private ControladorHuespedes controlador;
    private JTextField textFieldNumHuespedes;
    private JButton btnGenerarArchivo;

    public ArchivoLogView(Hotel hotel) {
        this.hotel = hotel;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Archivo Log");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JLabel labelNumHuespedes = new JLabel("Número de Huéspedes:");
        textFieldNumHuespedes = new JTextField(10);

        btnGenerarArchivo = new JButton("Generar Archivo");
        btnGenerarArchivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                generarArchivoLog();
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(labelNumHuespedes)
                        .addComponent(textFieldNumHuespedes)
                        .addComponent(btnGenerarArchivo)
                )
                .addContainerGap()
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNumHuespedes)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldNumHuespedes)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGenerarArchivo)
                .addContainerGap()
        );
    }

    private void generarArchivoLog() {
        int numHuespedes = Integer.parseInt(textFieldNumHuespedes.getText());
        ArrayList<ArrayList<String>> infoHuespedes = new ArrayList<ArrayList<String>>();

        for (int i = 1; i <= numHuespedes; i++) {
            ArrayList<String> infoHuesped = new ArrayList<String>();

            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del huésped " + i);
            String documento = JOptionPane.showInputDialog("Ingrese el documento del huésped " + i);

            infoHuesped.add(nombre);
            infoHuesped.add(documento);

            infoHuespedes.add(infoHuesped);
        }
        try {
			hotel.cargarArchivoHabitaciones();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int id = hotel.archivoLog(infoHuespedes);
        JOptionPane.showMessageDialog(null, "Se generó exitosamente el archivo log con id " + id + ".");
    }


}
