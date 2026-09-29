package co.edu.uptcsoft.taller.repository.impl;

import co.edu.uptcsoft.taller.model.Cliente;
import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.repository.Repository;

import java.util.ArrayList;
import java.util.Optional;

public class TecnicoRepository implements Repository<Tecnico>{

    private final ArrayList<Tecnico> tecnicos;

    public TecnicoRepository() {
        tecnicos = new ArrayList<>();
    }


    @Override
    public Tecnico guardar(Tecnico elemento) {

        if(elemento == null){
            return null;
        }

        Optional<Tecnico> existente = buscarPorId(elemento.getIdTecnico());

        if(existente.isPresent()){
            int index = tecnicos.indexOf(existente.get());
            tecnicos.set(index,elemento);
        }else {
            tecnicos.add(elemento);
        }
        return elemento;
    }

    @Override
    public Optional<Tecnico> buscarPorId(String id) {
        return tecnicos.stream()
                .filter(t->t.getIdTecnico().equalsIgnoreCase(id))
                .findFirst();
    }

    @Override
    public boolean eliminar(String id) {
        if(id == null || id.isBlank()){
            return false;
        }
        return tecnicos.removeIf(t->t.getIdTecnico().equalsIgnoreCase(id));
    }
}