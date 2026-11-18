package co.edu.uptcsoft.taller.vista.cli.consultas;

import co.edu.uptcsoft.taller.controller.old.ControlConsultas;
import co.edu.uptcsoft.taller.exception.TallerException;
import co.edu.uptcsoft.taller.vista.cli.ConsolaUtils;

import java.util.Scanner;

public class ConsultarOrdenPorIdCLI {

    private final Scanner sc;
    private final ControlConsultas controlConsultas;

    public ConsultarOrdenPorIdCLI(Scanner sc, ControlConsultas controlConsultas) {
        this.sc = sc;
        this.controlConsultas = controlConsultas;
    }

    public void execute() {
        System.out.println("MENU CONSULTAR ORDEN POR ID");

        System.out.println("Ingrese el ID de la Orden:");
        String idOrden = sc.nextLine();

        try {
            ConsolaUtils.imprimirExito("Detalle de la orden", controlConsultas.consultarOrdenPorId(idOrden));
        } catch (TallerException e) {
            ConsolaUtils.imprimirError(e.getMessage());
        }
    }
}