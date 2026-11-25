package co.edu.uptcsoft.taller.model;

import co.edu.uptcsoft.taller.exception.ServiceException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrdenTrabajo {

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final String idOrden;
    private final LocalDateTime fechaHoraIngreso;
    private LocalDateTime fechaHoraEntrega;
    private EstadoOrden estado;
    private final Vehiculo vehiculo;
    private Tecnico tecnico;
    private final String observacionesIngreso;
    private String observacionesEntrega;
    private final List<TrabajoRealizado> listaTrabajos;

    public OrdenTrabajo(String idOrden, LocalDateTime fechaHoraIngreso, LocalDateTime fechaHoraEntrega,
                        EstadoOrden estado, Vehiculo vehiculo, Tecnico tecnico, String observacionesIngreso,
                        String observacionesEntrega) {

        if (idOrden == null || idOrden.isBlank()) {
            throw new IllegalArgumentException("El ID de la orden no puede ser nulo o vacío");
        }
        this.idOrden = idOrden.trim();
        this.fechaHoraIngreso = Objects.requireNonNull(fechaHoraIngreso, "La fecha y hora de ingreso es obligatoria");
        this.fechaHoraEntrega = fechaHoraEntrega;
        this.estado = Objects.requireNonNull(estado, "El estado es obligatorio");
        this.vehiculo = Objects.requireNonNull(vehiculo, "El vehículo es obligatorio");
        this.tecnico = Objects.requireNonNull(tecnico, "El técnico es obligatorio");
        this.observacionesIngreso = observacionesIngreso == null ? "" : observacionesIngreso.trim();
        this.observacionesEntrega = observacionesEntrega;
        this.listaTrabajos = new ArrayList<>();
    }

    public void agregarTrabajo(TrabajoRealizado trabajo) {
        if (trabajo == null) {
            throw new IllegalArgumentException("El trabajo no puede ser nulo");
        }
        if (this.estado == EstadoOrden.FINALIZADA) {
            throw new IllegalStateException("No se pueden agregar trabajos a una orden finalizada");
        }
        listaTrabajos.add(trabajo);
    }

    public void eliminarTrabajo(int idx){
        try {
            listaTrabajos.remove(idx);
        }catch (Exception e){
            throw new ServiceException("No se ha podido elimnar el trabajo");
        }
    }




    public double calcularCostoTotal() {
        return listaTrabajos.stream()
                .mapToDouble(TrabajoRealizado::getValor)
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
        this.estado = Objects.requireNonNull(estado, "El estado no puede ser nulo");
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

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public List<TrabajoRealizado> getListaTrabajos() {
        return new ArrayList<>(listaTrabajos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdenTrabajo that = (OrdenTrabajo) o;
        return idOrden.equalsIgnoreCase(that.idOrden);
    }

    @Override
    public int hashCode() {
        return idOrden.toUpperCase().hashCode();
    }

    @Override
    public String toString() {
        StringBuilder factura = new StringBuilder();

        factura.append("FACTURA ORDEN: ").append(idOrden).append("\n");
        factura.append("ESTADO: ").append(estado).append("\n");

        factura.append("Técnico encargado: ").append(tecnico.getNombre()).append("\n");
        factura.append("Cliente: ")
                .append(vehiculo.getCliente() != null ? vehiculo.getCliente().getNombre() : "N/A")
                .append("\n");
        factura.append("Vehículo: ").append(vehiculo.getPlaca()).append("\n\n");

        factura.append("Creado: ").append(fechaHoraIngreso.format(FORMATO_FECHA)).append("\n");
        factura.append("Terminado: ");
        if (estado != EstadoOrden.FINALIZADA || fechaHoraEntrega == null) {
            factura.append("La orden no está finalizada").append("\n");
        } else {
            factura.append(fechaHoraEntrega.format(FORMATO_FECHA)).append("\n");
        }

        factura.append("Observaciones ingreso: ").append(observacionesIngreso).append("\n");
        if (observacionesEntrega != null && !observacionesEntrega.isBlank()) {
            factura.append("Observaciones entrega: ").append(observacionesEntrega).append("\n");
        }

        factura.append("\nTRABAJOS:\n");
        if (listaTrabajos.isEmpty()) {
            factura.append("Aún no hay trabajos registrados en esta orden\n");
        } else {
            for (TrabajoRealizado trabajo : listaTrabajos) {
                factura.append("- ")
                        .append(trabajo.getDescripcion())
                        .append(" $")
                        .append(String.format("%.2f", trabajo.getValor()))
                        .append("\n");
            }
        }

        factura.append("\nTOTAL: $")
                .append(String.format("%.2f", calcularCostoTotal()))
                .append("\n");

        return factura.toString();
    }
}