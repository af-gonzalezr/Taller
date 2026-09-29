package co.edu.uptcsoft.taller.model;

public abstract class Vehiculo {
    private String placa;
    private String marca;
    private int modelo;
    private Cliente cliente;

    protected Vehiculo(String placa, String marca, int modelo, Cliente cliente) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.cliente = cliente;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @Override
public String toString() {
    return getClass().getSimpleName() + "{" +
            "placa='" + placa + '\'' +
            ", marca='" + marca + '\'' +
            ", modelo=" + modelo +
            ", idCliente='" + cliente.getIdCliente() + '\'' +
            '}';
}
}