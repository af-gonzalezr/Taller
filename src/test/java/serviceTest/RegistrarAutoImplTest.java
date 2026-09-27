package serviceTest;

import co.edu.uptcsoft.taller.model.Automovil;
import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.repository.impl.ClienteRepository;
import co.edu.uptcsoft.taller.repository.impl.VehiculoRepository;
import co.edu.uptcsoft.taller.service.RegistrarAuto;
import co.edu.uptcsoft.taller.service.Respuesta;
import co.edu.uptcsoft.taller.service.impl.RegistrarAutoImpl;

public class RegistrarAutoImplTest {

    public static void main(String[] args) {
        // Setup de repositorios y datos iniciales
        Repository<Cliente> clienteRepo = new ClienteRepository();
        Repository<Vehiculo> vehiculoRepo = new VehiculoRepository();

        Cliente cliente1 = new Cliente("12345", "Jorge", "911", "jorge@email.com");
        Cliente cliente2 = new Cliente("67890", "Maria", "811", "maria@email.com");
        clienteRepo.guardar(cliente1);
        clienteRepo.guardar(cliente2);

        RegistrarAuto servicio = new RegistrarAutoImpl(clienteRepo, vehiculoRepo);

        // --- CASOS DE PRUEBA PARA REVISAR EN DEBUG ---

        // 1. Registro exitoso estándar
        Respuesta<Automovil> respuesta1 = servicio.execute("ABC-123", "Toyota", 2023, "12345", 4);

        // 2. Cliente inexistente
        Respuesta<Automovil> respuesta2 = servicio.execute("XYZ-999", "Mazda", 2022, "99999", 2);

        // 3. Placa duplicada
        Respuesta<Automovil> respuesta3 = servicio.execute("ABC-123", "Chevrolet", 2020, "12345", 4);

        // 4. Mismo cliente registrando un segundo auto
        Respuesta<Automovil> respuesta4 = servicio.execute("DEF-456", "Renault", 2021, "12345", 5);

        // 5. Diferente cliente registrado exitosamente
        Respuesta<Automovil> respuesta5 = servicio.execute("GHI-789", "Nissan", 2024, "67890", 2);

        // 6. Prueba con minúsculas en la placa ("abc-123")
        Respuesta<Automovil> respuesta6 = servicio.execute("abc-123", "Ford", 2019, "12345", 4);

        // 7. ID de cliente vacío
        Respuesta<Automovil> respuesta7 = servicio.execute("JKL-012", "Kia", 2022, "", 4);

        // Punto de parada (Breakpoint aquí)
        boolean fin = true;
    }
}