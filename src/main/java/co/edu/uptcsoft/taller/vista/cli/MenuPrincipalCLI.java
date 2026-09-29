package co.edu.uptcsoft.taller.vista.cli;
import co.edu.uptcsoft.taller.vista.cli.registros.MenuRegistrosCLI;
import java.util.Scanner;

public class MenuPrincipalCLI{
    private final Scanner sc;
    private final MenuRegistrosCLI menuRegistrosCLI;
    public MenuPrincipalCLI(Scanner sc, MenuRegistrosCLI menuRegistrosCLI) {
        this.sc = sc;
        this.menuRegistrosCLI = menuRegistrosCLI;
    }

    public void mostrarMenu(){
        boolean salir = false;
        while (!salir) {
           System.out.println("\nSISTEMA TALLER");
            System.out.println("1. Registros");
            System.out.println("2. Gestión de Órdenes");
            System.out.println("3. Actualizar Datos");
            System.out.println("4. Consultas");
            System.out.println("5. Salir");
            System.out.println("Seleccione una opción:");

            int opcion;
            try{
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e){
                System.out.println("Opcion no valida, escriba un numero");
                continue;
            }

           switch (opcion) {
                case 1 -> menuRegistrosCLI.mostrarMenu();
                case 2, 3, 4 -> System.out.println("PrOximamente");
                case 5 -> salir = true;
                default -> System.out.println("Opción inválida.");
            }
        }
        System.out.println("Hasta luego.");
    }
}