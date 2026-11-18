package co.edu.uptcsoft.taller.control;

import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.exception.BusinessRuleException;
import co.edu.uptcsoft.taller.service.*;

import java.util.List;

public class ControlConsultas {

    private final BuscarOrden buscarOrdenPorId;
    private final TodasOrdenes todasOrdenes;
    private final OrdenesCliente ordenesCliente;
    private final OrdenesEstado ordenesEstado;

    public ControlConsultas(BuscarOrden buscarOrden,
                            TodasOrdenes todasOrdenes,
                            OrdenesCliente ordenesCliente,
                            OrdenesEstado ordenesEstado) {
        this.buscarOrdenPorId = buscarOrden;
        this.todasOrdenes = todasOrdenes;
        this.ordenesCliente = ordenesCliente;
        this.ordenesEstado = ordenesEstado;
    }

    public OrdenTrabajo consultarOrdenPorId(String idOrden) {
        if (estaVacio(idOrden)) {
            throw new BusinessRuleException("El campo idOrden esta vacío");
        }
        return buscarOrdenPorId.execute(idOrden.trim());
    }

    public List<OrdenTrabajo> consultarTodasLasOrdenes() {
        return todasOrdenes.execute();
    }

    public List<OrdenTrabajo> consultarOrdenesCliente(String idCliente) {
        if (estaVacio(idCliente)) {
            throw new BusinessRuleException("El campo idCliente esta vacío");
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