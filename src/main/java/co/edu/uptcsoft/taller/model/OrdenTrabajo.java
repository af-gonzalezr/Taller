package co.edu.uptcsoft.taller.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrdenTrabajo{
    private final String idOrden;
    private final LocalDateTime fechaHoraIngreso;
    private LocalDateTime fechaHoraEntrega;
    private EstadoOrden estado;
    private final Vehiculo vehiculo;
    private final Tecnico tecnico;
    private final String observacionesIngreso;
    private String observacionesEntrega;
    private final List<ServicioRealizado> listaTrabajos;

    public OrdenTrabajo(String idOrden, LocalDateTime fechaHoraIngreso, LocalDateTime fechaHoraEntrega,
                        EstadoOrden estado, Vehiculo vehiculo, Tecnico tecnico, String observacionesIngreso,
                        String observacionesEntrega) {
        this.idOrden = idOrden;
        this.fechaHoraIngreso = fechaHoraIngreso;
        this.fechaHoraEntrega = fechaHoraEntrega;
        this.estado = estado;
        this.vehiculo = vehiculo;
        this.tecnico = tecnico;
        this.observacionesIngreso = observacionesIngreso;
        this.observacionesEntrega = observacionesEntrega;
        listaTrabajos = new ArrayList<>();
    }

    public void agregarTrabajo(ServicioRealizado trabajo) {
        listaTrabajos.add(trabajo);
    }

    public double calcularCostoTotal() {
        return listaTrabajos.stream()
                .mapToDouble(ServicioRealizado::getValor)
                .sum();
    }

    public String getIdOrden() {
        return idOrden;
    }

    public LocalDateTime getFechaHoraIngreso() {
        return fechaHoraIngreso;
    }

    public LocalDateTime getFechaHoraEntrega() {
        return fechaHoraEntrega;
    }

    public void setFechaHoraEntrega(LocalDateTime fechaHoraEntrega) {
        this.fechaHoraEntrega = fechaHoraEntrega;
    }

    public EstadoOrden getEstado() {
        return estado;
    }

    public void setEstado(EstadoOrden estado) {
        this.estado = estado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public String getObservacionesIngreso() {
        return observacionesIngreso;
    }

    public String getObservacionesEntrega() {
        return observacionesEntrega;
    }

    public void setObservacionesEntrega(String observacionesEntrega) {
        this.observacionesEntrega = observacionesEntrega;
    }

    public List<ServicioRealizado> getListaTrabajos() {
        return new ArrayList<>(listaTrabajos);
    }

    @Override
    public String toString() {
        StringBuilder factura = new StringBuilder();

        factura.append("FACTURA ORDEN: ").append(idOrden).append("\n");
        factura.append("ESTADO").append(estado).append("\n");
        factura.append("Cliente: ")
                .append(vehiculo.getCliente().getNombre())
                .append("\n");
        factura.append("Vehículo: ")
                .append(vehiculo.getPlaca())
                .append("\n\n");

        factura.append("TRABAJOS:\n");

        if(listaTrabajos.isEmpty()){
            factura.append("Aun no hay trabajos registrados en esta orden");
        }else {
            for (ServicioRealizado trabajo : listaTrabajos) {
                factura.append("- ")
                        .append(trabajo.getDescripcion())
                        .append(" $")
                        .append(trabajo.getValor())
                        .append("\n");
            }
        }


        factura.append("\nTOTAL: $")
                .append(calcularCostoTotal());

        return factura.toString();
    }

}