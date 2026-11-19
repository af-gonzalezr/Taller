package co.edu.uptcsoft.taller.controller.impl;

import co.edu.uptcsoft.taller.controller.OrdenController;
import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.model.TrabajoRealizado;
import co.edu.uptcsoft.taller.service.*;

import java.time.LocalDateTime;
import java.util.List;

public class OrdenControllerImpl implements OrdenController {
    private final CrearOrdenTrabajo crearOrdenTrabajo;
    private final AgregarTrabajoAOrden agregarTrabajoAOrden;
    private final QuitarTrabajoDeOrden quitarTrabajoDeOrden;
    private final ReasignarTecnicoOrden reasignarTecnicoOrden;
    private final FinalizarOrden finalizarOrden;
    private final CancelarOrden cancelarOrden;
    private final BuscarOrden buscarOrden;
    private final ListarOrdenes listarOrdenes;
    private final OrdenesCliente ordenesCliente;
    private final OrdenesEstado ordenesEstado;
    private final OrdenesTecnico ordenesTecnico;
    private final OrdenesVehiculo ordenesVehiculo;

    public OrdenControllerImpl(CrearOrdenTrabajo crearOrdenTrabajo,
                               AgregarTrabajoAOrden agregarTrabajoAOrden,
                               QuitarTrabajoDeOrden quitarTrabajoDeOrden,
                               ReasignarTecnicoOrden reasignarTecnicoOrden,
                               FinalizarOrden finalizarOrden,
                               CancelarOrden cancelarOrden,
                               BuscarOrden buscarOrden,
                               ListarOrdenes listarOrdenes,
                               OrdenesCliente ordenesCliente,
                               OrdenesEstado ordenesEstado,
                               OrdenesTecnico ordenesTecnico,
                               OrdenesVehiculo ordenesVehiculo) {
        this.crearOrdenTrabajo = crearOrdenTrabajo;
        this.agregarTrabajoAOrden = agregarTrabajoAOrden;
        this.quitarTrabajoDeOrden = quitarTrabajoDeOrden;
        this.reasignarTecnicoOrden = reasignarTecnicoOrden;
        this.finalizarOrden = finalizarOrden;
        this.cancelarOrden = cancelarOrden;
        this.buscarOrden = buscarOrden;
        this.listarOrdenes = listarOrdenes;
        this.ordenesCliente = ordenesCliente;
        this.ordenesEstado = ordenesEstado;
        this.ordenesTecnico = ordenesTecnico;
        this.ordenesVehiculo = ordenesVehiculo;
    }

    @Override
    public OrdenTrabajo crear(String idOrden, String fechaHoraIngreso, String idVehiculo,
                              String idTecnico, String observacionesIngreso) {
        String normalizedOrderId = ControllerValidation.requiredText(idOrden, "idOrden");
        LocalDateTime normalizedDate = ControllerValidation.localDateTime(
                fechaHoraIngreso, "fechaHoraIngreso");
        String normalizedVehicle = ControllerValidation.requiredText(idVehiculo, "idVehiculo");
        String normalizedTechnician = ControllerValidation.requiredText(idTecnico, "idTecnico");
        String normalizedObservations = ControllerValidation.optionalText(observacionesIngreso);
        return crearOrdenTrabajo.execute(normalizedOrderId, normalizedDate, normalizedVehicle,
                normalizedTechnician, normalizedObservations);
    }

    @Override
    public TrabajoRealizado agregarTrabajo(String idOrden, String descripcion, String valor) {
        String normalizedOrderId = ControllerValidation.requiredText(idOrden, "idOrden");
        String normalizedDescription = ControllerValidation.requiredText(descripcion, "descripcion");
        double normalizedValue = ControllerValidation.positiveDouble(valor, "valor");
        return agregarTrabajoAOrden.execute(normalizedOrderId, normalizedDescription, normalizedValue);
    }

    @Override
    public OrdenTrabajo quitarTrabajo(String idOrden, String indiceTrabajo) {
        String normalizedOrderId = ControllerValidation.requiredText(idOrden, "idOrden");
        int normalizedIndex = ControllerValidation.nonNegativeInt(indiceTrabajo, "indiceTrabajo");
        return quitarTrabajoDeOrden.execute(normalizedOrderId, normalizedIndex);
    }

    @Override
    public OrdenTrabajo reasignarTecnico(String idOrden, String idNuevoTecnico) {
        String normalizedOrderId = ControllerValidation.requiredText(idOrden, "idOrden");
        String normalizedTechnicianId = ControllerValidation.requiredText(idNuevoTecnico, "idNuevoTecnico");
        return reasignarTecnicoOrden.execute(normalizedOrderId, normalizedTechnicianId);
    }

    @Override
    public OrdenTrabajo finalizar(String idOrden, String observacionesEntrega) {
        String normalizedOrderId = ControllerValidation.requiredText(idOrden, "idOrden");
        String normalizedObservations = ControllerValidation.requiredText(
                observacionesEntrega, "observacionesEntrega");
        return finalizarOrden.execute(normalizedOrderId, normalizedObservations);
    }

    @Override
    public OrdenTrabajo cancelar(String idOrden) {
        return cancelarOrden.execute(
                ControllerValidation.requiredText(idOrden, "idOrden"));
    }

    @Override
    public OrdenTrabajo buscarPorId(String idOrden) {
        return buscarOrden.execute(
                ControllerValidation.requiredText(idOrden, "idOrden"));
    }

    @Override
    public List<OrdenTrabajo> listarTodas() {
        return listarOrdenes.execute();
    }

    @Override
    public List<OrdenTrabajo> listarPorCliente(String idCliente) {
        return ordenesCliente.execute(
                ControllerValidation.requiredText(idCliente, "idCliente"));
    }

    @Override
    public List<OrdenTrabajo> listarPorEstado(String estado) {
        EstadoOrden normalizedState = ControllerValidation.state(estado);
        return ordenesEstado.execute(normalizedState);
    }

    @Override
    public List<OrdenTrabajo> listarPorTecnico(String idTecnico) {
        return ordenesTecnico.execute(
                ControllerValidation.requiredText(idTecnico, "idTecnico"));
    }

    @Override
    public List<OrdenTrabajo> listarPorVehiculo(String placa) {
        return ordenesVehiculo.execute(
                ControllerValidation.plate(placa));
    }
}
