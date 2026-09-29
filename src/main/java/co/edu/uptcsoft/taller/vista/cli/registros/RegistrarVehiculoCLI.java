package co.edu.uptcsoft.taller.vista.cli.registros;

import java.util.Scanner;

public class RegistrarVehiculoCLI {
    private final Scanner sc;
    private final RegistrarAutomovilCLI registrarAutomovilCLI;
    private final RegistrarMotocicletaCLI registrarMotocicletaCLI;
    private final RegistrarCamionCLI registrarCamionCLI;

    public RegistrarVehiculoCLI(Scanner sc,
                                RegistrarAutomovilCLI registrarAutomovilCLI,
                                RegistrarMotocicletaCLI registrarMotocicletaCLI,
                                RegistrarCamionCLI registrarCamionCLI) {
        this.sc = sc;
        this.registrarAutomovilCLI = registrarAutomovilCLI;
        this.registrarMotocicletaCLI = registrarMotocicletaCLI;
        this.registrarCamionCLI = registrarCamionCLI;
    }

    public void execute() {
        boolean salir = false;
        while (!salir) {
            System.out.println("TIPO DE VEHICULO A REGISTRAR");
            System.out.println("1. Automóvil");
            System.out.println("2. Motocicleta");
            System.out.println("3. Camión");
            System.out.println("4. Volver al menú de registros");

            String opcion = sc.nextLine().trim();
            switch (opcion) {
                case "1" -> registrarAutomovilCLI.execute();
                case "2" -> registrarMotocicletaCLI.execute();
                case "3" -> registrarCamionCLI.execute();
                case "4" -> salir = true;
                default -> System.out.println("Opción no valida");
            }
        }
    }
}