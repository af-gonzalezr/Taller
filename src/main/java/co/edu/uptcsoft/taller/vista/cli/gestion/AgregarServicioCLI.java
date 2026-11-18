package co.edu.uptcsoft.taller.vista.cli.gestion;

import co.edu.uptcsoft.taller.control.ControlOrdenes;
import co.edu.uptcsoft.taller.exception.AppException;
import co.edu.uptcsoft.taller.vista.cli.ConsolaUtils;

import java.util.Scanner;

public class AgregarServicioCLI {

    private final Scanner sc;
    private final ControlOrdenes controlOrdenes;

    public AgregarServicioCLI(Scanner sc, ControlOrdenes controlOrdenes) {
        this.sc = sc;
        this.controlOrdenes = controlOrdenes;
    }

    public void execute() {
        System.out.println("MENU AGREGAR SERVICIO A ORDEN");
        System.out.println("INGRESE LOS DATOS DEL SERVICIO REALIZADO");

        System.out.println("ID de la Orden:");
        String idOrden = sc.nextLine();

        System.out.println("Descripción del Servicio:");
        String descripcion = sc.nextLine();

        System.out.println("Valor:");
        String valor = sc.nextLine();

        try {
            ConsolaUtils.imprimirExito("Trabajo agregado exitosamente",
                    controlOrdenes.agregarServicio(idOrden, descripcion, valor));
        } catch (AppException e) {
            ConsolaUtils.imprimirError(e.getMessage());
        }
    }
}