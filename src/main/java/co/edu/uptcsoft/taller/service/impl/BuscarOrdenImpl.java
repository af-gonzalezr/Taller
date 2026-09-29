package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.OrdenRepo;
import co.edu.uptcsoft.taller.service.BuscarOrden;
import co.edu.uptcsoft.taller.service.Respuesta;

import java.util.Optional;

public class BuscarOrdenImpl implements BuscarOrden {

    private final OrdenRepo ordenRepository;

    public BuscarOrdenImpl(OrdenRepo ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    @Override
    public Respuesta<OrdenTrabajo> execute(String idOrden) {
        Optional<OrdenTrabajo> ordenOpt = ordenRepository.buscarPorId(idOrden);
        if (ordenOpt.isEmpty()){
            return Respuesta.error("La orden no existe");
        }
        return Respuesta.completado("Detalle de la orden :",ordenOpt.get());
    }
}
