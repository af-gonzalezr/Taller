package co.edu.uptcsoft.taller.model;

public class ServicioRealizado {
    private String descripcion;
    private double valor;

    public ServicioRealizado(String descripcion, double valor) {
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "ServicioRealizado{" +
                "descripcion='" + descripcion + '\'' +
                ", valor=" + valor +
                '}';
    }
}