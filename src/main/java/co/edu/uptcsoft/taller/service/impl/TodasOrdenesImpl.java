package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.OrdenRepo;
import co.edu.uptcsoft.taller.service.Respuesta;
import co.edu.uptcsoft.taller.service.TodasOrdenes;

import java.util.List;

public class TodasOrdenesImpl implements TodasOrdenes {
    private  final OrdenRepo ordenRepository;

    public TodasOrdenesImpl(OrdenRepo ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    @Override
    public Respuesta<List<OrdenTrabajo>> execute() {
        List<OrdenTrabajo> ordenes = ordenRepository.todas();
        String mensaje;
        if(ordenes.isEmpty()){
            mensaje = "Aun no hay ordenes Registradas";
        }else {
            mensaje = "Ordenes registradas: ";
        }
        return Respuesta.completado(mensaje,ordenes);
    }
}
