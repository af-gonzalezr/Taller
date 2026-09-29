package co.edu.uptcsoft.taller.vista.cli.registros;

import co.edu.uptcsoft.taller.control.ControlRegistros;
import co.edu.uptcsoft.taller.vista.cli.RespuestaCLI;

import java.util.Scanner;

public class RegistrarMotocicletaCLI {

    private final Scanner sc;
    private final ControlRegistros controlRegistros;

    public RegistrarMotocicletaCLI(Scanner sc, ControlRegistros controlRegistros) {
        this.sc = sc;
        this.controlRegistros = controlRegistros;
    }

    public void execute() {
        System.out.println("MENU REGISTRO MOTOCICLETA");
        System.out.println("INGRESE LOS DATOS DE LA MOTOCICLETA");

        System.out.println("Placa:");
        String placa = sc.nextLine();

        System.out.println("Marca:");
        String marca = sc.nextLine();

        System.out.println("Modelo (Año):");
        String modelo = sc.nextLine();

        System.out.println("Identificación del Cliente:");
        String idCliente = sc.nextLine();

        System.out.println("Cilindraje (cc):");
        String cilindraje = sc.nextLine();

        RespuestaCLI.mostrarRespuesta(controlRegistros.crearMotocicleta(
                placa,
                marca,
                modelo,
                idCliente,
                cilindraje
        ));
    }
}