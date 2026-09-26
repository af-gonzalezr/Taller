package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.Cliente;

public interface ActualizarCliente {
    void execute(String idCliente, String nuevoNombre, String nuevoTelefono, String nuevoEmail);
}
