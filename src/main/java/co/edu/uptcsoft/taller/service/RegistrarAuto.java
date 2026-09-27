package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.Automovil;

public interface RegistrarAuto {
    Respuesta<Automovil> execute(String placa,
                      String marca,
                      int modelo,
                      String idCliente,
                      int numeroPuertas);
}
