package co.edu.uptcsoft.taller.vista.cli.gestion;

import co.edu.uptcsoft.taller.control.ControlOrdenes;
import co.edu.uptcsoft.taller.vista.cli.RespuestaCLI;

import java.util.Scanner;

public class CancelarOrdenCLI {

    private final Scanner sc;
    private final ControlOrdenes controlOrdenes;

    public CancelarOrdenCLI(Scanner sc, ControlOrdenes controlOrdenes) {
        this.sc = sc;
        this.controlOrdenes = controlOrdenes;
    }

    public void execute() {
        System.out.println("MENU CANCELAR ORDEN DE TRABAJO");

        System.out.println("ID de la Orden a Cancelar:");
        String idOrden = sc.nextLine();

        RespuestaCLI.mostrarRespuesta(controlOrdenes.cancelarOrden(idOrden));
    }
}
