package co.edu.uptcsoft.taller.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrdenTrabajo implements Comparable<OrdenTrabajo> {
    private String idOrden;
    private LocalDateTime fechaHoraIngreso;
    private LocalDateTime fechaHoraEntrega;
    private EstadoOrden estado;
    private Vehiculo vehiculo;
    private Tecnico tecnico;
    private String observacionesIngreso;
    private String observacionesEntrega;
    private List<ServicioRealizado> listaTrabajos;

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
        listaTrabajos =new ArrayList<>();
    }

    public void agregarTrabajo(ServicioRealizado trabajo) {
        listaTrabajos.add(trabajo);
    }

    public double calcularCostoTotal() {
        return listaTrabajos.stream()
                .mapToDouble(ServicioRealizado::getValor)
                .sum();
    }

    @Override
    public int compareTo(OrdenTrabajo otraOrden) {
        return fechaHoraIngreso.compareTo(otraOrden.fechaHoraIngreso);
    }

    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public LocalDateTime getFechaHoraIngreso() {
        return fechaHoraIngreso;
    }

    public void setFechaHoraIngreso(LocalDateTime fechaHoraIngreso) {
        this.fechaHoraIngreso = fechaHoraIngreso;
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

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public String getObservacionesIngreso() {
        return observacionesIngreso;
    }

    public void setObservacionesIngreso(String observacionesIngreso) {
        this.observacionesIngreso = observacionesIngreso;
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

    public void setListaTrabajos(List<ServicioRealizado> listaTrabajos) {
        this.listaTrabajos = listaTrabajos == null ? new ArrayList<>() : new ArrayList<>(listaTrabajos);
    }
}