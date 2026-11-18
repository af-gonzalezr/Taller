package co.edu.uptcsoft.taller.controller;

import co.edu.uptcsoft.taller.model.Tecnico;
import java.util.List;

public interface TecnicoController {
    Tecnico registrar(String id, String nombre, String especialidad);
    Tecnico buscarPorId(String id);
    List<Tecnico> listarTodos();
}