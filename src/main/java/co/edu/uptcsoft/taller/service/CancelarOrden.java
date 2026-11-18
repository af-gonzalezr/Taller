package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;

public interface CancelarOrden {
    OrdenTrabajo execute(String idOrden);
}
