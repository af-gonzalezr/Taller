package co.edu.uptcsoft.taller;

import co.edu.uptcsoft.taller.control.ControlRegistros;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.repository.impl.ClienteRepository;
import co.edu.uptcsoft.taller.repository.impl.TecnicoRepository;
import co.edu.uptcsoft.taller.repository.impl.VehiculoRepository;
import co.edu.uptcsoft.taller.service.RegistrarAuto;
import co.edu.uptcsoft.taller.service.RegistrarCamion;
import co.edu.uptcsoft.taller.service.RegistrarCliente;
import co.edu.uptcsoft.taller.service.RegistrarMoto;
import co.edu.uptcsoft.taller.service.RegistrarTecnico;
import co.edu.uptcsoft.taller.service.impl.RegistrarAutoImpl;
import co.edu.uptcsoft.taller.service.impl.RegistrarClienteImpl;
import co.edu.uptcsoft.taller.service.impl.RegistrarTecnicoImpl;
import co.edu.uptcsoft.taller.vista.VistaMain;
import co.edu.uptcsoft.taller.vista.cli.registros.MenuRegistrosCLI;
import co.edu.uptcsoft.taller.vista.cli.MenuPrincipalCLI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Scanner;

public class Main  {
    static void main() {
        Repository<Tecnico> tecRepository = new TecnicoRepository();
        Repository<Cliente> clienteRepository = new ClienteRepository();
        RegistrarCliente registrarCliente = new RegistrarClienteImpl(clienteRepository);
        RegistrarTecnico registrarTecnico = new RegistrarTecnicoImpl(tecRepository);
        Repository<Vehiculo> vehiculoRepository = new VehiculoRepository();
        

  
        
        RegistrarCamion registarCamion;
        RegistrarAuto registrarAuto;
        RegistrarMoto registarMoto;
        ControlRegistros controlRegistros = new ControlRegistros(registrarCliente, registrarTecnico, registrarAuto, registarCamion, registarMoto);
       

        Scanner sc = new Scanner(System.in);
        MenuPrincipalCLI menuPrincipalCLI = new MenuPrincipalCLI(sc, null);
        MenuRegistrosCLI menuRegistrosCLI = new MenuRegistrosCLI(sc, controlRegistros);

        menuPrincipalCLI.mostrarMenu();
        
    }

}