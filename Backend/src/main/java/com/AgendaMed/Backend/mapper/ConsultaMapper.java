package com.AgendaMed.Backend.mapper;

import com.AgendaMed.Backend.dto.response.ConsultaResponseDTO;
import com.AgendaMed.Backend.dto.response.MedicoResumoDTO;
import com.AgendaMed.Backend.dto.response.PacienteResumoDTO;
import com.AgendaMed.Backend.model.Consulta;

public class ConsultaMapper {

    public static ConsultaResponseDTO toResponseDTO(Consulta consulta) {
        return new ConsultaResponseDTO(
                consulta.getId(),
                new MedicoResumoDTO(
                        consulta.getMedico().getId(),
                        consulta.getMedico().getUsuario().getName(),
                        consulta.getMedico().getEspecialidade(),
                        consulta.getMedico().getCrm()),
                new PacienteResumoDTO(
                        consulta.getPaciente().getId(),
                        consulta.getPaciente().getUsuario().getName(),
                        consulta.getPaciente().getTelefone(),
                        consulta.getPaciente().getUsuario().getEmail()),
                consulta.getDataHora(),
                consulta.getStatus());
    }
}