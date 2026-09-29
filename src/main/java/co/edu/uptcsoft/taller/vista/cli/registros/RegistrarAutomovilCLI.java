package co.edu.uptcsoft.taller.vista.cli.registros;

import co.edu.uptcsoft.taller.control.ControlRegistros;
import co.edu.uptcsoft.taller.vista.cli.RespuestaCLI;

import java.util.Scanner;

public class RegistrarAutomovilCLI {

    private final Scanner sc;
    private final ControlRegistros controlRegistros;

    public RegistrarAutomovilCLI(Scanner sc, ControlRegistros controlRegistros) {
        this.sc = sc;
        this.controlRegistros = controlRegistros;
    }

    public void execute() {
        System.out.println("MENU REGISTRO AUTOMOVIL");
        System.out.println("INGRESE LOS DATOS DEL AUTOMOVIL");

        System.out.println("Placa:");
        String placa = sc.nextLine();

        System.out.println("Marca:");
        String marca = sc.nextLine();

        System.out.println("Modelo (Año):");
        String modelo = sc.nextLine();

        System.out.println("Identificación del Cliente:");
        String idCliente = sc.nextLine();

        System.out.println("Número de Puertas:");
        String numeroPuertas = sc.nextLine();

        RespuestaCLI.mostrarRespuesta(controlRegistros.crearAutomovil(
                placa,
                marca,
                modelo,
                idCliente,
                numeroPuertas
        ));
    }
}