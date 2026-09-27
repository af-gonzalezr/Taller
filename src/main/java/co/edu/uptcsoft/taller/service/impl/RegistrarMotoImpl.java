package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.Motocicleta;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.repository.impl.VehiculoRepository;
import co.edu.uptcsoft.taller.service.RegistrarMoto;
import co.edu.uptcsoft.taller.service.Respuesta;

import javax.swing.event.CaretListener;
import java.util.Optional;

public class RegistrarMotoImpl implements RegistrarMoto {

    private final Repository<Vehiculo> vehiculoRepository;
    private final Repository<Cliente> clienteRepository;

    public RegistrarMotoImpl(Repository<Vehiculo> vehiculoRepository, Repository<Cliente> clienteRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.clienteRepository = clienteRepository;
    }


    @Override
    public Respuesta<Motocicleta> execute(String placa, String marca, int modelo, String idCliente, int cilindraje) {

        if(vehiculoRepository.buscarPorId(placa).isPresent()){
            return Respuesta.error("La placa ya esta en uso");
        }

        Optional<Cliente> cOpt = clienteRepository.buscarPorId(idCliente);

        if (cOpt.isEmpty()){
            return Respuesta.error("El cliente no existe");
        }

        Cliente c = cOpt.get();
        Motocicleta m = new Motocicleta(placa, marca, modelo, c, cilindraje);
        vehiculoRepository.guardar(m);
        return Respuesta.completado("Moto registrada exitosamente", m);
    }
}
