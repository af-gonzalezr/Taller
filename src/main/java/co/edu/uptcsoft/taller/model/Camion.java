package co.edu.uptcsoft.taller.model;

public class Camion extends Vehiculo {
    private double capacidadCargaTon;

    public Camion(String placa, String marca, int modelo, Cliente cliente, double capacidadCargaTon) {
        super(placa, marca, modelo, cliente);
        this.capacidadCargaTon = capacidadCargaTon;
    }

    public double getCapacidadCargaTon() {
        return capacidadCargaTon;
    }

    public void setCapacidadCargaTon(double capacidadCargaTon) {
        this.capacidadCargaTon = capacidadCargaTon;
    }
}