package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.model.TrabajoRealizado;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.AgregarTrabajoAOrden;

import java.util.Optional;

public class AgregarTrabajoAOrdenImpl implements AgregarTrabajoAOrden {

    private final Repository<OrdenTrabajo> ordenTrabajoRepository;

    public AgregarTrabajoAOrdenImpl(Repository<OrdenTrabajo> ordenTrabajoRepository) {
        this.ordenTrabajoRepository = ordenTrabajoRepository;
    }

    @Override
    public TrabajoRealizado execute(String idOrden, String descripcion, double valor) {
        Optional<OrdenTrabajo> ordenOpt = ordenTrabajoRepository.buscarPorId(idOrden);
        if (ordenOpt.isEmpty()) {
            throw new ElementoNoEncontradoException("La orden no existe");
        }
        OrdenTrabajo orden = ordenOpt.get();
        if (orden.getEstado() != EstadoOrden.EN_PROCESO) {
            throw new ServiceException("Solo se pueden agregar trabajos a órdenes que estén en proceso");
        }
        TrabajoRealizado trabajoRealizado = new TrabajoRealizado(descripcion, valor);
        orden.agregarTrabajo(trabajoRealizado);
        ordenTrabajoRepository.guardar(orden);
        return trabajoRealizado;
    }
}