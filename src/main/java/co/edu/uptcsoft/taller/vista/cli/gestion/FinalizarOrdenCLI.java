package co.edu.uptcsoft.taller.vista.cli.gestion;

import co.edu.uptcsoft.taller.control.ControlOrdenes;
import co.edu.uptcsoft.taller.vista.cli.RespuestaCLI;

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

        RespuestaCLI.mostrarRespuesta(controlOrdenes.finalizarOrden(
                idOrden,
                observaciones
        ));
    }
}