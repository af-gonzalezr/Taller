@startuml
package model {

    ' --- Nivel 1: OrdenTrabajo ---
    class OrdenTrabajo {
        - idOrden : String
        - fechaHoraIngreso : LocalDateTime
        - fechaHoraEntrega : LocalDateTime
        - observacionesIngreso : String
        - observacionesEntrega : String
        + agregarTrabajo(trabajo : ServicioRealizado) : void
        + calcularCostoTotal() : double
        + toString() : String
    }

    enum EstadoOrden {
        EN_PROCESO
        FINALIZADA
        CANCELADA
    }

    ' --- Nivel 2: Vehiculo, Tecnico, ServicioRealizado ---
    abstract class Vehiculo {
        # placa : String
        # marca : String
        # modelo : int
        + toString() : String
    }

    class Tecnico {
        - idTecnico : String
        - nombre : String
        - especialidad : String
        + toString() : String
    }

    class ServicioRealizado {
        - descripcion : String
        - valor : double
        + toString() : String
    }

    ' --- Nivel 3: Subclases de Vehiculo y Cliente ---
    class Automovil {
        - numeroPuertas : int
        + toString() : String
    }

    class Camion {
        - capacidadCargaTon : double
        + toString() : String
    }

    class Motocicleta {
        - cilindraje : int
        + toString() : String
    }

    class Cliente {
        - idCliente : String
        - nombre : String
        - telefono : String
        - email : String
        + toString() : String
    }

    ' --- Relaciones y Multiplicidades ---
    
    ' Top -> Lado derecho
    OrdenTrabajo "0..*" -right-> "1" EstadoOrden : estado

    ' Top -> Nivel 2
    OrdenTrabajo "0..*" -down-> "1" Vehiculo : vehiculo
    OrdenTrabajo "0..*" -down-> "1" Tecnico : tecnico
    OrdenTrabajo "1" o-down-> "*" ServicioRealizado : listaTrabajos

    ' Vehiculo -> Cliente
    Vehiculo "0..*" -up-> "1" Cliente : cliente

    ' Herencia
    Automovil -up-|> Vehiculo
    Camion -up-|> Vehiculo
    Motocicleta -left-|> Vehiculo

}
@enduml