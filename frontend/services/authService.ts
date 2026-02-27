import { http } from "@/lib/http";
import { RegisterData, LoginData } from "@/types";

export const authService = {
    async login(data: LoginData) {
        const response: { token: string } = await http("/auth/login", {
            method: "POST",
            body: JSON.stringify(data),
        });

        localStorage.setItem("token", response.token);
        return response;
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

    logout() {
        localStorage.removeItem("token");
    }
};