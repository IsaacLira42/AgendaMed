"use client";

import { useEffect } from "react";
import { useRouter, usePathname } from "next/navigation";
import { useAuth } from "@/context/AuthContext";

const routeConfig = {
    MEDICO: {
        allowed: ["/minhas-consultas", "/medico"],
        defaultRoute: "/minhas-consultas"
    },
    PACIENTE: {
        allowed: ["/", "/agendar-consulta"],
        defaultRoute: "/"
    }
} as const;

export default function RequireAuth({ children }: { children: React.ReactNode }) {
    const { isAuthenticated, loading, user } = useAuth();
    const router = useRouter();
    const pathname = usePathname();

    useEffect(() => {
        if (loading) return;

        if (!isAuthenticated) {
            router.push("/login");
            return;
        }

        if (!user) return;

        const config = routeConfig[user.tipo as keyof typeof routeConfig];

        if (!config) {
            router.push("/login");
            return;
        }

        const isAllowed = config.allowed.some(route =>
            pathname.startsWith(route)
        );

        if (!isAllowed) {
            router.push(config.defaultRoute);
        }

    }, [loading, isAuthenticated, user, pathname, router]);

    if (loading || !isAuthenticated) return null;

    return <>{children}</>;
}