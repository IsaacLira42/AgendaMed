"use client";

import React, { createContext, useContext, useEffect, useState } from "react";
import { authService } from "@/services/authService";

type User = any;

interface AuthContextValue {
    user: User | null;
    isAuthenticated: boolean;
    loading: boolean;
    login: (email: string, senha: string) => Promise<void>;
    logout: () => void;
}

const AuthContext = createContext<AuthContextValue | undefined>(undefined);

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
    const [user, setUser] = useState<User | null>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        async function init() {
            const token = localStorage.getItem("token");
            if (token) {
                try {
                    const me = await authService.me();
                    setUser(me);
                } catch (e) {
                    console.error("Falha ao validar token", e);
                    authService.logout();
                    setUser(null);
                }
            }
            setLoading(false);
        }
        init();
    }, []);

    const login = async (email: string, senha: string) => {
        setLoading(true);
        await authService.login(email, senha);
        try {
            const me = await authService.me();
            setUser(me);
        } finally {
            setLoading(false);
        }
    };

    const logout = () => {
        authService.logout();
        setUser(null);
    };

    return (
        <AuthContext.Provider
            value={{ user, isAuthenticated: !!user, loading, login, logout }}
        >
            {children}
        </AuthContext.Provider>
    );
};

export function useAuth() {
    const ctx = useContext(AuthContext);
    if (!ctx) throw new Error("useAuth deve ser usado dentro de AuthProvider");
    return ctx;
}

export default AuthContext;
