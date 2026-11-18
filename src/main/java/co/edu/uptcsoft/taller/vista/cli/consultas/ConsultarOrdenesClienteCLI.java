package co.edu.uptcsoft.taller.vista.cli.consultas;

import co.edu.uptcsoft.taller.controller.old.ControlConsultas;
import co.edu.uptcsoft.taller.exception.TallerException;
import co.edu.uptcsoft.taller.vista.cli.ConsolaUtils;

import java.util.Scanner;

public class ConsultarOrdenesClienteCLI {

    private final Scanner sc;
    private final ControlConsultas controlConsultas;

    public ConsultarOrdenesClienteCLI(Scanner sc, ControlConsultas controlConsultas) {
        this.sc = sc;
        this.controlConsultas = controlConsultas;
    }

    public void execute() {
        System.out.println("MENU CONSULTAR ORDENES POR CLIENTE");

        System.out.println("Ingrese la Identificación del Cliente:");
        String idCliente = sc.nextLine();

        try {
            ConsolaUtils.imprimirExito("Ordenes del cliente",
                    controlConsultas.consultarOrdenesCliente(idCliente));
        } catch (TallerException e) {
            ConsolaUtils.imprimirError(e.getMessage());
        }
    }
}