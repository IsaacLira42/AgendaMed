"use client";
import React, { useState } from "react";
import { useRouter } from "next/navigation";
import Link from "next/link";
import { useAuth } from "@/context/AuthContext";
import RedirectIfAuthenticated from "@/components/RedirectIfAuthenticated";

export default function RegisterPage() {
    const router = useRouter();
    const [nome, setNome] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const { login } = useAuth();

    async function handleSubmit(e: React.FormEvent) {
        e.preventDefault();
        // Mock register: salvar token e user
        localStorage.setItem("token", "mock-token");
        // opcional: chamar login real aqui
        try {
            await login(email, password);
        } catch {
            // fallback para mock
        }
        router.push("/");
    }

    return (
        <RedirectIfAuthenticated>
            <div className="ml-56 p-8">
                <div className="max-w-md bg-white p-6 rounded-lg shadow">
                    <h1 className="text-2xl font-bold mb-4">Registrar</h1>
                    <form onSubmit={handleSubmit} className="space-y-4">
                        <div>
                            <label className="block text-sm font-medium">Nome</label>
                            <input value={nome} onChange={(e) => setNome(e.target.value)} className="w-full mt-1 p-2 border rounded" />
                        </div>
                        <div>
                            <label className="block text-sm font-medium">Email</label>
                            <input type="email" required value={email} onChange={(e) => setEmail(e.target.value)} className="w-full mt-1 p-2 border rounded" />
                        </div>
                        <div>
                            <label className="block text-sm font-medium">Senha</label>
                            <input type="password" required value={password} onChange={(e) => setPassword(e.target.value)} className="w-full mt-1 p-2 border rounded" />
                        </div>
                        <div className="flex items-center justify-between">
                            <button className="bg-azul-corporativo text-white px-4 py-2 rounded" type="submit">Registrar</button>
                            <Link href="/login" className="text-sm text-azul-corporativo">Já tem conta? Entrar</Link>
                        </div>
                    </form>
                </div>
            </div>
        </RedirectIfAuthenticated>
    );
}
