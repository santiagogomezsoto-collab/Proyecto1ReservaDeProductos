package logic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Reserva {

private String id;
private String actividad;

// OBJETOS de las librerias que acabo de importar
private LocalDate fecha;
private LocalTime horaInicio;
private LocalTime horaFin;


private EstadoReserva estado;
private Funcionario funcionario;

//ARRAY DE RECURSOS
private List<Recurso> recursos;

//reserva se construye con la LISTA de RECURSOS
    public Reserva() {
        recursos = new ArrayList<>();
    }


    public Reserva(String id,
                   String actividad,
                   LocalDate fecha,
                   LocalTime horaInicio,
                   LocalTime horaFin,
                   Funcionario funcionario) {

        this.id = id;
        this.actividad = actividad;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;

        this.funcionario = funcionario;

        this.estado = EstadoReserva.ACTIVA;

        this.recursos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Recurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<Recurso> recursos) {
        this.recursos = recursos;

    }
}
