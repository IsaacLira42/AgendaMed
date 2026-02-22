"use client";
import Link from "next/link";
import { usePathname, useRouter } from "next/navigation";
import { useEffect, useState } from "react";

export const Navbar = () => {
  const pathname = usePathname() || "/";
  const router = useRouter();
  const [userName, setUserName] = useState<string | null>(null);

  useEffect(() => {
    const raw = localStorage.getItem("user");
    if (raw) {
      try {
        const parsed = JSON.parse(raw);
        setUserName(parsed?.nome || parsed?.name || parsed?.username || String(parsed));
      } catch {
        setUserName(raw);
      }
    }
  }, []);

  const linkClass = (path: string) => {
    const active = path === "/" ? pathname === "/" : pathname.startsWith(path);
    return `${active ? "font-medium text-azul-soft bg-blue-50" : "text-gray-500"} pl-4 py-2 pr-auto rounded-lg hover:bg-blue-50 cursor-pointer`;
  };

  function handleLogout() {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    setUserName(null);
    router.push("/");
  }

  return (
    <nav className="h-full w-52 fixed px-4 rounded-r-2xl border-2 border-blue-100 bg-background flex flex-col justify-between">
      <div>
        <h1 className="text-[28px] font-bold text-azul-corporativo pt-16 pb-8 cursor-pointer text-center">AgenMed</h1>
        <ul className="flex flex-col">
          <li>
            <Link href="/" className={linkClass("/") + " block w-full"}>Home</Link>
          </li>
          <li>
            <Link href="/agendar" className={linkClass("/agendar") + " block w-full"}>Agendar Consulta</Link>
          </li>
          <li>
            <Link href="/minhas-consultas" className={linkClass("/minhas-consultas") + " block w-full"}>Minhas Consultas</Link>
          </li>
        </ul>
      </div>

      <div className="mb-8">
        {userName ? (
          <div className="text-center">
            <div className="text-sm font-medium text-gray-900">{userName}</div>
            <button onClick={handleLogout} className="text-sm text-red-600 mt-2">Sair</button>
          </div>
        ) : (
          <div className="space-y-2">
            <Link href="/login" className="block w-full text-center bg-white border rounded px-3 py-2 text-azul-corporativo">Entrar</Link>
            <Link href="/register" className="block w-full text-center border rounded px-3 py-2">Registrar</Link>
          </div>
        )}
      </div>
    </nav>
  )
}