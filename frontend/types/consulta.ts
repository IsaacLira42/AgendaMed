import { MedicoResumoDTO } from './medico';
import { PacienteResumoDTO } from './paciente';

export type StatusConsulta = 'AGENDADA' | 'CANCELADA' | 'REALIZADA';

export interface ConsultaResponseDTO {
    id: number;
    medico: MedicoResumoDTO;
    paciente: PacienteResumoDTO;
    dataHora: string;
    status: StatusConsulta;
}

export interface AgendaDTO {
    consulta: ConsultaResponseDTO;
    proximasConsultas: ConsultaResponseDTO[];
}