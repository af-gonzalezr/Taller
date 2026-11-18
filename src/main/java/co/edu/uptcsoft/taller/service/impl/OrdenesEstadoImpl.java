package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.OrdenRepo;
import co.edu.uptcsoft.taller.service.OrdenesEstado;

import java.util.List;

public class OrdenesEstadoImpl implements OrdenesEstado {

    private final OrdenRepo ordenRepository;

    public OrdenesEstadoImpl(OrdenRepo ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    @Override
    public List<OrdenTrabajo> execute(EstadoOrden estadoOrden) {
        return ordenRepository.buscarPorEstado(estadoOrden);
    }
}
