package Clases;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit.Parser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class CreateRoomView extends JFrame {
    private Hotel hotel;

    private JTextField ubicacionField;
    private JCheckBox balconCheckBox;
    private JCheckBox vistaCheckBox;
    private JCheckBox cocinaIntegradaCheckBox;
    private JComboBox<String> tipoHabitacionComboBox;
    private JButton crearHabitacionButton;

    // Nuevos componentes
    private JTextField metrosCuadradosField;
    private JCheckBox aireAcondicionadoCheckBox;
    private JCheckBox calefaccionCheckBox;
    private JCheckBox tvCheckBox;
    private JCheckBox cafeteraCheckBox;
    private JCheckBox camaHipoCheckBox;
    private JCheckBox planchaCheckBox;
    private JCheckBox secadorPeloCheckBox;
    private JTextField voltajeACField;
    private JCheckBox usbaCheckBox;
    private JCheckBox usbcCheckBox;
    private JCheckBox desayunoCheckBox;


    public CreateRoomView(Hotel hotel) {
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
        setTitle("Crear Habitación");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(14, 2, 10, 10));
        add(mainPanel, BorderLayout.CENTER);

        // Componentes de la interfaz
        mainPanel.add(new JLabel("Ubicación:"));
        ubicacionField = new JTextField();
        mainPanel.add(ubicacionField);

        mainPanel.add(new JLabel("Balcón:"));
        balconCheckBox = new JCheckBox();
        mainPanel.add(balconCheckBox);

        mainPanel.add(new JLabel("Vista:"));
        vistaCheckBox = new JCheckBox();
        mainPanel.add(vistaCheckBox);

        mainPanel.add(new JLabel("Cocina Integrada:"));
        cocinaIntegradaCheckBox = new JCheckBox();
        mainPanel.add(cocinaIntegradaCheckBox);

        mainPanel.add(new JLabel("Tipo de Habitación:"));
        tipoHabitacionComboBox = new JComboBox<>(new String[]{"Estándar", "Doble", "Básica", "Básica Doble", "Suite", "Suite Doble"});
        mainPanel.add(tipoHabitacionComboBox);

        // Nuevos componentes
        mainPanel.add(new JLabel("Metros Cuadrados:"));
        metrosCuadradosField = new JTextField();
        mainPanel.add(metrosCuadradosField);

        mainPanel.add(new JLabel("Aire Acondicionado:"));
        aireAcondicionadoCheckBox = new JCheckBox();
        mainPanel.add(aireAcondicionadoCheckBox);

        mainPanel.add(new JLabel("Calefacción:"));
        calefaccionCheckBox = new JCheckBox();
        mainPanel.add(calefaccionCheckBox);

        mainPanel.add(new JLabel("TV:"));
        tvCheckBox = new JCheckBox();
        mainPanel.add(tvCheckBox);

        mainPanel.add(new JLabel("Cafetera:"));
        cafeteraCheckBox = new JCheckBox();
        mainPanel.add(cafeteraCheckBox);

        mainPanel.add(new JLabel("Cama Hipoalergénica:"));
        camaHipoCheckBox = new JCheckBox();
        mainPanel.add(camaHipoCheckBox);

        mainPanel.add(new JLabel("Plancha:"));
        planchaCheckBox = new JCheckBox();
        mainPanel.add(planchaCheckBox);

        mainPanel.add(new JLabel("Secador de Pelo:"));
        secadorPeloCheckBox = new JCheckBox();
        mainPanel.add(secadorPeloCheckBox);

        mainPanel.add(new JLabel("Voltaje AC:"));
        voltajeACField = new JTextField();
        mainPanel.add(voltajeACField);

        mainPanel.add(new JLabel("Puertos USB-A:"));
        usbaCheckBox = new JCheckBox();
        mainPanel.add(usbaCheckBox);

        mainPanel.add(new JLabel("Puertos USB-C:"));
        usbcCheckBox = new JCheckBox();
        mainPanel.add(usbcCheckBox);

        mainPanel.add(new JLabel("Desayuno:"));
        desayunoCheckBox = new JCheckBox();
        mainPanel.add(desayunoCheckBox);

        // Botón para crear la habitación
        crearHabitacionButton = new JButton("Crear Habitación");
        crearHabitacionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearHabitacion();
            }
        });
        add(crearHabitacionButton, BorderLayout.SOUTH);
    }

    private void crearHabitacion() {
        String ubicacion = ubicacionField.getText();
        boolean balcon = balconCheckBox.isSelected();
        boolean vista = vistaCheckBox.isSelected();
        boolean cocinaIntegrada = cocinaIntegradaCheckBox.isSelected();
        String tipoHabitacion = (String) tipoHabitacionComboBox.getSelectedItem();

        // Nuevos datos de habitación
        Integer metrosCuadrados = Integer.parseInt(metrosCuadradosField.getText());
        boolean aireAcondicionado = aireAcondicionadoCheckBox.isSelected();
        boolean calefaccion = calefaccionCheckBox.isSelected();
        boolean tv = tvCheckBox.isSelected();
        boolean cafetera = cafeteraCheckBox.isSelected();
        boolean camaHipo = camaHipoCheckBox.isSelected();
        boolean plancha = planchaCheckBox.isSelected();
        boolean secadorPelo = secadorPeloCheckBox.isSelected();
        Integer voltajeAC = Integer.parseInt(voltajeACField.getText());
        boolean usba = usbaCheckBox.isSelected();
        boolean usbc = usbcCheckBox.isSelected();
        boolean desayuno = desayunoCheckBox.isSelected();

        ArrayList<ArrayList<String>> infoCamas = new ArrayList<ArrayList<String>>();
        
        ArrayList<String> datosCama = new ArrayList<String>();
        if (tipoHabitacion == "Suite Doble")
        {
            Integer numCamas = 2;
            for (int i = 0; i < numCamas; i++) {
            	datosCama.add("king");
            	datosCama.add("2");
            	datosCama.add("false");
            	
            }
            infoCamas.add(datosCama);
        }
        else if (tipoHabitacion == "Suite")
        {
        	Integer numCamas = 1;
        	for (int i = 0; i < numCamas; i++) {
        		datosCama.add("king");
        		datosCama.add("2");
        		datosCama.add("false");
        		
            }
        	infoCamas.add(datosCama);
        }
        else if (tipoHabitacion == "Estándar")
        {
        	Integer numCamas = 1;
        	for (int i = 0; i < numCamas; i++) {
        		datosCama.add("doble");
        		datosCama.add("2");
        		datosCama.add("false");
        		
            }
        	infoCamas.add(datosCama);
        }
        else if (tipoHabitacion == "Doble")
        {
        	Integer numCamas = 2;
        	for (int i = 0; i < numCamas; i++) {
        		datosCama.add("doble");
        		datosCama.add("2");
        		datosCama.add("false");
        		infoCamas.add(datosCama);
            }
        	infoCamas.add(datosCama);
        }
        else if (tipoHabitacion == "Básica Doble")
        {
        	Integer numCamas = 2;
        	for (int i = 0; i < numCamas; i++) {
        		datosCama.add("individual");
        		datosCama.add("1");
        		datosCama.add("false");
        		
            }
        	infoCamas.add(datosCama);
        }
        else if (tipoHabitacion == "Básica")
        {
        	Integer numCamas = 1;
        	for (int i = 0; i < numCamas; i++) {
        		datosCama.add("individual");
        		datosCama.add("1");
        		datosCama.add("false");
        		
            }
        	infoCamas.add(datosCama);
        }

        this.hotel.crearHabitacion(ubicacion, balcon, vista, cocinaIntegrada, tipoHabitacion, infoCamas,
                metrosCuadrados, aireAcondicionado, calefaccion, tv, cafetera, camaHipo, plancha, secadorPelo,
                voltajeAC, usba, usbc, desayuno);
        JOptionPane.showMessageDialog(null, "La habitación se creó exitosamente.");

        // Cerrar la ventana
        dispose();
    }
}