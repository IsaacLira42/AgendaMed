"use client";

import { useEffect } from "react";
import { useRouter } from "next/navigation";
import { useAuth } from "@/context/AuthContext";

export default function RequireAuth({ children, roles }: { children: React.ReactNode; roles?: string[] }) {
    const { isAuthenticated, loading, user } = useAuth();
    const router = useRouter();

    useEffect(() => {
        if (!loading && !isAuthenticated) {
            router.push("/login");
            return;
        }

        if (!loading && isAuthenticated && roles && user) {
            const userTipo = user.tipo;

            if (!userTipo || !roles.includes(userTipo)) {
                if (userTipo === "MEDICO") {
                    router.push("/minhas-consultas");
                } else {
                    router.push("/");
                }
            }
        }
    }, [isAuthenticated, loading, router, roles, user]);

    if (loading || !isAuthenticated) return null;

    return <>{children}</>;
}
