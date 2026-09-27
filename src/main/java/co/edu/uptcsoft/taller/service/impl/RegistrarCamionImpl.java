package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.*;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.RegistrarCamion;
import co.edu.uptcsoft.taller.service.Respuesta;

import java.util.Optional;

public class RegistrarCamionImpl implements RegistrarCamion {
    private final Repository<Cliente> clienteRepository;
    private final Repository<Vehiculo> vehiculoRepository;

    public RegistrarCamionImpl(Repository<Cliente> clienteRepository, Repository<Vehiculo> vehiculoRepository) {
        this.clienteRepository = clienteRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public Respuesta<Camion> execute(String placa, String marca, int modelo, String idCliente, double capacidadCargaTon) {

        if (vehiculoRepository.buscarPorId(placa).isPresent()) {
            return Respuesta.error("Placa ya registrada");
        }

        Optional<Cliente> cOpt = clienteRepository.buscarPorId(idCliente);
        if (cOpt.isEmpty()) {
            return Respuesta.error("El cliente no existe");
        }

        Cliente cliente = cOpt.get();
        Camion camion = new Camion(placa, marca, modelo, cliente, capacidadCargaTon);
        vehiculoRepository.guardar(camion);

        return Respuesta.completado("Camión registrado exitosamente", camion);
    }
}
