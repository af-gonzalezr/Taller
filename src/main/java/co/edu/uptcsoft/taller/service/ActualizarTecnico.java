package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.Tecnico;

public interface ActualizarTecnico {
    void execute(String idTecnico, String nuevoNombre, String nuevaEspecialidad);
}
