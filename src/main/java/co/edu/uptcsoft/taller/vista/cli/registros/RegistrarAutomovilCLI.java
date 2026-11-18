package co.edu.uptcsoft.taller.vista.cli.registros;

import co.edu.uptcsoft.taller.controller.old.ControlRegistros;
import co.edu.uptcsoft.taller.exception.TallerException;
import co.edu.uptcsoft.taller.model.Automovil;
import co.edu.uptcsoft.taller.vista.cli.ConsolaUtils;

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

        try {
            Automovil automovil = controlRegistros.crearAutomovil(
                    placa,
                    marca,
                    modelo,
                    idCliente,
                    numeroPuertas
            );
            ConsolaUtils.imprimirExito("Automóvil registrado exitosamente", automovil);
        } catch (TallerException e) {
            ConsolaUtils.imprimirError(e.getMessage());
        }
    }
}