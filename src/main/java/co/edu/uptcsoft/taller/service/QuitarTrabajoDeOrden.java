package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;

public interface QuitarTrabajoDeOrden {
    OrdenTrabajo execute(String idOrden, int indiceTrabajo);
}