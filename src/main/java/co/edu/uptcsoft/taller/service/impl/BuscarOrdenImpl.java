package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.exception.ResourceNotFoundException;
import co.edu.uptcsoft.taller.repository.OrdenRepo;
import co.edu.uptcsoft.taller.service.BuscarOrden;

import java.util.Optional;

public class BuscarOrdenImpl implements BuscarOrden {

    private final OrdenRepo ordenRepository;

    public BuscarOrdenImpl(OrdenRepo ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    @Override
    public OrdenTrabajo execute(String idOrden) {
        Optional<OrdenTrabajo> ordenOpt = ordenRepository.buscarPorId(idOrden);
        if (ordenOpt.isEmpty()){
            throw new ResourceNotFoundException("La orden no existe");
        }
        return ordenOpt.get();
    }
}
