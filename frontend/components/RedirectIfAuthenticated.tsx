"use client";

import { useEffect } from "react";
import { useRouter } from "next/navigation";
import { useAuth } from "@/context/AuthContext";

export default function RedirectIfAuthenticated({ children }: { children: React.ReactNode }) {
    const { isAuthenticated, loading } = useAuth();
    const router = useRouter();

    useEffect(() => {
        if (!loading && isAuthenticated) {
            router.push("/");
        }
    }, [isAuthenticated, loading, router]);

    if (loading || isAuthenticated) return null;

    return <>{children}</>;
}
