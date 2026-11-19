package co.edu.uptcsoft.taller.vista.cli.gestion;

import java.util.Scanner;

public class GestionCLI {
    private final Scanner sc;
    private final CrearOrdenCLI crearOrdenCLI;
    private final FinalizarOrdenCLI finalizarOrdenCLI;
    private final CancelarOrdenCLI cancelarOrdenCLI;
    private final AgregarServicioCLI agregarServicioCLI;

    public GestionCLI(Scanner sc,
                      CrearOrdenCLI crearOrdenCLI,
                      FinalizarOrdenCLI finalizarOrdenCLI,
                      CancelarOrdenCLI cancelarOrdenCLI,
                      AgregarServicioCLI agregarServicioCLI) {
        this.sc = sc;
        this.crearOrdenCLI = crearOrdenCLI;
        this.finalizarOrdenCLI = finalizarOrdenCLI;
        this.cancelarOrdenCLI = cancelarOrdenCLI;
        this.agregarServicioCLI = agregarServicioCLI;
    }

    public void execute() {
        boolean salir = false;
        while (!salir) {
            System.out.println("MENU DE ORDENES DE TRABAJO");
            System.out.println("ELIJA UNA OPCIÓN");
            System.out.println("1. Crear Orden de Trabajo");
            System.out.println("2. Agregar Servicio/Trabajo a Orden");
            System.out.println("3. Finalizar Orden");
            System.out.println("4. Cancelar Orden");
            System.out.println("5. volver");

            String seleccion = sc.nextLine().trim();
            switch (seleccion) {
                case "1" -> crearOrdenCLI.execute();
                case "2" -> agregarServicioCLI.execute();
                case "3" -> finalizarOrdenCLI.execute();
                case "4" -> cancelarOrdenCLI.execute();
                case "5" -> salir = true;
                default -> System.out.println("Opción no valida");
            }
        }
    }
}