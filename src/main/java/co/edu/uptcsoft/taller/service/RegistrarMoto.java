package co.edu.uptcsoft.taller.service;

import co.edu.uptcsoft.taller.model.Motocicleta;

public interface RegistrarMoto {
    Respuesta<Motocicleta> execute(String placa,
                        String marca,
                        int modelo,
                        String idCliente,
                        int cilindraje);
}
