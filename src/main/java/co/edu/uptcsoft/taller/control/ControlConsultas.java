package co.edu.uptcsoft.taller.control;

import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
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

    public Respuesta<OrdenTrabajo> consultarOrdenPorId(String idOrden) {
        if (estaVacio(idOrden)) {
            return Respuesta.error("El campo idOrden esta vacío");
        }
        return buscarOrdenPorId.execute(idOrden.trim());
    }

    public Respuesta<List<OrdenTrabajo>> consultarTodasLasOrdenes() {
        return todasOrdenes.execute();
    }

    public Respuesta<List<OrdenTrabajo>> consultarOrdenesCliente(String idCliente) {
        if (estaVacio(idCliente)) {
            return Respuesta.error("El campo idCliente esta vacío");
        }
        return ordenesCliente.execute(idCliente.trim());
    }

    public Respuesta<List<OrdenTrabajo>> consultarOrdenesEnProceso() {
        return ordenesEstado.execute(EstadoOrden.EN_PROCESO);
    }

    public Respuesta<List<OrdenTrabajo>> consultarOrdenesFinalizadas() {
        return ordenesEstado.execute(EstadoOrden.FINALIZADA);
    }

    public Respuesta<List<OrdenTrabajo>> consultarOrdenesCanceladas() {
        return ordenesEstado.execute(EstadoOrden.CANCELADA);
    }

    private boolean estaVacio(String texto) {
        return texto == null || texto.isBlank();
    }
}