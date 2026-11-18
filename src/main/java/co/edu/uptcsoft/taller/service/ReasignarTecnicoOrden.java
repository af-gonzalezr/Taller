package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;

public interface ReasignarTecnicoOrden {
    OrdenTrabajo execute(String idOrden, String idNuevoTecnico);
}