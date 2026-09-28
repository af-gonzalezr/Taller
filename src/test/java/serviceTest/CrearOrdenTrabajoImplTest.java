package serviceTest;

import co.edu.uptcsoft.taller.model.Automovil;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.OrdenRepo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.repository.impl.OrdenRepository;
import co.edu.uptcsoft.taller.repository.impl.TecnicoRepository;
import co.edu.uptcsoft.taller.repository.impl.VehiculoRepository;
import co.edu.uptcsoft.taller.service.CrearOrdenTrabajo;
import co.edu.uptcsoft.taller.service.Respuesta;
import co.edu.uptcsoft.taller.service.impl.CrearOrdenTrabajoImpl;

import java.time.LocalDateTime;

public class CrearOrdenTrabajoImplTest {

    public static void main(String[] args) {
        // Setup de repositorios
        CrearOrdenTrabajo servicio = setupDeRepositorios();

        LocalDateTime ahora = LocalDateTime.now();

        // --- CASOS DE PRUEBA PARA REVISAR EN DEBUG ---

        // 1. Registro exitoso estándar
        Respuesta<OrdenTrabajo> respuesta1 = servicio.execute("ORD-001", ahora, "ABC-123", "TEC-01", "Cambio de aceite");

        // 2. Error: ID de Orden ya existente
        Respuesta<OrdenTrabajo> respuesta2 = servicio.execute("ORD-001", ahora, "DEF-456", "TEC-01", "Revisión de frenos");

        // 3. Error: Técnico no existe
        Respuesta<OrdenTrabajo> respuesta3 = servicio.execute("ORD-002", ahora, "ABC-123", "TEC-99", "Alineación y balanceo");

        // 4. Error: Vehículo no existe
        Respuesta<OrdenTrabajo> respuesta4 = servicio.execute("ORD-003", ahora, "XYZ-999", "TEC-01", "Fallo en motor");

        // 5. Éxito: Segunda orden válida para diferente vehículo
        Respuesta<OrdenTrabajo> respuesta5 = servicio.execute("ORD-004", ahora, "DEF-456", "TEC-01", "Mantenimiento general");

        // 6. Validación: Intento con campos vacíos/inexistentes en idTecnico e idVehiculo a la vez (falla primero en técnico por el flujo)
        Respuesta<OrdenTrabajo> respuesta6 = servicio.execute("ORD-005", ahora, "", "", "Sin datos");

        // Punto de parada (Breakpoint aquí)
        boolean fin = true;
    }

    private static CrearOrdenTrabajo setupDeRepositorios() {
        Repository<Vehiculo> vehiculoRepo = new VehiculoRepository();
        Repository<Tecnico> tecnicoRepo = new TecnicoRepository();
        OrdenRepo ordenTrabajoRepo = new OrdenRepository();

        // Carga de datos base (Cliente, Vehículo y Técnico)
        Cliente cliente = new Cliente("12345", "Jorge", "911", "jorge@email.com");
        Vehiculo vehiculo1 = new Automovil("ABC-123", "Toyota", 2023, cliente, 4);
        Vehiculo vehiculo2 = new Automovil("DEF-456", "Renault", 2021, cliente, 5);
        Tecnico tecnico1 = new Tecnico("TEC-01", "Carlos Pérez", "Mecánica General");

        vehiculoRepo.guardar(vehiculo1);
        vehiculoRepo.guardar(vehiculo2);
        tecnicoRepo.guardar(tecnico1);

        // Instancia del servicio
        CrearOrdenTrabajo servicio = new CrearOrdenTrabajoImpl(vehiculoRepo, tecnicoRepo, ordenTrabajoRepo);
        return servicio;
    }
}