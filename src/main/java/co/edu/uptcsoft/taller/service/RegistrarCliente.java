package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.Cliente;

public interface RegistrarCliente {
    Respuesta<Cliente> execute(String idCliente,
                    String nombre,
                    String telefono,
                    String email);
}
