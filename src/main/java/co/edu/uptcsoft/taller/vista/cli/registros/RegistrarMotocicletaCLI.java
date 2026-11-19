package co.edu.uptcsoft.taller.vista.cli.registros;

import co.edu.uptcsoft.taller.controller.old.ControlRegistros;
import co.edu.uptcsoft.taller.exception.TallerException;
import co.edu.uptcsoft.taller.model.Motocicleta;
import co.edu.uptcsoft.taller.vista.cli.ConsolaUtils;

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

        try {
            Motocicleta motocicleta = controlRegistros.crearMotocicleta(
                    placa,
                    marca,
                    modelo,
                    idCliente,
                    cilindraje
            );
            ConsolaUtils.imprimirExito("Moto registrada exitosamente", motocicleta);
        } catch (TallerException e) {
            ConsolaUtils.imprimirError(e.getMessage());
        }
    }
}