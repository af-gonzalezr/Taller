package co.edu.uptcsoft.taller.repository.impl;

import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.EstadoOrden;
import co.edu.uptcsoft.taller.model.OrdenTrabajo;
import co.edu.uptcsoft.taller.repository.OrdenRepo;
import co.edu.uptcsoft.taller.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdenRepository implements OrdenRepo {

    private final ArrayList<OrdenTrabajo> ordenes;

    public OrdenRepository() {
        ordenes = new ArrayList<>();
    }


    @Override
    public OrdenTrabajo guardar(OrdenTrabajo elemento) {
        if(elemento == null){
            return null;
        }

        Optional<OrdenTrabajo> existente = buscarPorId(elemento.getIdOrden());

        if(existente.isPresent()){
            int index = ordenes.indexOf(existente.get());
            ordenes.set(index,elemento);
        }else {
            ordenes.add(elemento);
        }
        return elemento;
    }

    @Override
    public Optional<OrdenTrabajo> buscarPorId(String id) {
        return ordenes.stream()
                .filter(o->o.getIdOrden().equalsIgnoreCase(id))
                .findFirst();
    }

    @Override
    public boolean eliminar(String id) {
        if(id == null || id.isBlank()){
            return false;}
        return ordenes.removeIf(o->o.getIdOrden().equalsIgnoreCase(id));
    }

    @Override
    public List<OrdenTrabajo> buscarPorCliente(String idCliente){
        return ordenes.stream()
                .filter(o->o.getVehiculo().getCliente().getIdCliente().equalsIgnoreCase(idCliente))
                .toList();
    }

    @Override
    public List<OrdenTrabajo> buscarPorEstado(EstadoOrden estadoOrden) {
        return ordenes.stream()
                .filter(o-> o.getEstado() == estadoOrden)
                .toList();
    }

    @Override
    public List<OrdenTrabajo> todas() {
        return List.copyOf(ordenes);
    }
}
