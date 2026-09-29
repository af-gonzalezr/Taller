package co.edu.uptcsoft.taller.vista.cli.registros;


import co.edu.uptcsoft.taller.control.ControlRegistros;
import java.util.Scanner;

public class MenuRegistrosCLI {

    private final Scanner sc;
    private final RegistrarClienteCLI registrarClienteCLI;
    private final RegistrarTecnicoCLI registrarTecnicoCLI;
    private final RegistrarAutoCLI registrarAutoCLI;
    private final RegistrarCamionCLI registrarCamionCLI;
    private final RegistrarMotoCLI registrarMotoCLI;
   
    


    public MenuRegistrosCLI(Scanner sc, RegistrarClienteCLI registrarClienteCLI,
            RegistrarTecnicoCLI registrarTecnicoCLI, RegistrarAutoCLI registrarAutoCLI,
            RegistrarCamionCLI registrarCamionCLI, RegistrarMotoCLI registrarMotoCLI) {
        this.sc = sc;
        this.registrarClienteCLI = registrarClienteCLI;
        this.registrarTecnicoCLI = registrarTecnicoCLI;
        this.registrarAutoCLI = registrarAutoCLI;
        this.registrarCamionCLI = registrarCamionCLI;
        this.registrarMotoCLI = registrarMotoCLI;
    }




    public void mostrarMenu() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\nMENU DE REGISTROS");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Registrar Técnico");
            System.out.println("3. Registrar Automovil");
            System.out.println("4. Registrar Camion ");
            System.out.println("5. Registrar Moto");
            System.out.println("5. Volver");
            System.out.println("Seleccione una opción:");

            int opcion;
            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida, escriba un número.");
                continue;
            }

            switch (opcion) {
                case 1 -> registrarClienteCLI.execute();
                case 2 -> registrarTecnicoCLI.execute();
                case 3 -> registrarAutoCLI.execute();
                case 4 -> registrarCamionCLI.execute();
                case 5 -> registrarMotoCLI.execute();
                case 6 -> salir = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }
}