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
        + getIdOrden() : String
        + getFechaHoraIngreso() : LocalDateTime
        + getFechaHoraEntrega() : LocalDateTime
        + setFechaHoraEntrega(fechaHoraEntrega : LocalDateTime) : void
        + getEstado() : EstadoOrden
        + setEstado(estado : EstadoOrden) : void
        + getVehiculo() : Vehiculo
        + getTecnico() : Tecnico
        + getObservacionesIngreso() : String
        + getObservacionesEntrega() : String
        + setObservacionesEntrega(observacionesEntrega : String) : void
        + getListaTrabajos() : List<ServicioRealizado>
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
        + getPlaca() : String
        + setPlaca(placa : String) : void
        + getMarca() : String
        + setMarca(marca : String) : void
        + getModelo() : int
        + setModelo(modelo : int) : void
        + getCliente() : Cliente
        + setCliente(cliente : Cliente) : void
        + toString() : String
    }

    class Tecnico {
        - idTecnico : String
        - nombre : String
        - especialidad : String
        + getIdTecnico() : String
        + getNombre() : String
        + getEspecialidad() : String
        + toString() : String
    }

    class ServicioRealizado {
        - descripcion : String
        - valor : double
        + getDescripcion() : String
        + setDescripcion(descripcion : String) : void
        + getValor() : double
        + setValor(valor : double) : void
        + toString() : String
    }

    ' --- Nivel 3: Subclases de Vehiculo y Cliente ---
    class Automovil {
        - numeroPuertas : int
        + getNumeroPuertas() : int
        + setNumeroPuertas(numeroPuertas : int) : void
        + toString() : String
    }

    class Camion {
        - capacidadCargaTon : double
        + getCapacidadCargaTon() : double
        + setCapacidadCargaTon(capacidadCargaTon : double) : void
        + toString() : String
    }

    class Motocicleta {
        - cilindraje : int
        + getCilindraje() : int
        + toString() : String
    }

    class Cliente {
        - idCliente : String
        - nombre : String
        - telefono : String
        - email : String
        + getIdCliente() : String
        + getNombre() : String
        + toString() : String
    }

    ' --- Relaciones con Multiplicidades Ajustadas ---
    
    ' OrdenTrabajo -> EstadoOrden (Varias órdenes pueden tener 1 estado determinado)
    OrdenTrabajo "0..*" -right-> "1" EstadoOrden : estado

    ' OrdenTrabajo -> Dependencias (Varias órdenes apuntan a 1 Vehículo/Técnico)
    OrdenTrabajo "0..*" -down-> " 1      " Vehiculo : vehiculo
    OrdenTrabajo "0..*" -down-> " 1 " Tecnico : tecnico

    ' Agregación: 1 Orden contiene de 0 a muchos (*) trabajos realizados
    OrdenTrabajo "1 " o-down-> "0..*  " ServicioRealizado : listaTrabajos

    ' Vehiculo -> Cliente (Muchos vehículos "0..*" pertenecen a 1 Cliente "1")
    Vehiculo "0..*" -up-> "1  " Cliente : cliente

    ' Herencia
    Automovil -up-|> Vehiculo
    Camion -up-|> Vehiculo
    Motocicleta -left-|> Vehiculo

}
@enduml@startuml
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
        + getIdOrden() : String
        + getFechaHoraIngreso() : LocalDateTime
        + getFechaHoraEntrega() : LocalDateTime
        + setFechaHoraEntrega(fechaHoraEntrega : LocalDateTime) : void
        + getEstado() : EstadoOrden
        + setEstado(estado : EstadoOrden) : void
        + getVehiculo() : Vehiculo
        + getTecnico() : Tecnico
        + getObservacionesIngreso() : String
        + getObservacionesEntrega() : String
        + setObservacionesEntrega(observacionesEntrega : String) : void
        + getListaTrabajos() : List<ServicioRealizado>
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
        + getPlaca() : String
        + setPlaca(placa : String) : void
        + getMarca() : String
        + setMarca(marca : String) : void
        + getModelo() : int
        + setModelo(modelo : int) : void
        + getCliente() : Cliente
        + setCliente(cliente : Cliente) : void
        + toString() : String
    }

    class Tecnico {
        - idTecnico : String
        - nombre : String
        - especialidad : String
        + getIdTecnico() : String
        + getNombre() : String
        + getEspecialidad() : String
        + toString() : String
    }

    class ServicioRealizado {
        - descripcion : String
        - valor : double
        + getDescripcion() : String
        + setDescripcion(descripcion : String) : void
        + getValor() : double
        + setValor(valor : double) : void
        + toString() : String
    }

    ' --- Nivel 3: Subclases de Vehiculo y Cliente ---
    class Automovil {
        - numeroPuertas : int
        + getNumeroPuertas() : int
        + setNumeroPuertas(numeroPuertas : int) : void
        + toString() : String
    }

    class Camion {
        - capacidadCargaTon : double
        + getCapacidadCargaTon() : double
        + setCapacidadCargaTon(capacidadCargaTon : double) : void
        + toString() : String
    }

    class Motocicleta {
        - cilindraje : int
        + getCilindraje() : int
        + toString() : String
    }

    class Cliente {
        - idCliente : String
        - nombre : String
        - telefono : String
        - email : String
        + getIdCliente() : String
        + getNombre() : String
        + toString() : String
    }

    ' --- Relaciones con Multiplicidades Ajustadas ---
    
    ' OrdenTrabajo -> EstadoOrden (Varias órdenes pueden tener 1 estado determinado)
    OrdenTrabajo "0..*" -right-> "1" EstadoOrden : estado

    ' OrdenTrabajo -> Dependencias (Varias órdenes apuntan a 1 Vehículo/Técnico)
    OrdenTrabajo "0..*" -down-> " 1      " Vehiculo : vehiculo
    OrdenTrabajo "0..*" -down-> " 1 " Tecnico : tecnico

    ' Agregación: 1 Orden contiene de 0 a muchos (*) trabajos realizados
    OrdenTrabajo "1 " o-down-> "0..*  " ServicioRealizado : listaTrabajos

    ' Vehiculo -> Cliente (Muchos vehículos "0..*" pertenecen a 1 Cliente "1")
    Vehiculo "0..*" -up-> "1  " Cliente : cliente

    ' Herencia
    Automovil -up-|> Vehiculo
    Camion -up-|> Vehiculo
    Motocicleta -left-|> Vehiculo

}
@enduml