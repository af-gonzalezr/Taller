package co.edu.uptcsoft.taller.vista.cli.gestion;

import co.edu.uptcsoft.taller.control.ControlOrdenes;
import co.edu.uptcsoft.taller.exception.AppException;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.vista.cli.ConsolaUtils;

import java.util.Scanner;

public class CrearOrdenCLI {

    private final Scanner sc;
    private final ControlOrdenes controlOrdenes;

    public CrearOrdenCLI(Scanner sc, ControlOrdenes controlOrdenes) {
        this.sc = sc;
        this.controlOrdenes = controlOrdenes;
    }

    public void execute() {
        System.out.println("MENU CREAR ORDEN DE TRABAJO");
        System.out.println("INGRESE LOS DATOS PARA UNA NUEVA ORDEN");

        System.out.println("ID de la Orden:");
        String idOrden = sc.nextLine();

        System.out.println("Placa del Vehículo:");
        String placa = sc.nextLine();

        System.out.println("ID del Técnico Asignado:");
        String idTecnico = sc.nextLine();

        System.out.println("Observaciones (opcional):");
        String observaciones = sc.nextLine();

        try {
            OrdenTrabajo orden = controlOrdenes.crearOrden(
                    idOrden,
                    placa,
                    idTecnico,
                    observaciones
            );
            ConsolaUtils.imprimirExito("Orden registrada exitosamente", orden);
        } catch (AppException e) {
            ConsolaUtils.imprimirError(e.getMessage());
        }
    }
}