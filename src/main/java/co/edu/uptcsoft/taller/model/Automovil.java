package co.edu.uptcsoft.taller.model;

public class Automovil extends Vehiculo {
    private int numeroPuertas;

    public Automovil(String placa, String marca, int modelo, Cliente cliente, int numeroPuertas) {
        super(placa, marca, modelo, cliente);
        this.numeroPuertas = numeroPuertas;
    }

    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }


    @Override
    public String toString() {
        return "Automovil{" +
                "numeroPuertas=" + numeroPuertas +
                ", placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo=" + modelo +
                ", cliente=" + cliente +
                '}';
    }
}