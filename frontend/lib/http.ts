export async function http<T>(
    url: string,
    options?: RequestInit
): Promise<T> {
    const token = localStorage.getItem("token");

    const response = await fetch(`http://localhost:8080${url}`, {
        ...options,
        headers: {
            "Content-Type": "application/json",
            Authorization: token ? `Bearer ${token}` : "",
            ...options?.headers,
        },
    });

    if (!response.ok) {
        throw new Error("Erro na requisição");
    }

    return response.json();
}