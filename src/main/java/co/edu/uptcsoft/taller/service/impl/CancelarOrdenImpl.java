package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.OrdenRepo;
import co.edu.uptcsoft.taller.service.CancelarOrden;
import co.edu.uptcsoft.taller.service.Respuesta;

import java.util.Optional;

public class CancelarOrdenImpl implements CancelarOrden {

    private final OrdenRepo ordenRepository;

    public CancelarOrdenImpl(OrdenRepo ordenRepository) {
        this.ordenRepository = ordenRepository;
    }


    @Override
    public Respuesta<OrdenTrabajo> execute(String idOrden) {
        Optional<OrdenTrabajo> ordenOpt= ordenRepository.buscarPorId(idOrden);
        if(ordenOpt.isEmpty()){
            return Respuesta.error("La orden no existe");
        }
        OrdenTrabajo o = ordenOpt.get();
        if (o.getEstado()!= EstadoOrden.EN_PROCESO){
            return Respuesta.error("Solo se pueden cancelar ordenes en proceso");
        }
        o.setEstado(EstadoOrden.CANCELADA);
        return Respuesta.completado("Orden cancelada exitosamente",
                ordenRepository.guardar(o));
    }
}
