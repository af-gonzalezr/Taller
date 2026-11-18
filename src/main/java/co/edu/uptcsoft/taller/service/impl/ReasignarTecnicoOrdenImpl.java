package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.ReasignarTecnicoOrden;

public class ReasignarTecnicoOrdenImpl implements ReasignarTecnicoOrden {

    private final Repository<OrdenTrabajo> ordenRepository;
    private final Repository<Tecnico> tecnicoRepository;

    public ReasignarTecnicoOrdenImpl(Repository<OrdenTrabajo> ordenRepository, Repository<Tecnico> tecnicoRepository) {
        this.ordenRepository = ordenRepository;
        this.tecnicoRepository = tecnicoRepository;
    }

    @Override
    public OrdenTrabajo execute(String idOrden, String idNuevoTecnico) {
        if (idOrden == null || idOrden.isBlank()) {
            throw new ServiceException("El ID de la orden de trabajo es obligatorio.");
        }
        if (idNuevoTecnico == null || idNuevoTecnico.isBlank()) {
            throw new ServiceException("El ID del nuevo técnico es obligatorio.");
        }

        OrdenTrabajo orden = ordenRepository.buscarPorId(idOrden)
                .orElseThrow(() -> new ElementoNoEncontradoException("La orden no existe"));

        if (orden.getEstado() != EstadoOrden.EN_PROCESO) {
            throw new ServiceException("Solo    se puede reasignar el técnico a órdenes en proceso");
        }

        Tecnico nuevoTecnico = tecnicoRepository.buscarPorId(idNuevoTecnico)
                .orElseThrow(() -> new ElementoNoEncontradoException("El técnico no existe"));

        if (orden.getTecnico() != null && orden.getTecnico().getId().equalsIgnoreCase(idNuevoTecnico)) {
            throw new ServiceException("La orden ya se encuentra asignada a este técnico");
        }

        orden.setTecnico(nuevoTecnico);
        return ordenRepository.guardar(orden);
    }
}