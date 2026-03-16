import { http } from "@/lib/http";
import { RegisterData, LoginData } from "@/types";

export const authService = {
    async login(data: LoginData) {
        return await http<any>("/auth/login", {
            method: "POST",
            body: JSON.stringify(data),
        });
    },

    async me() {
        return await http<any>("/usuarios/me");
    },

    async register(data: RegisterData) {
        await http("/auth/register", {
            method: "POST",
            body: JSON.stringify(data),
        });
    },

    async logout() {
        // Geralmente para cookies, chamamos o backend para invalidar
        try {
            await http("/auth/logout", { method: "POST" });
        } catch (e) {
            console.error("Erro ao deslogar no backend", e);
        }
    }
};
