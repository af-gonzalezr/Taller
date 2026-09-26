package co.edu.uptcsoft.taller;

    import co.edu.uptcsoft.taller.model.Automovil;
    import co.edu.uptcsoft.taller.model.Cliente;
    import co.edu.uptcsoft.taller.model.Tecnico;

public class Main {
        public static void main(String[] args) {
            TallerServiceImpl servicio = new TallerServiceImpl(
                    new InMemoryClienteRepository(),
                    new InMemoryVehiculoRepository(),
                    new InMemoryTecnicoRepository(),
                    new InMemoryOrdenRepository());

            Cliente cliente = new Cliente("123456789", "Ana Gomez", "3001234567", "ana@example.com");
            Tecnico tecnico = new Tecnico("TEC-001", "Carlos Perez", "Mecanica general");
            Automovil automovil = new Automovil("ABC123", "Toyota", 2020, cliente, 4);

            servicio.registrarCliente(cliente);
            servicio.registrarTecnico(tecnico);
            servicio.registrarVehiculo(automovil);

            String idOrden = "ORD-0001";
            servicio.crearOrdenTrabajo(idOrden, automovil.getPlaca(), tecnico.getIdTecnico(),
                    "El vehiculo presenta ruido en el motor.");
            servicio.agregarTrabajoAOrden(idOrden, "Cambio de aceite", 45000);
            servicio.agregarTrabajoAOrden(idOrden, "Alineacion", 30000);
            servicio.finalizarOrdenTrabajo(idOrden, "Vehiculo probado y entregado en buen estado.");

            System.out.println(servicio.generarFacturaString(idOrden));
        }
    }
