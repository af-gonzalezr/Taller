package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.TrabajoRealizado;

public interface AgregarTrabajoAOrden {
    TrabajoRealizado execute(String idOrden,
                             String descripcion,
                             double valor);
}
