package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.Camion;
import co.edu.uptcsoft.taller.model.Cliente;

public interface RegistrarCamion {
    Respuesta<Camion> execute(String placa,
                   String marca,
                   int modelo,
                   String idCliente,
                   double capacidadCargaTon);
}
