"use client";
import React, { useMemo, useState } from "react";

type Medico = { id: string; nome: string; especialidade: string };

const MEDICOS: Medico[] = [
    { id: "1", nome: "Dr. João Silva", especialidade: "Cardiologia" },
    { id: "2", nome: "Dra. Maria Souza", especialidade: "Dermatologia" },
    { id: "3", nome: "Dr. Carlos Lima", especialidade: "Cardiologia" },
    { id: "4", nome: "Dra. Ana Paula", especialidade: "Pediatria" },
];

export default function AgendarForm() {
    const especialidades = useMemo(() => Array.from(new Set(MEDICOS.map((m) => m.especialidade))), []);
    const [especialidade, setEspecialidade] = useState<string>(especialidades[0] || "");
    const [medicoId, setMedicoId] = useState<string>("");
    const [data, setData] = useState<string>("");
    const [horario, setHorario] = useState<string>("");

    const medicosFiltrados = MEDICOS.filter((m) => m.especialidade === especialidade);

    function handleSubmit(e: React.FormEvent) {
        e.preventDefault();
        if (!medicoId || !data || !horario) {
            alert("Por favor preencha todos os campos.");
            return;
        }
        const medico = MEDICOS.find((m) => m.id === medicoId);
        alert(`Consulta agendada com ${medico?.nome} em ${data} às ${horario}`);
        setMedicoId("");
        setData("");
        setHorario("");
    }

    return (
        <form onSubmit={handleSubmit} className="bg-white p-6 rounded-lg shadow">
            <h3 className="text-lg font-semibold mb-4">Agendar Consulta</h3>

            <label className="block mb-2">Especialidade</label>
            <select className="w-full mb-4 p-2 border rounded" value={especialidade} onChange={(e) => setEspecialidade(e.target.value)}>
                {especialidades.map((esp) => (
                    <option key={esp} value={esp}>{esp}</option>
                ))}
            </select>

            <label className="block mb-2">Médico</label>
            <select className="w-full mb-4 p-2 border rounded" value={medicoId} onChange={(e) => setMedicoId(e.target.value)}>
                <option value="">Selecione um médico</option>
                {medicosFiltrados.map((m) => (
                    <option key={m.id} value={m.id}>{m.nome}</option>
                ))}
            </select>

            <label className="block mb-2">Data</label>
            <input className="w-full mb-4 p-2 border rounded" type="date" value={data} onChange={(e) => setData(e.target.value)} />

            <label className="block mb-2">Horário</label>
            <select className="w-full mb-4 p-2 border rounded" value={horario} onChange={(e) => setHorario(e.target.value)}>
                <option value="">Selecione um horário</option>
                <option value="09:00">09:00</option>
                <option value="10:00">10:00</option>
                <option value="14:00">14:00</option>
                <option value="15:30">15:30</option>
            </select>

            <button className="bg-azul-corporativo text-white px-4 py-2 rounded" type="submit">Agendar</button>
        </form>
    );
}
