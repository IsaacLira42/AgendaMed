import ConsultaTable from "./ConsultaTable";
import AgendarForm from "./AgendarForm";

const MOCK_PROXIMA = {
    medico: "Dra. Maria Souza",
    especialidade: "Dermatologia",
    data: new Date(Date.now() + 86400000).toISOString(),
};

const MOCK_FUTURAS = [
    { id: "a1", medico: "Dra. Maria Souza", especialidade: "Dermatologia", data: new Date(Date.now() + 86400000).toISOString(), status: "Agendada" },
    { id: "a2", medico: "Dr. João Silva", especialidade: "Cardiologia", data: new Date(Date.now() + 86400000 * 3).toISOString(), status: "Confirmada" },
    { id: "a3", medico: "Dra. Ana Paula", especialidade: "Pediatria", data: new Date(Date.now() + 86400000 * 10).toISOString(), status: "Pendente" },
];

export default function Dashboard() {
    return (
        <div className="space-y-6">
            <section className="bg-white p-6 rounded-lg shadow">
                <h2 className="text-2xl font-bold">Bem-vindo de volta</h2>
                <p className="text-gray-600">Aqui estão suas informações recentes.</p>
            </section>

            <section className="grid grid-cols-1 lg:grid-cols-3 gap-6">
                <div className="col-span-2">
                    <div className="bg-white p-6 rounded-lg shadow mb-6">
                        <h3 className="text-lg font-semibold">Próxima Consulta</h3>
                        <div className="mt-4">
                            <p className="font-medium">{MOCK_PROXIMA.medico} — {MOCK_PROXIMA.especialidade}</p>
                            <p className="text-sm text-gray-600">{new Date(MOCK_PROXIMA.data).toLocaleString()}</p>
                        </div>
                    </div>

                    <div className="bg-white p-6 rounded-lg shadow">
                        <h3 className="text-lg font-semibold mb-4">Consultas Futuras</h3>
                        <ConsultaTable consultas={MOCK_FUTURAS} />
                    </div>
                </div>

                <aside>
                    <AgendarForm />
                </aside>
            </section>
        </div>
    );
}
