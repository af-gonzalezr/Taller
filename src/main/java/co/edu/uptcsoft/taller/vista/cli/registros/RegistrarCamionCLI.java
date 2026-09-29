package co.edu.uptcsoft.taller.vista.cli.registros;

import co.edu.uptcsoft.taller.control.ControlRegistros;
import co.edu.uptcsoft.taller.vista.cli.RespuestaCLI;

import java.util.Scanner;

public class RegistrarCamionCLI {
    private final Scanner sc;
    private final ControlRegistros controlRegistros;
    public RegistrarCamionCLI(Scanner sc, ControlRegistros controlRegistros) {
        this.sc = sc;
        this.controlRegistros = controlRegistros;
    }

   public void execute() {
        System.out.println("MENU REGISTRO CAMIONES");
        System.out.println("INGRESE LOS DATOS PARA REGISTRAR UN NUEVO CAMION");

        System.out.println("Placa:");
        String placa = sc.nextLine();

        System.out.println("Marca:");
        String marca = sc.nextLine();

        System.out.println("Modelo en años:");
        String modelo = sc.nextLine();

        System.out.println("ID del Cliente:");
        String idCliente = sc.nextLine();

        System.out.println("Capacidad de carga en toneladas");
        String capacidadCargaTon = sc.nextLine();

        RespuestaCLI.mostrarRespuesta(controlRegistros
                .crearAuto(placa,
                        marca,
                        modelo,
                        idCliente,
                    capacidadCargaTon));
    }  

}
