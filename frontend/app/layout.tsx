import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";
import "./globals.css";
import { Navbar } from "@/components/navbar";

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "AgendaMed",
  description: "Aplicação para agendamento de consultas médicas",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="pt-br">
    <body className={`${geistSans.variable} ${geistMono.variable} antialiased`}>
      <Navbar />
      <main className="bg-slate-50 min-h-screen">
        {children}
      </main>
    </body>
    </html>
  );
}
