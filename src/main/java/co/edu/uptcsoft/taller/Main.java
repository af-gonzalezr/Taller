package co.edu.uptcsoft.taller;

import co.edu.uptcsoft.taller.controller.impl.ClienteControllerImpl;
import co.edu.uptcsoft.taller.controller.impl.OrdenControllerImpl;
import co.edu.uptcsoft.taller.controller.impl.TecnicoControllerImpl;
import co.edu.uptcsoft.taller.controller.impl.VehiculoControllerImpl;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.repository.impl.ClienteRepository;
import co.edu.uptcsoft.taller.repository.impl.OrdenRepository;
import co.edu.uptcsoft.taller.repository.impl.TecnicoRepository;
import co.edu.uptcsoft.taller.repository.impl.VehiculoRepository;
import co.edu.uptcsoft.taller.service.*;
import co.edu.uptcsoft.taller.service.impl.*;
import co.edu.uptcsoft.taller.vista.gui.VentanaPrincipal;

import javax.swing.SwingUtilities;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Repository<Cliente> clienteRepository = new ClienteRepository();
        Repository<Vehiculo> vehiculoRepository = new VehiculoRepository();
        Repository<Tecnico> tecnicoRepository = new TecnicoRepository();
        Repository<OrdenTrabajo> ordenRepository = new OrdenRepository();

        RegistrarCliente registrarCliente = new RegistrarClienteImpl(clienteRepository);
        RegistrarTecnico registrarTecnico = new RegistrarTecnicoImpl(tecnicoRepository);
        RegistrarAuto registrarAuto = new RegistrarAutoImpl(clienteRepository, vehiculoRepository);
        RegistrarMoto registrarMoto = new RegistrarMotoImpl(vehiculoRepository, clienteRepository);
        RegistrarCamion registrarCamion = new RegistrarCamionImpl(clienteRepository, vehiculoRepository);
        BuscarCliente buscarCliente = new BuscarClienteImpl(clienteRepository);
        BuscarTecnico buscarTecnico = new BuscarTecnicoImpl(tecnicoRepository);
        ListarClientes listarClientes = new ListarClientesImpl(clienteRepository);
        ListarTecnicos listarTecnicos = new ListarTecnicosImpl(tecnicoRepository);
        BuscarVehiculo buscarVehiculo = new BuscarVehiculoImpl(vehiculoRepository);
        VehiculosCliente vehiculosCliente = new VehiculosClienteImpl(clienteRepository, vehiculoRepository);

        CrearOrdenTrabajo crearOrdenTrabajo = new CrearOrdenTrabajoImpl(
                vehiculoRepository, tecnicoRepository, ordenRepository);
        AgregarTrabajoAOrden agregarTrabajoAOrden = new AgregarTrabajoAOrdenImpl(ordenRepository);
        QuitarTrabajoDeOrden quitarTrabajoDeOrden = new QuitarTrabajoDeOrdenImpl(ordenRepository);
        ReasignarTecnicoOrden reasignarTecnicoOrden =
                new ReasignarTecnicoOrdenImpl(ordenRepository, tecnicoRepository);
        FinalizarOrden finalizarOrden = new FinalizarOrdenImpl(ordenRepository);
        CancelarOrden cancelarOrden = new CancelarOrdenImpl(ordenRepository);
        BuscarOrden buscarOrden = new BuscarOrdenImpl(ordenRepository);
        ListarOrdenes listarOrdenes = new ListarOrdenesImpl(ordenRepository);
        OrdenesCliente ordenesCliente = new OrdenesClienteImpl(clienteRepository, ordenRepository);
        OrdenesEstado ordenesEstado = new OrdenesEstadoImpl(ordenRepository);
        OrdenesTecnico ordenesTecnico = new OrdenesTecnicoImpl(ordenRepository, tecnicoRepository);
        OrdenesVehiculo ordenesVehiculo =
                new OrdenesVehiculoImpl(ordenRepository, vehiculoRepository);

        ClienteControllerImpl clienteController = new ClienteControllerImpl(
                registrarCliente, buscarCliente, listarClientes);
        TecnicoControllerImpl tecnicoController = new TecnicoControllerImpl(
                registrarTecnico, buscarTecnico, listarTecnicos);
        VehiculoControllerImpl vehiculoController = new VehiculoControllerImpl(
                registrarAuto, registrarMoto, registrarCamion, buscarVehiculo, vehiculosCliente);
        OrdenControllerImpl ordenController = new OrdenControllerImpl(
                crearOrdenTrabajo,
                agregarTrabajoAOrden,
                quitarTrabajoDeOrden,
                reasignarTecnicoOrden,
                finalizarOrden,
                cancelarOrden,
                buscarOrden,
                listarOrdenes,
                ordenesCliente,
                ordenesEstado,
                ordenesTecnico,
                ordenesVehiculo);

        clienteController.registrar("101010", "Carlos Perez", "3101234567", "carlos@gmail.com");
        clienteController.registrar("202020", "Ana Gomez", "3209876543", "ana@gmail.com");
        tecnicoController.registrar("808080", "Roberto Silva", "Electricidad");
        tecnicoController.registrar("909090", "Martha Ruiz", "Mecanica General");
        vehiculoController.registrarAuto("ABC123", "Toyota", "2020", "101010", "4");
        vehiculoController.registrarMoto("XYZ987", "Yamaha", "2022", "202020", "250");
        vehiculoController.registrarCamion("TRK456", "Volvo", "2019", "101010", "12.5");
        ordenController.crear("ORD-1", LocalDateTime.now().toString(),
                "ABC123", "808080", "Falla en sistema eléctrico");
        ordenController.crear("ORD-2", LocalDateTime.now().toString(),
                "XYZ987", "909090", "Mantenimiento preventivo 10k km");
        ordenController.agregarTrabajo("ORD-1", "Cambio de batería", "180000");

        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal(
                    clienteController,
                    tecnicoController,
                    vehiculoController,
                    ordenController);
            ventana.setVisible(true);
        });
    }
}