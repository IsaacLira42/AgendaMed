export async function http<T>(
    url: string,
    options?: RequestInit
): Promise<T> {
    const response = await fetch(`http://localhost:8080${url}`, {
        ...options,
        credentials: "include",
        headers: {
            "Content-Type": "application/json",
            ...options?.headers,
        },
    });

    // 1. Tratamento de Erro Centralizado
    if (!response.ok) {
        const errorText = await response.text();
        let errorMessage = "Erro na requisição";
        
        try {
            const errorJson = JSON.parse(errorText);
            errorMessage = errorJson.message || errorMessage;
        } catch {
            errorMessage = errorText || errorMessage;
        }
        
        throw new Error(errorMessage);
    }

    // 2. Verificação de "No Content" (Status 204 ou corpo vazio)
    if (response.status === 204) {
        return {} as T;
    }

    const contentType = response.headers.get("content-type");

    // 3. Processa apenas se o servidor confirmar que está enviando JSON
    if (contentType && contentType.includes("application/json")) {
        const text = await response.text();
        return text ? JSON.parse(text) : ({} as T);
    }

    return {} as T;  // Caso o backend retorne 200 OK mas sem corpo
}