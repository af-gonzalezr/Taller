package co.edu.uptcsoft.taller.model;

public class Tecnico {
    private final String idTecnico;
    private String nombre;
    private String especialidad;

    public Tecnico(String idTecnico, String nombre, String especialidad) {
        this.idTecnico = idTecnico;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getIdTecnico() {
        return idTecnico;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public String toString() {
        return "Tecnico{" +
                "idTecnico='" + idTecnico + '\'' +
                ", nombre='" + nombre + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}