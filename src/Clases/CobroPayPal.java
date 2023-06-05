package Clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CobroPayPal {

    private static final String ARCHIVO_INFORMACION = "Proyecto1Entrega3/Datos/paypal.txt";
    private static final String SEPARADOR = ";";

    private Map<String, CuentaBancaria> cuentas;

    public CobroPayPal() {
        cuentas = new HashMap<>();
        cargarInformacion();
    }

    private void cargarInformacion() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_INFORMACION))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);
                if (datos.length == 4) {
                    String nombre = datos[0];
                    String numCuenta = datos[1];
                    String clave = datos[2];
                    double saldo = Double.parseDouble(datos[3]);

                    CuentaBancaria cuenta = new CuentaBancaria(numCuenta, clave, saldo);
                    cuentas.put(nombre, cuenta);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean realizarCobro(String nombre, String numCuenta, String clave, double monto) {
        CuentaBancaria cuenta = cuentas.get(nombre);
        if (cuenta != null && cuenta.getNumCuenta().equals(numCuenta) && cuenta.getClave().equals(clave)) {
            if (cuenta.getSaldo() >= monto) {
                cuenta.descontarSaldo(monto);
                actualizarSaldoEnArchivo(nombre, cuenta.getSaldo());
                return true;
            }
        }
        return false;
    }
    
    private void actualizarSaldoEnArchivo(String nombre, double nuevoSaldo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_INFORMACION));
             BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_INFORMACION + ".tmp"))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);
                if (datos.length == 4 && datos[0].equals(nombre)) {
                    datos[3] = String.valueOf(nuevoSaldo);
                    linea = String.join(SEPARADOR, datos);
                }
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        File archivoOriginal = new File(ARCHIVO_INFORMACION);
        File archivoTemporal = new File(ARCHIVO_INFORMACION + ".tmp");
        archivoOriginal.delete();
        archivoTemporal.renameTo(archivoOriginal);
    }

    public static class CuentaBancaria {
        private String numCuenta;
        private String clave;
        private double saldo;

        public CuentaBancaria(String numCuenta, String clave, double saldo) {
            this.numCuenta = numCuenta;
            this.clave = clave;
            this.saldo = saldo;
        }

        public String getNumCuenta() {
            return numCuenta;
        }

        public String getClave() {
            return clave;
        }

        public double getSaldo() {
            return saldo;
        }

        public void descontarSaldo(double monto) {
            saldo -= monto;
        }
        
    }
}