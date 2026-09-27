package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.RegistrarCliente;
import co.edu.uptcsoft.taller.service.Respuesta;

import javax.swing.event.CaretListener;

public class RegistrarClienteImpl implements RegistrarCliente {

    private final Repository<Cliente> clienteRepository;

    public RegistrarClienteImpl(Repository<Cliente> clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Respuesta<Cliente> execute(String idCliente, String nombre, String telefono, String email) {
        Cliente c = new Cliente(idCliente,nombre,telefono,email);
        if(clienteRepository.buscarPorId(idCliente).isPresent()){
            return Respuesta.error("Ya existe un cliente con esa id");
        }
        return Respuesta.completado("Cliente registrado exitosamente",
                clienteRepository.guardar(c));
    }
}
