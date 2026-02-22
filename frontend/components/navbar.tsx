"use client";
import Link from "next/link";
import { usePathname } from "next/navigation";

export const Navbar = () => {
  const pathname = usePathname() || "/";

  const linkClass = (path: string) => {
    const active = path === "/" ? pathname === "/" : pathname.startsWith(path);
    return `${active ? "font-medium text-azul-soft bg-blue-50" : "text-gray-500"} pl-4 py-2 pr-auto rounded-lg hover:bg-blue-50 cursor-pointer`;
  };

  return (
    <nav className="h-full w-52 fixed px-4 rounded-r-2xl border-2 border-blue-100 bg-background">
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
    </nav>
  )
}