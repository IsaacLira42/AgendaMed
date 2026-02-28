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

    const login = async (data: LoginData) => {
        setLoading(true);
        await authService.login(data);
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
