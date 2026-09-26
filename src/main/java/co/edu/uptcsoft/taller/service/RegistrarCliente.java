package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.Cliente;

public interface RegistrarCliente {
    Cliente execute(String cedula,
                    String nombre,
                    String telefono,
                    String email);
}
