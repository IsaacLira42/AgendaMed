import ConsultaTable from "@/components/ConsultaTable";

const MOCK = [
    { id: "1", medico: "Dr. João Silva", especialidade: "Cardiologia", data: new Date().toISOString(), status: "Concluída" },
    { id: "2", medico: "Dra. Maria Souza", especialidade: "Dermatologia", data: new Date(Date.now() + 86400000 * 2).toISOString(), status: "Agendada" },
    { id: "3", medico: "Dra. Ana Paula", especialidade: "Pediatria", data: new Date(Date.now() + 86400000 * 7).toISOString(), status: "Pendente" },
];

export default function MinhasConsultasPage() {
    return (
        <div className="ml-56 p-8">
            <h1 className="text-2xl font-bold mb-4">Minhas Consultas</h1>
            <ConsultaTable consultas={MOCK} />
        </div>
    );
}
