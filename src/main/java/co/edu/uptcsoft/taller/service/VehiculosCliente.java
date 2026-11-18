package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.Vehiculo;

import java.util.List;

public interface VehiculosCliente {
    List<Vehiculo> execute(String idCliente);
}
