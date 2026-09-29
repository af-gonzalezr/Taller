package co.edu.uptcsoft.taller.vista.cli.registros;

import java.util.Scanner;

public class RegistrosCLI {
    private final Scanner sc;
    private final RegistrarClienteCLI registrarClienteCLI;
    private final RegistrarTecnicoCLI registrarTecnicoCLI;
    private final RegistrarVehiculoCLI registrarVehiculoCLI;

    public RegistrosCLI(Scanner sc,
                        RegistrarClienteCLI registrarClienteCLI,
                        RegistrarTecnicoCLI registrarTecnicoCLI,
                        RegistrarVehiculoCLI registrarVehiculoCLI) {
        this.sc = sc;
        this.registrarClienteCLI = registrarClienteCLI;
        this.registrarTecnicoCLI = registrarTecnicoCLI;
        this.registrarVehiculoCLI = registrarVehiculoCLI;
    }

    public void execute() {
        boolean salir = false;
        while (!salir) {
            System.out.println("MENU DE REGISTROS");
            System.out.println("ELIJA UNA OPCION");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Registrar Técnico");
            System.out.println("3. Registrar Vehículo");
            System.out.println("4. volver");

            String seleccion = sc.nextLine().trim();
            switch (seleccion) {
                case "1" -> registrarClienteCLI.execute();
                case "2" -> registrarTecnicoCLI.execute();
                case "3" -> registrarVehiculoCLI.execute();
                case "4" -> salir = true;
                default -> System.out.println("Opción no valida");
            }
        }
    }
}