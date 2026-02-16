export const Navbar = () => {
  return (
    <nav className="h-full w-52 fixed px-4 rounded-r-2xl border-2 border-blue-100 bg-background">
        <h1 className="text-[28px] font-bold text-azul-corporativo pt-16 pb-8 cursor-pointer text-center">AgenMed</h1>
        <ul className="flex flex-col">
            <li className="font-medium text-azul-soft pl-4 py-2 pr-auto rounded-lg bg-blue-50 hover:bg-blue-50 cursor-pointer">Home</li>
            <li className="text-gray-500 pl-4 py-2 pr-auto rounded-lg hover:bg-blue-50 cursor-pointer">Agendar Consulta</li>
            <li className="text-gray-500 pl-4 py-2 pr-auto rounded-lg hover:bg-blue-50 cursor-pointer">Minhas Consultas</li> 
        </ul>
    </nav>
  )
}