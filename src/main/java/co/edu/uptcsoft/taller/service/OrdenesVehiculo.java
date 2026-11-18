package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.model.Vehiculo;

import java.util.List;

public interface OrdenesVehiculo {

    List<OrdenTrabajo> execute(String placa);
}
