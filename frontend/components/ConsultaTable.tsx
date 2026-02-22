import React from "react";

type Consulta = {
    id: string;
    medico: string;
    especialidade: string;
    data: string;
    status: string;
};

export default function ConsultaTable({ consultas }: { consultas: Consulta[] }) {
    return (
        <div className="overflow-x-auto bg-white rounded-lg shadow p-4">
            <table className="min-w-full text-left">
                <thead>
                    <tr>
                        <th className="p-2">Médico</th>
                        <th className="p-2">Especialidade</th>
                        <th className="p-2">Data</th>
                        <th className="p-2">Status</th>
                    </tr>
                </thead>
                <tbody>
                    {consultas.map((c) => (
                        <tr key={c.id} className="border-t">
                            <td className="p-2">{c.medico}</td>
                            <td className="p-2">{c.especialidade}</td>
                            <td className="p-2">{new Date(c.data).toLocaleString()}</td>
                            <td className="p-2">{c.status}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
