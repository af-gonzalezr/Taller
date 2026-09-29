package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.OrdenRepo;
import co.edu.uptcsoft.taller.service.OrdenesEstado;
import co.edu.uptcsoft.taller.service.Respuesta;

import java.util.List;

public class OrdenesEstadoImpl implements OrdenesEstado {

    private final OrdenRepo ordenRepository;

    public OrdenesEstadoImpl(OrdenRepo ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    @Override
    public Respuesta<List<OrdenTrabajo>> execute(EstadoOrden estadoOrden) {
        List<OrdenTrabajo> ordenes = ordenRepository.buscarPorEstado(estadoOrden);
        if (ordenes.isEmpty()){
            return Respuesta.completado("No hay ordenes con estado: "+ estadoOrden.toString(),ordenes);
        }else{
            return Respuesta.completado("Ordenes con estado "+ estadoOrden.toString(),ordenes);
        }
    }
}
