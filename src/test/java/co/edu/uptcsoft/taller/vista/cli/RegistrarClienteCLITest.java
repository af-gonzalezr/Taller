package co.edu.uptcsoft.taller.vista.cli;

import co.edu.uptcsoft.taller.control.ControlRegistros;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.repository.impl.ClienteRepository;
import co.edu.uptcsoft.taller.repository.impl.TecnicoRepository;
import co.edu.uptcsoft.taller.repository.impl.VehiculoRepository;
import co.edu.uptcsoft.taller.service.RegistrarAuto;
import co.edu.uptcsoft.taller.service.RegistrarCliente;
import co.edu.uptcsoft.taller.service.RegistrarTecnico;
import co.edu.uptcsoft.taller.service.impl.RegistrarAutoImpl;
import co.edu.uptcsoft.taller.service.impl.RegistrarClienteImpl;
import co.edu.uptcsoft.taller.service.impl.RegistrarTecnicoImpl;
import co.edu.uptcsoft.taller.vista.cli.registros.RegistrarClienteCLI;

import java.util.Scanner;

class RegistrarClienteCLITest {
    static void main() {
        Repository<Cliente> clienteRepository = new ClienteRepository();
        Repository<Tecnico> tecnicoRepository = new TecnicoRepository();
        Repository<Vehiculo> vehiculoRepository = new VehiculoRepository();

        RegistrarCliente registrarCliente = new RegistrarClienteImpl(clienteRepository);
        RegistrarTecnico registrarTecnico = new RegistrarTecnicoImpl(tecnicoRepository);
        RegistrarAuto registrarAuto = new RegistrarAutoImpl(clienteRepository, vehiculoRepository);

        ControlRegistros controlRegistros =
                new ControlRegistros(registrarCliente, registrarTecnico, registrarAuto, null, null);

        Scanner sc = new Scanner(System.in);
        RegistrarClienteCLI registrarClienteCLI = new RegistrarClienteCLI(sc, controlRegistros);
        registrarCliente.execute("123", "Andres", "3143129803", "a@email.com");
        registrarClienteCLI.execute();
        boolean fin = true;
    }
}