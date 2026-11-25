package co.edu.uptcsoft.taller.controller;

import co.edu.uptcsoft.taller.model.Automovil;
import co.edu.uptcsoft.taller.model.Camion;
import co.edu.uptcsoft.taller.model.Motocicleta;
import co.edu.uptcsoft.taller.model.Vehiculo;
import java.util.List;

public interface VehiculoController {
    Automovil registrarAuto(String placa, String marca, String modelo, String idCliente, String numeroPuertas);
    Motocicleta registrarMoto(String placa, String marca, String modelo, String idCliente, String cilindraje);
    Camion registrarCamion(String placa, String marca, String modelo, String idCliente, String capacidadCarga);
    Vehiculo buscarPorPlaca(String placa);
    List<Vehiculo> listarTodos();
    List<Vehiculo> listarPorCliente(String idCliente);
}