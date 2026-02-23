"use client";
import React, { useState } from "react";
import { useRouter } from "next/navigation";
import Link from "next/link";
import { authService } from "@/services/authService";
import { useAuth } from "@/context/AuthContext";
import RedirectIfAuthenticated from "@/components/RedirectIfAuthenticated";

export default function LoginPage() {
    const router = useRouter();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const { login } = useAuth();

    async function handleSubmit(e: React.FormEvent) {
        e.preventDefault();

        await login(email, password);
        router.push("/");
    }
    return (
        <RedirectIfAuthenticated>
            <div className="ml-56 p-8">
                <div className="max-w-md bg-white p-6 rounded-lg shadow">
                    <h1 className="text-2xl font-bold mb-4">Entrar</h1>
                    <form onSubmit={handleSubmit} className="space-y-4">
                        <div>
                            <label className="block text-sm font-medium">Email</label>
                            <input type="email" required value={email} onChange={(e) => setEmail(e.target.value)} className="w-full mt-1 p-2 border rounded" />
                        </div>
                        <div>
                            <label className="block text-sm font-medium">Senha</label>
                            <input type="password" required value={password} onChange={(e) => setPassword(e.target.value)} className="w-full mt-1 p-2 border rounded" />
                        </div>
                        <div className="flex items-center justify-between">
                            <button className="bg-azul-corporativo text-white px-4 py-2 rounded" type="submit">Entrar</button>
                            <Link href="/register" className="text-sm text-azul-corporativo">Criar conta</Link>
                        </div>
                    </form>
                </div>
            </div>
        </RedirectIfAuthenticated>
    );
}
