package co.edu.uptcsoft.taller.controller;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.model.TrabajoRealizado;
import java.util.List;

public interface OrdenController {
    OrdenTrabajo crear(String idOrden, String fechaHoraIngreso, String idVehiculo, String idTecnico, String observacionesIngreso);
    TrabajoRealizado agregarTrabajo(String idOrden, String descripcion, String valor);
    OrdenTrabajo quitarTrabajo(String idOrden, String indiceTrabajo);
    OrdenTrabajo reasignarTecnico(String idOrden, String idNuevoTecnico);
    OrdenTrabajo finalizar(String idOrden, String observacionesEntrega);
    OrdenTrabajo cancelar(String idOrden);
    OrdenTrabajo buscarPorId(String idOrden);
    List<OrdenTrabajo> listarTodas();
    List<OrdenTrabajo> listarPorCliente(String idCliente);
    List<OrdenTrabajo> listarPorEstado(String estado);
    List<OrdenTrabajo> listarPorTecnico(String idTecnico);
    List<OrdenTrabajo> listarPorVehiculo(String placa);
}