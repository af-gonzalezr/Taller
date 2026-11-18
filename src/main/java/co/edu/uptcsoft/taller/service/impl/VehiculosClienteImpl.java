package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.VehiculosCliente;

import java.util.List;

public class VehiculosClienteImpl implements VehiculosCliente {
    private final Repository<Cliente> clienteRepository;
    private final Repository<Vehiculo> vehiculoRepository;

    public VehiculosClienteImpl(Repository<Cliente> clienteRepository,
                                Repository<Vehiculo> vehiculoRepository) {
        this.clienteRepository = clienteRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public List<Vehiculo> execute(String idCliente) {
        clienteRepository.buscarPorId(idCliente)
                .orElseThrow(() -> new ElementoNoEncontradoException("El cliente no existe"));

        return vehiculoRepository.filtrar(vehiculo ->
                vehiculo.getCliente() != null
                        && idCliente.equalsIgnoreCase(vehiculo.getCliente().getId()));
    }
}
