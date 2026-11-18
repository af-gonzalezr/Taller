package co.edu.uptcsoft.taller.vista.cli.registros;

import co.edu.uptcsoft.taller.controller.old.ControlRegistros;
import co.edu.uptcsoft.taller.exception.TallerException;
import co.edu.uptcsoft.taller.vista.cli.ConsolaUtils;

import java.util.Scanner;

public class RegistrarCamionCLI {

    private final Scanner sc;
    private final ControlRegistros controlRegistros;

    public RegistrarCamionCLI(Scanner sc, ControlRegistros controlRegistros) {
        this.sc = sc;
        this.controlRegistros = controlRegistros;
    }

    public void execute() {
        System.out.println("MENU REGISTRO CAMION");
        System.out.println("INGRESE LOS DATOS DEL CAMION");

        System.out.println("Placa:");
        String placa = sc.nextLine();

        System.out.println("Marca:");
        String marca = sc.nextLine();

        System.out.println("Modelo (Año):");
        String modelo = sc.nextLine();

        System.out.println("Identificación del Cliente:");
        String idCliente = sc.nextLine();

        System.out.println("Capacidad de Carga (Toneladas):");
        String capacidadCarga = sc.nextLine();

        try {
            ConsolaUtils.imprimirExito("Camión registrado exitosamente",
                    controlRegistros.crearCamion(placa, marca, modelo, idCliente, capacidadCarga));
        } catch (TallerException e) {
            ConsolaUtils.imprimirError(e.getMessage());
        }
    }
}