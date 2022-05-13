package com.deps.consultorioapi.model.DTO;

import com.deps.consultorioapi.model.Medico;
import com.deps.consultorioapi.model.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.time.LocalDate;

public class ConsultaDTO {

    private Long medicoId;

    private Long pacienteId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataConsulta;

    public ConsultaDTO(){}

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }
}
