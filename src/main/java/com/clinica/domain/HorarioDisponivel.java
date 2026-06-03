package com.clinica.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class HorarioDisponivel {
    private final DayOfWeek diaSemana;
    private final LocalTime horaInicio;
    private final LocalTime horaFim;

    public HorarioDisponivel(DayOfWeek diaSemana, LocalTime horaInicio, LocalTime horaFim) {
        if (diaSemana == null) {
            throw new IllegalArgumentException("O dia da semana é obrigatório.");
        }
        if (horaInicio == null) {
            throw new IllegalArgumentException("A hora de início é obrigatória.");
        }
        if (horaFim == null) {
            throw new IllegalArgumentException("A hora de fim é obrigatória.");
        }
        if (!horaFim.isAfter(horaInicio)) {
            throw new IllegalArgumentException("A hora de fim deve ser posterior à hora de início.");
        }

        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public DayOfWeek getDiaSemana() {
        return diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public boolean contem(LocalTime horario) {
        if (horario == null) {
            throw new IllegalArgumentException("O horário é obrigatório.");
        }

        return !horario.isBefore(horaInicio) && horario.isBefore(horaFim);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HorarioDisponivel that = (HorarioDisponivel) o;
        return diaSemana == that.diaSemana
            && horaInicio.equals(that.horaInicio)
            && horaFim.equals(that.horaFim);
    }

    @Override
    public int hashCode() {
        int result = diaSemana.hashCode();
        result = 31 * result + horaInicio.hashCode();
        result = 31 * result + horaFim.hashCode();
        return result;
    }
}
