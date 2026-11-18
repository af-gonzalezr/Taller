package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.Tecnico;

public interface RegistrarTecnico {
    Tecnico execute(String idTecnico,
                    String nombre,
                    String especialidad);

}
