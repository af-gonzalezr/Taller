package co.edu.uptcsoft.taller.vista;

import co.edu.uptcsoft.taller.vista.cli.consultas.ConsultarOrdenesClienteCLI;
import co.edu.uptcsoft.taller.vista.cli.consultas.ConsultasOrdenesCLI;
import co.edu.uptcsoft.taller.vista.cli.gestion.GestionCLI;
import co.edu.uptcsoft.taller.vista.cli.registros.RegistrosCLI;

import java.util.Scanner;

public class VistaMain {

    private final Scanner sc;
    private final RegistrosCLI menuRegistros;
    private final GestionCLI menuGestion;
    private final ConsultasOrdenesCLI menuConsultas;


    public VistaMain(Scanner sc, RegistrosCLI menuRegistros, GestionCLI menuGestion, ConsultasOrdenesCLI menuConsultas) {
        this.sc = sc;
        this.menuRegistros = menuRegistros;
        this.menuGestion = menuGestion;
        this.menuConsultas = menuConsultas;
    }


    public void mostrarMain(){
        String[] opciones = {
                "1. Registros",
                "2. Gestion de Ordenes",
                "3. Consultas",
                "4. Salir"
        };
        boolean salir = false;
        while (!salir){
            System.out.println("MENU PRINCIPAL");
            for(String op:opciones){
                System.out.println("- "+op);
            }
            String seleccion =sc.nextLine().trim();
            switch (seleccion){
                case "1"-> menuRegistros.execute();
                case "2" -> menuGestion.execute();
                case "3" -> menuConsultas.execute();
                case "4" -> salir =true;
                default -> System.out.println("Opción no valida");
            }
        }
    }
}