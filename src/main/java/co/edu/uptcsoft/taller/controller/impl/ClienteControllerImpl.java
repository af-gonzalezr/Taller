package co.edu.uptcsoft.taller.controller.impl;

import co.edu.uptcsoft.taller.controller.ClienteController;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.service.BuscarCliente;
import co.edu.uptcsoft.taller.service.ListarClientes;
import co.edu.uptcsoft.taller.service.RegistrarCliente;

import java.util.List;

public class ClienteControllerImpl implements ClienteController {
    private final RegistrarCliente registrarCliente;
    private final BuscarCliente buscarCliente;
    private final ListarClientes listarClientes;

    public ClienteControllerImpl(RegistrarCliente registrarCliente,
                                 BuscarCliente buscarCliente,
                                 ListarClientes listarClientes) {
        this.registrarCliente = registrarCliente;
        this.buscarCliente = buscarCliente;
        this.listarClientes = listarClientes;
    }

    @Override
    public Cliente registrar(String id, String nombre, String telefono, String correo) {
        String normalizedId = ControllerValidation.requiredText(id, "id");
        String normalizedName = ControllerValidation.requiredText(nombre, "nombre");
        String normalizedPhone = ControllerValidation.phone(telefono);
        String normalizedEmail = ControllerValidation.email(correo);
        return registrarCliente.execute(normalizedId, normalizedName, normalizedPhone, normalizedEmail);
    }

    @Override
    public Cliente buscarPorId(String id) {
        return buscarCliente.execute(ControllerValidation.requiredText(id, "id"));
    }

    @Override
    public List<Cliente> listarTodos() {
        return listarClientes.execute();
    }
}
