"use client";

import React, { createContext, useContext, useEffect, useState } from "react";
import { authService } from "@/services/authService";
import { LoginData, UserDTO } from "@/types";

type User = UserDTO;

interface AuthContextValue {
    user: User | null;
    isAuthenticated: boolean;
    loading: boolean;
    login: (data: LoginData) => Promise<void>;
    logout: () => Promise<void>;
}

const AuthContext = createContext<AuthContextValue | undefined>(undefined);

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
    const [user, setUser] = useState<User | null>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        async function init() {
            try {
                const me = await authService.me();
                setUser(me);
            } catch (e) {
                // Silenciosamente falha, o usuário não está logado
                setUser(null);
            } finally {
                setLoading(false);
            }
        }
        init();
    }, []);

    const login = async (data: LoginData) => {
        setLoading(true);
        try {
            await authService.login(data);
            const me = await authService.me();
            setUser(me);
        } finally {
            setLoading(false);
        }
    };

    const logout = async () => {
        await authService.logout();
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
