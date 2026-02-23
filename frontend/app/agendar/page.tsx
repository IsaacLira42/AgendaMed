"use client";
import AgendarForm from "@/components/AgendarForm";
import RequireAuth from "@/components/RequireAuth";

export default function AgendarPage() {
    return (
        <RequireAuth>
            <div className="ml-56 p-8">
                <h1 className="text-2xl font-bold mb-4">Agendar Consulta</h1>
                <div className="max-w-lg">
                    <AgendarForm />
                </div>
            </div>
        </RequireAuth>
    );
}
