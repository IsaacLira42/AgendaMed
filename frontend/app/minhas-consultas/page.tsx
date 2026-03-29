'use client'

import ConsultaTable from "@/components/ConsultaTable";
import RequireAuth from "@/components/RequireAuth";
import { pacienteService } from "@/services/pacienteService";
import { ConsultaResponseDTO, MedicoResumoDTO, PacienteResumoDTO } from "@/types";
import { useEffect, useState } from "react";

// const mockMedicos: MedicoResumoDTO[] = [
//     { id: 1, nome: 'Dra. Ana Silva', especialidade: 'Cardiologia', crm: '12345-SP' },
//     { id: 2, nome: 'Dr. Carlos Santos', especialidade: 'Dermatologia', crm: '23456-SP' },
//     { id: 3, nome: 'Dra. Mariana Costa', especialidade: 'Pediatria', crm: '34567-SP' }
// ];

// const mockPacientes: PacienteResumoDTO[] = [
//     { id: 1, nome: 'João Mendes', telefone: '(11) 98765-4321', email: 'joao@email.com' },
//     { id: 2, nome: 'Maria Oliveira', telefone: '(11) 97654-3210', email: 'maria@email.com' },
//     { id: 3, nome: 'Pedro Santos', telefone: '(11) 96543-2109', email: 'pedro@email.com' }
// ];

// const mockConsultas: ConsultaResponseDTO[] = [
//     { id: 1, medico: mockMedicos[0], paciente: mockPacientes[0], dataHora: '2024-02-15T09:00:00', status: 'AGENDADA' },
//     { id: 2, medico: mockMedicos[1], paciente: mockPacientes[1], dataHora: '2024-02-15T10:30:00', status: 'AGENDADA' },
//     { id: 3, medico: mockMedicos[2], paciente: mockPacientes[2], dataHora: '2024-02-14T14:00:00', status: 'REALIZADA' },
//     { id: 4, medico: mockMedicos[0], paciente: mockPacientes[1], dataHora: '2024-02-13T11:00:00', status: 'CANCELADA' },
//     { id: 5, medico: mockMedicos[1], paciente: mockPacientes[2], dataHora: '2024-02-16T08:30:00', status: 'AGENDADA' }
// ];

export default function MinhasConsultasPage() {
    const [consultas, setConsultas] = useState<ConsultaResponseDTO[]>([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        pacienteService.getConsultas()
            .then((data) => {
                setConsultas(data);
            })
            .finally(() => setLoading(false));
    }, []);

        if (loading) {
            return <p>Carregando...</p>;
        }


    return (
        <RequireAuth>
            <div className="ml-56 p-8">
                <h1 className="text-2xl font-bold mb-4">Minhas Consultas</h1>
                <ConsultaTable consultas={consultas} />
            </div>
        </RequireAuth>
    );
}
