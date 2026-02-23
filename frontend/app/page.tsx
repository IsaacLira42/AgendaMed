
"use client";
import Dashboard from "@/components/Dashboard";
import RequireAuth from "@/components/RequireAuth";

export default function Home() {
  return (
    <RequireAuth>
      <div className="ml-56 p-8">
        <Dashboard />
      </div>
    </RequireAuth>
  );
}
