package Clases;

import java.util.ArrayList;

public class Habitacion {
    private int id;
    private String ubicacion;
    private boolean balcon;
    private boolean vista;
    private boolean cocinaIntegrada;
    private String tipoHabitacion;
    private int metrosCuadrados;
    private boolean aireAcondicionado;
    private boolean calefaccion;
    private boolean tv;
    private boolean cafetera;
    private boolean ropaCamaTapetesHipo;
    private boolean plancha;
    private boolean secadorPelo;
    private int voltajeAC;
    private boolean tomasUSBA;
    private boolean tomasUSBC;
    private boolean incluyeDesayuno;

    private ArrayList<Cama> camas;
    private ArrayList<Reserva> reservas;

    public Habitacion(int id, String ubicacion, boolean balcon, boolean vista, boolean cocinaIntegrada, String tipoHabitacion,
                      int metrosCuadrados, boolean aireAcondicionado, boolean calefaccion, boolean tv,
                      boolean cafetera, boolean ropaCamaTapetesHipo, boolean plancha, boolean secadorPelo,
                      int voltajeAC, boolean tomasUSBA, boolean tomasUSBC, boolean incluyeDesayuno) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.balcon = balcon;
        this.vista = vista;
        this.cocinaIntegrada = cocinaIntegrada;
        this.tipoHabitacion = tipoHabitacion;
        this.metrosCuadrados = metrosCuadrados;
        this.aireAcondicionado = aireAcondicionado;
        this.calefaccion = calefaccion;
        this.tv = tv;
        this.cafetera = cafetera;
        this.ropaCamaTapetesHipo = ropaCamaTapetesHipo;
        this.plancha = plancha;
        this.secadorPelo = secadorPelo;
        this.voltajeAC = voltajeAC;
        this.tomasUSBA = tomasUSBA;
        this.tomasUSBC = tomasUSBC;
        this.incluyeDesayuno = incluyeDesayuno;
        this.reservas = new ArrayList<Reserva>();
        this.camas = new ArrayList<Cama>();
    }

    public int getId() {
        return id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public boolean isBalcon() {
        return balcon;
    }

    public boolean isVista() {
        return vista;
    }

    public boolean isCocinaIntegrada() {
        return cocinaIntegrada;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public int getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public boolean isCalefaccion() {
        return calefaccion;
    }

    public boolean isTv() {
        return tv;
    }

    public boolean isCafetera() {
        return cafetera;
    }

    public boolean isRopaCamaTapetesHipo() {
        return ropaCamaTapetesHipo;
    }

    public boolean isPlancha() {
        return plancha;
    }

    public boolean isSecadorPelo() {
        return secadorPelo;
    }

    public int getVoltajeAC() {
        return voltajeAC;
    }

    public boolean isTomasUSBA() {
        return tomasUSBA;
    }

    public boolean isTomasUSBC() {
        return tomasUSBC;
    }

    public boolean isIncluyeDesayuno() {
        return incluyeDesayuno;
    }

    public ArrayList<Cama> getCamas() {
        return camas;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void addCama(Cama cama) {
        camas.add(cama);
    }

    public int getEspacio() {
        int espacio = 0;
        for (Cama cama : camas) {
            espacio += cama.getCantidadPersonas();
        }
        return espacio;
    }

    public String textoInventario() {
        String retorno = ubicacion + " " + "Id->" + id + ": Habitación tipo " + tipoHabitacion +
                " con capacidad para " + getEspacio() + " personas.\n";
        retorno += "Cocina Integrada: " + cocinaIntegrada + "\nVista: " + vista + "\nBalcon: " + balcon + "\n";
        retorno += "Tamaño en metros cuadrados: " + metrosCuadrados + "\nAire acondicionado: " + aireAcondicionado +
                "\nCalefacción: " + calefaccion + "\nTV: " + tv +
                "\nCafetera: " + cafetera + "\nRopa de cama y tapetes hipoalergénicos: " + ropaCamaTapetesHipo +
                "\nPlancha: " + plancha + "\nSecador de pelo: " + secadorPelo + "\nVoltaje AC: " + voltajeAC +
                "\nTomas USB-A: " + tomasUSBA + "\nTomas USB-C: " + tomasUSBC + "\nIncluye desayuno: " + incluyeDesayuno +
                "\nCamas:\n";
        for (int i = 0; i < camas.size(); i++) {
            retorno += "    Cama " + (i + 1) + ": " + camas.get(i).stringFactura() + "\n";
        }
        retorno += "Reservas:\n";
        for (int i = 0; i < reservas.size(); i++) {
            if (!reservas.get(i).isCancelado()) {
                retorno += (i) + ": " + reservas.get(i).stringInventario() + "\n";
            }
        }
        return retorno;
    }
}
