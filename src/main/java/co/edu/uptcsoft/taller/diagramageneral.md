@startuml
autonumber
skinparam BoxPadding 20

actor User

box "vista" #FFF8DC
participant vista
end box

box "control" #E6F2FF
participant control
end box

box "servicio" #E6FFE6
participant servicio
end box

box "repositorio" #FFE6E6
participant repositorio
end box

User -> vista : in
activate vista

vista -> control : in
activate control

control -> servicio : valid in
activate servicio

servicio -> repositorio : consulta
activate repositorio

repositorio --> servicio : datos
deactivate repositorio

servicio --> control : Respuesta<T>
deactivate servicio

control --> vista : Respuesta<T>
deactivate control

vista --> User : out
deactivate vista

@enduml