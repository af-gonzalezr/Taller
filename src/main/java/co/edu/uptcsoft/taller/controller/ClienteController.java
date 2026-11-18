package co.edu.uptcsoft.taller.controller;

import co.edu.uptcsoft.taller.model.Cliente;
import java.util.List;

public interface ClienteController {
    Cliente registrar(String id, String nombre, String telefono, String correo);
    Cliente buscarPorId(String id);
    List<Cliente> listarTodos();
}