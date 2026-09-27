package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.Tecnico;

public interface RegistrarTecnico {
    Respuesta<Tecnico> execute(String idTecnico,
                    String nombre,
                    String especialidad);

}
