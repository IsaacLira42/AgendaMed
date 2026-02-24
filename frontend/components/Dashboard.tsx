import ConsultaTable from "./ConsultaTable";
import AgendarForm from "./AgendarForm";
import { useEffect, useState } from "react";
import { pacienteService } from "@/services/pacienteService";
import { AgendaDTO } from "@/types";


export default function Dashboard() {
    const [consultas, setConsultas] = useState<AgendaDTO | null>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        pacienteService.getAgenda()
            .then((data) => {
                setConsultas(data);
            })
            .finally(() => setLoading(false));
    }, []);

    if (loading) {
        return <p>Carregando...</p>;
    }

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

                        {consultas?.consulta ? (
                            <div className="mt-4 flex items-center justify-between">
                                <div>
                                    <p className="font-medium">
                                        {consultas.consulta.medico.nome} — {consultas.consulta.medico.especialidade}
                                    </p>
                                    <p className="text-sm text-gray-600">
                                        {new Date(consultas.consulta.dataHora).toLocaleString()}
                                    </p>
                                </div>

                                <div className="ml-4">
                                    <span className="inline-flex items-center text-sm font-medium px-3 py-1 rounded-full bg-blue-50 text-azul-corporativo">
                                        {consultas.consulta.status}
                                    </span>
                                </div>
                            </div>
                        ) : (
                            <p className="mt-4 text-gray-500">
                                Nenhuma consulta futura agendada.
                            </p>
                        )}
                    </div>

                    <div className="bg-white p-6 rounded-lg shadow">
                        <h3 className="text-lg font-semibold mb-4">
                            Consultas Futuras
                        </h3>

                        <ConsultaTable
                            consultas={consultas?.proximasConsultas ?? []}
                        />
                    </div>

                </div>

                <aside>
                    <AgendarForm />
                </aside>
            </section>
        </div>
    );
}