package co.edu.uptcsoft.taller.repository.impl;

import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.repository.Repository;

import java.util.ArrayList;
import java.util.Optional;

public class ClienteRepository implements Repository<Cliente> {

    private final ArrayList<Cliente> clientes;

    public ClienteRepository() {
        clientes = new ArrayList<>();
    }


    @Override
    public Cliente guardar(Cliente elemento) {

        if(elemento == null){
            return null;
        }

        Optional<Cliente> existente = buscarPorId(elemento.getIdCliente());

        if(existente.isPresent()){
            int index = clientes.indexOf(existente.get());
            clientes.set(index,elemento);
        }else {
            clientes.add(elemento);
        }
        return elemento;
    }

    @Override
    public Optional<Cliente> buscarPorId(String id) {
        return clientes.stream()
                .filter(c->c.getIdCliente().equalsIgnoreCase(id))
                .findFirst();
    }

    @Override
    public boolean eliminar(String id) {
        if(id == null || id.isBlank()){
            return false;
        }
        return clientes.removeIf(c->c.getIdCliente().equalsIgnoreCase(id));
    }
}
