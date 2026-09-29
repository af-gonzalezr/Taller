package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.OrdenRepo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.FinalizarOrden;
import co.edu.uptcsoft.taller.service.OrdenesTecnico;
import co.edu.uptcsoft.taller.service.Respuesta;

import java.time.LocalDateTime;
import java.util.Optional;

public class FinalizarOrdenImpl implements FinalizarOrden {

    private final OrdenRepo ordenTrabajoRepository;

    public FinalizarOrdenImpl(OrdenRepo ordenTrabajoRepository) {
        this.ordenTrabajoRepository = ordenTrabajoRepository;
    }

    @Override
    public Respuesta<OrdenTrabajo> execute(String idOrden, String obsevacionesEntrega) {
        Optional<OrdenTrabajo> ordenOpt = ordenTrabajoRepository.buscarPorId(idOrden);
        if (ordenOpt.isEmpty()){
            return Respuesta.error("La orden no existe");
        }
        OrdenTrabajo orden = ordenOpt.get();
        if (orden.getEstado()!= EstadoOrden.EN_PROCESO){
            return Respuesta.error("Solo se pueden finalizar ordenes en proceso");
        }
        orden.setEstado(EstadoOrden.FINALIZADA);
        orden.setObservacionesEntrega(obsevacionesEntrega);
        orden.setFechaHoraEntrega(LocalDateTime.now());
        return Respuesta.completado("Orden finalizada exitosamente",
                ordenTrabajoRepository.guardar(orden));
    }
}
