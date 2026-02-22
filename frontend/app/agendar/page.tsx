import AgendarForm from "@/components/AgendarForm";

export default function AgendarPage() {
    return (
        <div className="ml-56 p-8">
            <h1 className="text-2xl font-bold mb-4">Agendar Consulta</h1>
            <div className="max-w-lg">
                <AgendarForm />
            </div>
        </div>
    );
}
