package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.*;
import co.edu.uptcsoft.taller.exception.ServiceException;
import co.edu.uptcsoft.taller.exception.ElementoNoEncontradoException;
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
        if (modelo <= 0 || !Double.isFinite(capacidadCargaTon) || capacidadCargaTon <= 0.0) {
            throw new ServiceException("El modelo y la capacidad de carga deben ser positivos");
        }

        if (vehiculoRepository.buscarPorId(placa).isPresent()) {
            throw new ServiceException("Placa ya registrada");
        }

        Optional<Cliente> cOpt = clienteRepository.buscarPorId(idCliente);
        if (cOpt.isEmpty()) {
            throw new ElementoNoEncontradoException("El cliente no existe");
        }

        Cliente cliente = cOpt.get();
        Camion camion = new Camion(placa, marca, modelo, cliente, capacidadCargaTon);
        vehiculoRepository.guardar(camion);

        return camion;
    }
}
