package co.edu.uptcsoft.taller.presentation;

import co.edu.uptcsoft.taller.control.ControlOrdenes;
import co.edu.uptcsoft.taller.control.ControlRegistros;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.OrdenRepo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.repository.impl.ClienteRepository;
import co.edu.uptcsoft.taller.repository.impl.OrdenRepository;
import co.edu.uptcsoft.taller.repository.impl.TecnicoRepository;
import co.edu.uptcsoft.taller.repository.impl.VehiculoRepository;
import co.edu.uptcsoft.taller.service.CancelarOrden;
import co.edu.uptcsoft.taller.service.CrearOrdenTrabajo;
import co.edu.uptcsoft.taller.service.FinalizarOrden;
import co.edu.uptcsoft.taller.service.RegistrarCliente;
import co.edu.uptcsoft.taller.service.impl.CancelarOrdenImpl;
import co.edu.uptcsoft.taller.service.impl.CrearOrdenTrabajoImpl;
import co.edu.uptcsoft.taller.service.impl.FinalizarOrdenImpl;
import co.edu.uptcsoft.taller.service.impl.RegistrarClienteImpl;
import co.edu.uptcsoft.taller.vista.VistaMain;

public class Main  {
    static void main() {
        Repository<Cliente> clienteRepository = new ClienteRepository();
        Repository<Vehiculo> vehiculoRepository = new VehiculoRepository();
        Repository<Tecnico> tecnicoRepository = new TecnicoRepository();
        OrdenRepo ordenRepository = new OrdenRepository();

        RegistrarCliente registrarCliente = new RegistrarClienteImpl(clienteRepository);
        ControlRegistros controlRegistros = new ControlRegistros(registrarCliente);

        CrearOrdenTrabajo crearOrden = new CrearOrdenTrabajoImpl(vehiculoRepository, tecnicoRepository, ordenRepository);
        FinalizarOrden finalizarOrden = new FinalizarOrdenImpl(ordenRepository);
        CancelarOrden cancelarOrden = new CancelarOrdenImpl(ordenRepository);
        ControlOrdenes controlOrdenes = new ControlOrdenes(crearOrden, finalizarOrden, cancelarOrden);

        VistaMain vista = new VistaMain(controlRegistros, controlOrdenes);
        vista.mostrarMain();
    }

}