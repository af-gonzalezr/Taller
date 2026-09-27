package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.ServicioRealizado;

public interface AgregarTrabajoAOrden {
    Respuesta<ServicioRealizado> execute(String idOrden,
                                         String descripcion,
                                         double valor);
}
