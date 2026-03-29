package com.AgendaMed.Backend.mapper;

import com.AgendaMed.Backend.dto.response.MedicoResumoDTO;
import com.AgendaMed.Backend.model.Medico;

public class MedicoMapper {

    public static MedicoResumoDTO toResponseDTO(Medico medico) {
        return new MedicoResumoDTO(
                medico.getId(),
                medico.getUsuario().getName(),
                medico.getEspecialidade(),
                medico.getCrm());
    }
}
