import { http } from "@/lib/http";
import { AgendaDTO, ConsultaResponseDTO } from "@/types";

export const pacienteService = {
    async getAgenda() {
        const agenda: AgendaDTO = await http<AgendaDTO>("/pacientes/agenda");

        return agenda;
    },

    async getConsultas() {
        const consultas: ConsultaResponseDTO[] = await http<ConsultaResponseDTO[]>("/pacientes/consultas-paciente");

        return consultas;
    }
};