package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.Tecnico;

public interface ActualizarTecnico {
    Respuesta<Tecnico> execute(String idTecnico, String nuevoNombre, String nuevaEspecialidad);
}
