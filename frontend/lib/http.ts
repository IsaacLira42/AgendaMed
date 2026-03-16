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
        throw new Error("Erro na requisição");
    }

    return response.json();
}
