package co.edu.uptcsoft.taller.service.impl;

import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.repository.Repository;
import co.edu.uptcsoft.taller.service.ActualizarTecnico;
import co.edu.uptcsoft.taller.service.Respuesta;

import java.util.Optional;

public class ActualizarTecnicoImpl implements ActualizarTecnico {

    private final Repository<Tecnico> tecnicoRepository;

    public ActualizarTecnicoImpl(Repository<Tecnico> tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    @Override
    public Respuesta<Tecnico> execute(String idTecnico, String nuevoNombre, String nuevaEspecialidad) {

        Optional<Tecnico> tOpt = tecnicoRepository.buscarPorId(idTecnico);
        if(tOpt.isEmpty()){
            return Respuesta.error("El técnico no existe");
        }

        Tecnico t = tOpt.get();
        t.setInfo(nuevoNombre, nuevaEspecialidad);
        return Respuesta.completado("Datos actualizados exitosamente",
                tecnicoRepository.guardar(t));
    }
}
