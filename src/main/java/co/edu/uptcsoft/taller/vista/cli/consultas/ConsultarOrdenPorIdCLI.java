package co.edu.uptcsoft.taller.vista.cli.consultas;

import co.edu.uptcsoft.taller.control.ControlConsultas;
import co.edu.uptcsoft.taller.vista.cli.RespuestaCLI;

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

        RespuestaCLI.mostrarRespuesta(controlConsultas.consultarOrdenPorId(idOrden));
    }
}