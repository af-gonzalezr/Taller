package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.BuscarOrden;

import java.util.Optional;

public class BuscarOrdenImpl implements BuscarOrden {

    private final Repository<OrdenTrabajo> ordenRepository;

    public BuscarOrdenImpl(Repository<OrdenTrabajo> ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    @Override
    public OrdenTrabajo execute(String idOrden) {
        /*Optional<OrdenTrabajo> ordenOpt = ordenRepository.buscarPorId(idOrden);
        if (ordenOpt.isEmpty()) {
            throw new ElementoNoEncontradoException("La orden no existe");
        }
        return ordenOpt.get();*/

        return ordenRepository
                .buscarPorId(idOrden)
                .orElseThrow(()-> new ElementoNoEncontradoException("La orden no existe"));
    }
}