package co.edu.uptcsoft.taller.vista.cli.registros;

import co.edu.uptcsoft.taller.control.ControlRegistros;
import co.edu.uptcsoft.taller.vista.cli.RespuestaCLI;

import java.util.Scanner;

public class RegistrarClienteCLI {

    private final Scanner sc;
    private final ControlRegistros controlRegistros;

    public RegistrarClienteCLI(Scanner sc, ControlRegistros controlRegistros) {
        this.sc = sc;
        this.controlRegistros = controlRegistros;
    }

    public void execute() {
        System.out.println("MENU REGISTRO CLIENTES");
        System.out.println("INGRESE LOS DATOS PARA REGISTRAR UN NUEVO CLIENTE");

        System.out.println("Identificación");
        String idCliente = sc.nextLine();

        System.out.println("Nombre:");
        String nombre = sc.nextLine();

        System.out.println("Teléfono:");
        String telefono = sc.nextLine();

        System.out.println("Correo Electrónico:");
        String email = sc.nextLine();

        RespuestaCLI.mostrarRespuesta(controlRegistros
                .crearCliente(idCliente,
                        nombre,
                        telefono,
                        email));
    }



}
