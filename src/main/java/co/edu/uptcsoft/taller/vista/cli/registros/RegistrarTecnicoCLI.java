package co.edu.uptcsoft.taller.vista.cli.registros;
import co.edu.uptcsoft.taller.control.ControlRegistros;
import co.edu.uptcsoft.taller.vista.cli.RespuestaCLI;

import java.util.Scanner;

public class RegistrarTecnicoCLI {
 private final Scanner sc;
 private final ControlRegistros controlRegistros;
 public RegistrarTecnicoCLI(Scanner sc, ControlRegistros controlRegistros) {
    this.sc = sc;
    this.controlRegistros = controlRegistros;
 }
 
 public void execute() {
        System.out.println("MENU REGISTRO TECNICOS");
        System.out.println("INGRESE LOS DATOS PARA REGISTRAR UN NUEVO TECNICO");

        System.out.println("Identificación");
        String idTecnico = sc.nextLine();

        System.out.println("Nombre:");
        String nombre = sc.nextLine();

        System.out.println("Especialidad:");
        String especialidad = sc.nextLine();

        RespuestaCLI.mostrarRespuesta(controlRegistros
                .crearTecnico(idTecnico,
                        nombre,
                        especialidad));
    }
}
