package co.edu.uptcsoft.taller.vista.cli.consultas;

import co.edu.uptcsoft.taller.controller.old.ControlConsultas;
import co.edu.uptcsoft.taller.exception.TallerException;
import co.edu.uptcsoft.taller.vista.cli.ConsolaUtils;

import java.util.Scanner;
import java.util.List;
import java.util.function.Supplier;

public class ConsultasOrdenesCLI {

    private final Scanner sc;
    private final ControlConsultas controlConsultas;
    private final ConsultarOrdenPorIdCLI consultarOrdenPorIdCLI;
    private final ConsultarOrdenesClienteCLI consultarOrdenesClienteCLI;

    public ConsultasOrdenesCLI(Scanner sc,
                               ControlConsultas controlConsultas,
                               ConsultarOrdenPorIdCLI consultarOrdenPorIdCLI,
                               ConsultarOrdenesClienteCLI consultarOrdenesClienteCLI) {
        this.sc = sc;
        this.controlConsultas = controlConsultas;
        this.consultarOrdenPorIdCLI = consultarOrdenPorIdCLI;
        this.consultarOrdenesClienteCLI = consultarOrdenesClienteCLI;
    }

    public void execute() {
        boolean salir = false;
        while (!salir) {
            System.out.println("MENU CONSULTAS DE ORDENES");
            System.out.println("ELIJA UNA OPCION");
            System.out.println("1. Consultar una orden por ID");
            System.out.println("2. Consultar todas las órdenes");
            System.out.println("3. Consultar órdenes de un cliente");
            System.out.println("4. Consultar órdenes en proceso");
            System.out.println("5. Consultar órdenes finalizadas");
            System.out.println("6. Consultar órdenes canceladas");
            System.out.println("7. volver");

            String seleccion = sc.nextLine().trim();
            switch (seleccion) {
                case "1" -> consultarOrdenPorIdCLI.execute();
                case "2" -> consultarTodas();
                case "3" -> consultarOrdenesClienteCLI.execute();
                case "4" -> consultarEstado("Ordenes en proceso", controlConsultas::consultarOrdenesEnProceso);
                case "5" -> consultarEstado("Ordenes finalizadas", controlConsultas::consultarOrdenesFinalizadas);
                case "6" -> consultarEstado("Ordenes canceladas", controlConsultas::consultarOrdenesCanceladas);
                case "7" -> salir = true;
                default -> System.out.println("Opción no valida");
            }

        }
    }

    private void consultarTodas() {
        try {
            ConsolaUtils.imprimirExito("Ordenes registradas", controlConsultas.consultarTodasLasOrdenes());
        } catch (TallerException e) {
            ConsolaUtils.imprimirError(e.getMessage());
        }
    }

    private void consultarEstado(String mensaje, Supplier<List<co.edu.uptcsoft.taller.model.OrdenTrabajo>> consulta) {
        try {
            ConsolaUtils.imprimirExito(mensaje, consulta.get());
        } catch (TallerException e) {
            ConsolaUtils.imprimirError(e.getMessage());
        }
    }
}