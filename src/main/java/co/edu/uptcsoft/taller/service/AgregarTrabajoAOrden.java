package co.edu.uptcsoft.taller.service;

public interface AgregarTrabajoAOrden {
    void execute(String idOrden,
                 String descripcion,
                 double valor);
}
