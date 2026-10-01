package co.edu.uptcsoft.taller.model;

public class Motocicleta extends Vehiculo {
    private int cilindraje;

    public Motocicleta(String placa, String marca, int modelo, Cliente cliente, int cilindraje) {
        super(placa, marca, modelo, cliente);
        this.cilindraje = cilindraje;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    @Override
    public String toString() {
        return "Motocicleta{" +
                "placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo=" + modelo +
                ", cliente=" + cliente +
                ", cilindraje=" + cilindraje +
                '}';
    }
}