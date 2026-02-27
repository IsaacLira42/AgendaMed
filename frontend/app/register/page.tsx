"use client";
import React, { useState } from "react";
import { useRouter } from "next/navigation";
import Link from "next/link";
import { useAuth } from "@/context/AuthContext";
import RedirectIfAuthenticated from "@/components/RedirectIfAuthenticated";
import { authService } from "@/services/authService";
import { RegisterData } from "@/types";

export default function RegisterPage() {
    const router = useRouter();
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const [cpf, setCpf] = useState("")
    const [telefone, setTelefone] = useState("");
    const { login } = useAuth();

    async function handleSubmit(e: React.FormEvent) {
        e.preventDefault();

        const registerData: RegisterData = {
            name,
            email,
            senha,
            cpf,
            telefone
        };

        try {
            console.log(registerData);
            await authService.register(registerData);
            await login({ email, senha });
            router.push("/");
        } catch (error) {
            console.error("Erro no registro:", error);
        }
    }

    return (
        <RedirectIfAuthenticated>
            <div className="ml-56 p-8">
                <div className="max-w-md bg-white p-6 rounded-lg shadow">
                    <h1 className="text-2xl font-bold mb-4">Registrar</h1>
                    <form onSubmit={handleSubmit} className="space-y-4">
                        <div>
                            <label className="block text-sm font-medium">Nome</label>
                            <input value={name} onChange={(e) => setName(e.target.value)} className="w-full mt-1 p-2 border rounded" />
                        </div>
                        <div>
                            <label className="block text-sm font-medium">Email</label>
                            <input type="email" required value={email} onChange={(e) => setEmail(e.target.value)} className="w-full mt-1 p-2 border rounded" />
                        </div>
                        <div>
                            <label className="block text-sm font-medium">Senha</label>
                            <input type="password" required value={senha} onChange={(e) => setSenha(e.target.value)} className="w-full mt-1 p-2 border rounded" />
                        </div>
                        <div>
                            <label className="block text-sm font-medium">CPF</label>
                            <input value={cpf} onChange={(e) => setCpf(e.target.value)} className="w-full mt-1 p-2 border rounded" />
                        </div>
                        <div>
                            <label className="block text-sm font-medium">Telefone</label>
                            <input value={telefone} onChange={(e) => setTelefone(e.target.value)} className="w-full mt-1 p-2 border rounded" />
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
