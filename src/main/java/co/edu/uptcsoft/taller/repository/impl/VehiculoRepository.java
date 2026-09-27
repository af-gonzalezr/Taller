package co.edu.uptcsoft.taller.repository.impl;

import co.edu.uptcsoft.taller.model.Vehiculo;
import co.edu.uptcsoft.taller.repository.Repository;

import java.util.ArrayList;
import java.util.Optional;

public class VehiculoRepository implements Repository<Vehiculo> {

    private final ArrayList<Vehiculo> vehiculos;

    public VehiculoRepository() {
        vehiculos = new ArrayList<>();
    }


    @Override
    public Vehiculo guardar(Vehiculo elemento) {

        if(elemento == null){
            return null;
        }

        Optional<Vehiculo> existente = buscarPorId(elemento.getPlaca());

        if(existente.isPresent()){
            int index = vehiculos.indexOf(elemento);
            vehiculos.set(index,elemento);
        }else {
            vehiculos.add(elemento);
        }
        return elemento;
    }

    @Override
    public Optional<Vehiculo> buscarPorId(String id) {
        return vehiculos.stream()
                .filter(v->v.getPlaca().equalsIgnoreCase(id))
                .findFirst();
    }

    @Override
    public boolean eliminar(String id) {
        if(id == null || id.isBlank()){
            return false;
        }
        return vehiculos.removeIf(v->v.getPlaca().equalsIgnoreCase(id));
    }
}
