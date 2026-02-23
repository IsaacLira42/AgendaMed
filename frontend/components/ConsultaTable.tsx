import React from "react";

type Consulta = {
    id: string;
    medico: string;
    especialidade: string;
    data: string;
    status: string;
};

const statusClass = (status: string) => {
    switch (status.toLowerCase()) {
        case "confirmada":
            return "bg-green-100 text-green-800";
        case "agendada":
            return "bg-blue-100 text-azul-corporativo";
        case "pendente":
            return "bg-yellow-100 text-yellow-800";
        case "concluída":
        case "concluida":
            return "bg-gray-100 text-gray-800";
        default:
            return "bg-gray-100 text-gray-800";
    }
};

export default function ConsultaTable({ consultas }: { consultas: Consulta[] }) {
    return (
        <div className="overflow-x-auto bg-white rounded-lg shadow">
            <table className="min-w-full divide-y divide-gray-200">
                <thead className="bg-gray-50">
                    <tr>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Médico</th>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Especialidade</th>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Data</th>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Status</th>
                    </tr>
                </thead>
                <tbody className="bg-white divide-y divide-gray-100">
                    {consultas.map((c, idx) => (
                        <tr key={c.id} className={idx % 2 === 0 ? "bg-white" : "bg-gray-50"}>
                            <td className="px-6 py-4 whitespace-nowrap">
                                <div className="text-sm font-medium text-gray-900">{c.medico}</div>
                            </td>
                            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-600">{c.especialidade}</td>
                            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-600">{new Date(c.data).toLocaleString()}</td>
                            <td className="px-6 py-4 whitespace-nowrap">
                                <span className={`inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium ${statusClass(c.status)}`}>
                                    {c.status}
                                </span>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
