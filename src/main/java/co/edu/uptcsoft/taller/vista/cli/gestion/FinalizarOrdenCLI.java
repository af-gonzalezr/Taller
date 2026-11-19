package co.edu.uptcsoft.taller.vista.cli.gestion;

import co.edu.uptcsoft.taller.controller.old.ControlOrdenes;
import co.edu.uptcsoft.taller.exception.TallerException;
import co.edu.uptcsoft.taller.vista.cli.ConsolaUtils;

import java.util.Scanner;

public class FinalizarOrdenCLI {

    private final Scanner sc;
    private final ControlOrdenes controlOrdenes;

    public FinalizarOrdenCLI(Scanner sc, ControlOrdenes controlOrdenes) {
        this.sc = sc;
        this.controlOrdenes = controlOrdenes;
    }

    public void execute() {
        System.out.println("MENU FINALIZAR ORDEN DE TRABAJO");

        System.out.println("ID de la Orden a Finalizar:");
        String idOrden = sc.nextLine();

        System.out.println("Observaciones de Finalización:");
        String observaciones = sc.nextLine();

        try {
            ConsolaUtils.imprimirExito("Orden finalizada exitosamente",
                    controlOrdenes.finalizarOrden(idOrden, observaciones));
        } catch (TallerException e) {
            ConsolaUtils.imprimirError(e.getMessage());
        }
    }
}