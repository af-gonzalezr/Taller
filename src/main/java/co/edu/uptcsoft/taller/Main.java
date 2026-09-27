package co.edu.uptcsoft.taller.presentation;

import co.edu.uptcsoft.taller.control.ControlRegistros;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.repository.impl.ClienteRepository;
import co.edu.uptcsoft.taller.service.RegistrarCliente;
import co.edu.uptcsoft.taller.service.impl.RegistrarClienteImpl;
import co.edu.uptcsoft.taller.vista.VistaMain;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class Main  {
    static void main() {
        Repository<Cliente> clienteRepository = new ClienteRepository();
        RegistrarCliente registrarCliente = new RegistrarClienteImpl(clienteRepository);
        ControlRegistros controlRegistros = new ControlRegistros(registrarCliente);
        VistaMain vista = new VistaMain(controlRegistros);
        vista.mostrarMain();
    }

}