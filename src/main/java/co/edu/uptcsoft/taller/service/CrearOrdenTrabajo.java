package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import java.time.LocalDateTime;

public interface CrearOrdenTrabajo {
    Respuesta<OrdenTrabajo> execute(String idOrden,
                         LocalDateTime fechaHoraIngreso,
                         String idVehiculo,
                         String idTecnico,
                         String observacionesIngreso);
}
