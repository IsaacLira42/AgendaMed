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

    if (!response.ok) {
        const errorText = await response.text();
        let errorMessage = "Erro na requisição";
        
        try {
            // Tenta extrair a mensagem se o erro for um JSON
            const errorJson = JSON.parse(errorText);
            errorMessage = errorJson.message || errorMessage;
        } catch {
            // Se não for JSON (ex: erro 500 ou texto puro), usa o texto da resposta
            errorMessage = errorText || errorMessage;
        }
        
        throw new Error(errorMessage);
    }

    const text = await response.text();
    
    // Retorna o JSON parseado apenas se houver conteúdo no corpo da resposta
    return text ? JSON.parse(text) : ({} as T);
}