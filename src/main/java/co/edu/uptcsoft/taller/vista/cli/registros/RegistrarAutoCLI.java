package co.edu.uptcsoft.taller.vista.cli.registros;

import co.edu.uptcsoft.taller.control.ControlRegistros;
import co.edu.uptcsoft.taller.vista.cli.RespuestaCLI;

import java.util.Scanner;

public class RegistrarAutoCLI {
    private final Scanner sc;
    private final ControlRegistros controlRegistros;
    
    public RegistrarAutoCLI(Scanner sc, ControlRegistros controlRegistros) {
        this.sc = sc;
        this.controlRegistros = controlRegistros;
    }

    public void execute() {
        System.out.println("MENU REGISTRO AUTOS");
        System.out.println("INGRESE LOS DATOS PARA REGISTRAR UN NUEVO AUTO");

        System.out.println("Placa:");
        String placa = sc.nextLine();

        System.out.println("Marca:");
        String marca = sc.nextLine();

        System.out.println("Modelo en años:");
        String modelo = sc.nextLine();

        System.out.println("ID del Cliente:");
        String idCliente = sc.nextLine();

        System.out.println("Numero de puertas");
        String numeroPuertas = sc.nextLine();

        RespuestaCLI.mostrarRespuesta(controlRegistros
                .crearAuto(placa,
                        marca,
                        modelo,
                        idCliente,
                    numeroPuertas));
    }


    
}
