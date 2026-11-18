package co.edu.uptcsoft.taller.vista.cli.registros;

import co.edu.uptcsoft.taller.controller.old.ControlRegistros;
import co.edu.uptcsoft.taller.exception.TallerException;
import co.edu.uptcsoft.taller.vista.cli.ConsolaUtils;

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

        try {
            ConsolaUtils.imprimirExito("Cliente registrado exitosamente",
                    controlRegistros.crearCliente(idCliente, nombre, telefono, email));
        } catch (TallerException e) {
            ConsolaUtils.imprimirError(e.getMessage());
        }
    }



}
