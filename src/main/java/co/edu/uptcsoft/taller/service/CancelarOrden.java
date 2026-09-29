package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;

public interface CancelarOrden {
    Respuesta<OrdenTrabajo> execute(String idOrden);
}