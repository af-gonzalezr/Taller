package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListarVehiculosImplTest {

    @Test
    void returnsAllVehiclesFromRepository() {
        @SuppressWarnings("unchecked")
        Repository<Vehiculo> repository = mock(Repository.class);
        List<Vehiculo> vehiculos = List.of(mock(Vehiculo.class), mock(Vehiculo.class));
        when(repository.listar()).thenReturn(vehiculos);

        assertEquals(vehiculos, new ListarVehiculosImpl(repository).execute());
    }
}
