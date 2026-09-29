package co.edu.uptcsoft.taller.vista.cli.consultas;

import co.edu.uptcsoft.taller.control.ControlConsultas;
import co.edu.uptcsoft.taller.vista.cli.RespuestaCLI;

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

        RespuestaCLI.mostrarRespuesta(controlConsultas.consultarOrdenesCliente(idCliente));
    }
}