package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.*;
import co.edu.uptcsoft.taller.exception.BusinessRuleException;
import co.edu.uptcsoft.taller.exception.ResourceNotFoundException;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.RegistrarCamion;

import java.util.Optional;

public class RegistrarCamionImpl implements RegistrarCamion {
    private final Repository<Cliente> clienteRepository;
    private final Repository<Vehiculo> vehiculoRepository;

    public RegistrarCamionImpl(Repository<Cliente> clienteRepository, Repository<Vehiculo> vehiculoRepository) {
        this.clienteRepository = clienteRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public Camion execute(String placa, String marca, int modelo, String idCliente, double capacidadCargaTon) {

        if (vehiculoRepository.buscarPorId(placa).isPresent()) {
            throw new BusinessRuleException("Placa ya registrada");
        }

        Optional<Cliente> cOpt = clienteRepository.buscarPorId(idCliente);
        if (cOpt.isEmpty()) {
            throw new ResourceNotFoundException("El cliente no existe");
        }

        Cliente cliente = cOpt.get();
        Camion camion = new Camion(placa, marca, modelo, cliente, capacidadCargaTon);
        vehiculoRepository.guardar(camion);

        return camion;
    }
}
