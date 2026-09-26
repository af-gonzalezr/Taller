package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.Automovil;
import co.edu.uptcsoft.taller.model.Cliente;

public interface RegistrarAuto {
    Automovil execute(String placa,
                      String marca,
                      int modelo,
                      String idCliente,
                      int numeroPuertas);
}
