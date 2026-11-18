package co.edu.uptcsoft.taller.controller.old;

import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.service.*;

import java.util.List;

public class ControlConsultas {

    private final BuscarOrden buscarOrdenPorId;
    private final ListarOrdenes listarOrdenes;
    private final OrdenesCliente ordenesCliente;
    private final OrdenesEstado ordenesEstado;

    public ControlConsultas(BuscarOrden buscarOrden,
                            ListarOrdenes listarOrdenes,
                            OrdenesCliente ordenesCliente,
                            OrdenesEstado ordenesEstado) {
        this.buscarOrdenPorId = buscarOrden;
        this.listarOrdenes = listarOrdenes;
        this.ordenesCliente = ordenesCliente;
        this.ordenesEstado = ordenesEstado;
    }

    public OrdenTrabajo consultarOrdenPorId(String idOrden) {
        if (estaVacio(idOrden)) {
            throw new ServiceException("El campo idOrden esta vacío");
        }
        return buscarOrdenPorId.execute(idOrden.trim());
    }

    public List<OrdenTrabajo> consultarTodasLasOrdenes() {
        return listarOrdenes.execute();
    }

    public List<OrdenTrabajo> consultarOrdenesCliente(String idCliente) {
        if (estaVacio(idCliente)) {
            throw new ServiceException("El campo idCliente esta vacío");
        }
        return ordenesCliente.execute(idCliente.trim());
    }

    public List<OrdenTrabajo> consultarOrdenesEnProceso() {
        return ordenesEstado.execute(EstadoOrden.EN_PROCESO);
    }

    public List<OrdenTrabajo> consultarOrdenesFinalizadas() {
        return ordenesEstado.execute(EstadoOrden.FINALIZADA);
    }

    public List<OrdenTrabajo> consultarOrdenesCanceladas() {
        return ordenesEstado.execute(EstadoOrden.CANCELADA);
    }

    private boolean estaVacio(String texto) {
        return texto == null || texto.isBlank();
    }
}