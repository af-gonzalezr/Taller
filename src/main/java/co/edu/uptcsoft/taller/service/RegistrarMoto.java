package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.Motocicleta;

public interface RegistrarMoto {
    Motocicleta execute(String placa,
                        String marca,
                        int modelo,
                        String idCliente,
                        int cilindraje);
}
