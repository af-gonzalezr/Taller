package co.edu.uptcsoft.taller.vista.cli.consultas;

import co.edu.uptcsoft.taller.control.ControlConsultas;
import co.edu.uptcsoft.taller.vista.cli.RespuestaCLI;

import java.util.Scanner;

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
                case "2" -> RespuestaCLI.mostrarRespuesta(controlConsultas.consultarTodasLasOrdenes());
                case "3" -> consultarOrdenesClienteCLI.execute();
                case "4" -> RespuestaCLI.mostrarRespuesta(controlConsultas.consultarOrdenesEnProceso());
                case "5" -> RespuestaCLI.mostrarRespuesta(controlConsultas.consultarOrdenesFinalizadas());
                case "6" -> RespuestaCLI.mostrarRespuesta(controlConsultas.consultarOrdenesCanceladas());
                case "7" -> salir = true;
                default -> System.out.println("Opción no valida");
            }
        }
    }
}