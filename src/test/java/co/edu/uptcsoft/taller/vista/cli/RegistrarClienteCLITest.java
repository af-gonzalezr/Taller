package co.edu.uptcsoft.taller.vista.cli;

import co.edu.uptcsoft.taller.control.ControlRegistros;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.repository.impl.ClienteRepository;
import co.edu.uptcsoft.taller.service.RegistrarCliente;
import co.edu.uptcsoft.taller.service.impl.RegistrarClienteImpl;
import co.edu.uptcsoft.taller.vista.cli.registros.RegistrarClienteCLI;

import java.util.Scanner;

class RegistrarClienteCLITest {
    static void main() {
        Repository<Cliente> clienteRepository = new ClienteRepository();
        RegistrarCliente registrarCliente = new RegistrarClienteImpl(clienteRepository);
        ControlRegistros controlRegistros = new ControlRegistros(registrarCliente);
        Scanner sc = new Scanner(System.in);
        RegistrarClienteCLI registrarClienteCLI = new RegistrarClienteCLI(sc,controlRegistros);
        registrarCliente.execute("123","Andres","3143129803","a@email.com");
        registrarClienteCLI.execute();
        boolean fin = true;
    }
}