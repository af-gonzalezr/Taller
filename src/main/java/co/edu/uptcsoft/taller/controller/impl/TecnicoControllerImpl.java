package co.edu.uptcsoft.taller.controller.impl;

import co.edu.uptcsoft.taller.controller.TecnicoController;
import co.edu.uptcsoft.taller.model.Tecnico;
import co.edu.uptcsoft.taller.service.BuscarTecnico;
import co.edu.uptcsoft.taller.service.ListarTecnicos;
import co.edu.uptcsoft.taller.service.RegistrarTecnico;

import java.util.List;

public class TecnicoControllerImpl implements TecnicoController {
    private final RegistrarTecnico registrarTecnico;
    private final BuscarTecnico buscarTecnico;
    private final ListarTecnicos listarTecnicos;

    public TecnicoControllerImpl(RegistrarTecnico registrarTecnico,
                                 BuscarTecnico buscarTecnico,
                                 ListarTecnicos listarTecnicos) {
        this.registrarTecnico = registrarTecnico;
        this.buscarTecnico = buscarTecnico;
        this.listarTecnicos = listarTecnicos;
    }

    @Override
    public Tecnico registrar(String id, String nombre, String especialidad) {
        String normalizedId = ControllerValidation.requiredText(id, "id");
        String normalizedName = ControllerValidation.requiredText(nombre, "nombre");
        String normalizedSpecialty = ControllerValidation.requiredText(especialidad, "especialidad");
        return registrarTecnico.execute(normalizedId, normalizedName, normalizedSpecialty);
    }

    @Override
    public Tecnico buscarPorId(String id) {
        return buscarTecnico.execute(ControllerValidation.requiredText(id, "id"));
    }

    @Override
    public List<Tecnico> listarTodos() {
        return listarTecnicos.execute();
    }
}
