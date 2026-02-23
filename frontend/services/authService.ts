import { http } from "@/lib/http";

export const authService = {
    async login(email: string, senha: string) {
        const data: { token: string } = await http("/auth/login", {
            method: "POST",
            body: JSON.stringify({ email, senha }),
        });

        localStorage.setItem("token", data.token);
        return data;
    },

    async me() {
        return await http<any>("/usuarios/me");
    },

    logout() {
        localStorage.removeItem("token");
    }
};