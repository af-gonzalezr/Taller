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
        ListarVehiculos listarVehiculos = new ListarVehiculosImpl(vehiculoRepository);

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
        OrdenesFechaIngreso ordenesFechaIngreso = new OrdenesFechaIngresoImpl(ordenRepository);
        CalcularTotalOrdenes calcularTotalOrdenes = new CalcularTotalOrdenesImpl();

        ClienteControllerImpl clienteController = new ClienteControllerImpl(
                registrarCliente, buscarCliente, listarClientes);
        TecnicoControllerImpl tecnicoController = new TecnicoControllerImpl(
                registrarTecnico, buscarTecnico, listarTecnicos);
        VehiculoControllerImpl vehiculoController = new VehiculoControllerImpl(
                registrarAuto, registrarMoto, registrarCamion, buscarVehiculo, vehiculosCliente,
                listarVehiculos);
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
                ordenesVehiculo,
                ordenesFechaIngreso,
                calcularTotalOrdenes);

        clienteController.registrar("101010", "Carlos Perez", "3101234567", "carlos@gmail.com");
        clienteController.registrar("202020", "Ana Gomez", "3209876543", "ana@gmail.com");
        clienteController.registrar("303030", "Luis Martinez", "3157654321", "luis@gmail.com");
        clienteController.registrar("404040", "Sofia Ramirez", "3012345678", "sofia@gmail.com");
        clienteController.registrar("505050", "Diego Castro", "3178901234", "diego@gmail.com");
        clienteController.registrar("606060", "Valentina Lopez", "3123456789", "valentina@gmail.com");

        tecnicoController.registrar("808080", "Roberto Silva", "Electricidad");
        tecnicoController.registrar("909090", "Martha Ruiz", "Mecanica General");
        tecnicoController.registrar("707070", "Andres Torres", "Suspension");
        tecnicoController.registrar("606161", "Camila Rojas", "Inyeccion Electronica");
        tecnicoController.registrar("515151", "Julian Medina", "Transmisiones");
        tecnicoController.registrar("424242", "Laura Vargas", "Aire Acondicionado");

        vehiculoController.registrarAuto("ABC123", "Toyota", "2020", "101010", "4");
        vehiculoController.registrarMoto("XYZ987", "Yamaha", "2022", "202020", "250");
        vehiculoController.registrarCamion("TRK456", "Volvo", "2019", "101010", "12.5");
        vehiculoController.registrarAuto("KLM852", "Mazda", "2023", "303030", "5");
        vehiculoController.registrarMoto("MOT321", "Honda", "2021", "404040", "600");
        vehiculoController.registrarCamion("CAM741", "Chevrolet", "2020", "505050", "8");
        vehiculoController.registrarAuto("SUV963", "Kia", "2024", "606060", "5");
        vehiculoController.registrarMoto("END147", "Suzuki", "2020", "303030", "450");
        vehiculoController.registrarCamion("FUE258", "Ford", "2018", "404040", "15");

        ordenController.crear("ORD-1",
                LocalDateTime.now().minusDays(12).withHour(9).withMinute(30).withSecond(0).toString(),
                "ABC123", "808080", "Falla intermitente en el sistema eléctrico");
        ordenController.agregarTrabajo("ORD-1", "Diagnóstico eléctrico", "85000");
        ordenController.agregarTrabajo("ORD-1", "Cambio de batería", "180000");
        ordenController.finalizar("ORD-1", "Sistema eléctrico probado y batería reemplazada");

        ordenController.crear("ORD-2",
                LocalDateTime.now().minusDays(9).withHour(10).withMinute(15).withSecond(0).toString(),
                "XYZ987", "909090", "Mantenimiento preventivo de 10.000 km");
        ordenController.agregarTrabajo("ORD-2", "Cambio de aceite", "95000");
        ordenController.agregarTrabajo("ORD-2", "Cambio de filtros", "65000");

        ordenController.crear("ORD-3",
                LocalDateTime.now().minusDays(7).withHour(14).withMinute(45).withSecond(0).toString(),
                "TRK456", "707070", "Revisión de suspensión y dirección");
        ordenController.agregarTrabajo("ORD-3", "Alineación y balanceo", "140000");
        ordenController.agregarTrabajo("ORD-3", "Cambio de amortiguadores", "420000");

        ordenController.crear("ORD-4",
                LocalDateTime.now().minusDays(5).withHour(8).withMinute(20).withSecond(0).toString(),
                "KLM852", "606161", "Vehículo presenta pérdida de potencia");
        ordenController.agregarTrabajo("ORD-4", "Escaneo de computadora", "70000");
        ordenController.agregarTrabajo("ORD-4", "Cambio de bujías", "110000");
        ordenController.finalizar("ORD-4", "Sistema de inyección ajustado y prueba de carretera aprobada");

        ordenController.crear("ORD-5",
                LocalDateTime.now().minusDays(3).withHour(11).withMinute(10).withSecond(0).toString(),
                "MOT321", "909090", "Mantenimiento general y revisión de frenos");
        ordenController.agregarTrabajo("ORD-5", "Cambio de pastillas de freno", "160000");

        ordenController.crear("ORD-6",
                LocalDateTime.now().minusDays(2).withHour(16).withMinute(0).withSecond(0).toString(),
                "CAM741", "515151", "Ruido en la caja de cambios");
        ordenController.agregarTrabajo("ORD-6", "Diagnóstico de transmisión", "120000");
        ordenController.cancelar("ORD-6");

        ordenController.crear("ORD-7",
                LocalDateTime.now().minusDays(1).withHour(13).withMinute(25).withSecond(0).toString(),
                "SUV963", "424242", "El aire acondicionado no enfría");
        ordenController.agregarTrabajo("ORD-7", "Recarga de refrigerante", "150000");

        ordenController.crear("ORD-8",
                LocalDateTime.now().withHour(7).withMinute(50).withSecond(0).toString(),
                "END147", "707070", "Inspección de suspensión tras golpe");
        ordenController.agregarTrabajo("ORD-8", "Inspección general", "50000");

        ordenController.crear("ORD-9",
                LocalDateTime.now().withHour(15).withMinute(30).withSecond(0).toString(),
                "FUE258", "909090", "Cambio de aceite y revisión preventiva");
        ordenController.agregarTrabajo("ORD-9", "Cambio de aceite", "120000");
        ordenController.agregarTrabajo("ORD-9", "Revisión de frenos", "80000");

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