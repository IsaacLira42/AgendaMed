import { http } from "@/lib/http";
import { AgendaDTO } from "@/types";

export const pacienteService = {
    async getAgenda() {
        const agenda: AgendaDTO = await http<AgendaDTO>("/pacientes/agenda");

        return agenda;
    },
};