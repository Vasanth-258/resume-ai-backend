"use client";

import { useEffect, useState } from "react";

export default function Home() {

  const [candidates, setCandidates] = useState<any[]>([]);
  const [form, setForm] = useState({
    name: "",
    email: "",
    experience: ""
  });

  // GET — load all candidates
  useEffect(() => {
    fetch("http://localhost:8080/api/candidates")
        .then(res => res.json())
        .then(data => setCandidates(data))
        .catch(err => console.error(err));
  }, []);

  // POST — add candidate
  const handleSubmit = async (e: any) => {
    e.preventDefault();

    await fetch("http://localhost:8080/api/candidates", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form)
    });

    // reload list
    window.location.reload();
  };

  return (
      <div style={{ padding: 20 }}>
        <h1>Candidate List</h1>

        <ul>
          {candidates.map((c: any) => (
              <li key={c.id}>
                {c.name} — {c.email} — {c.experience} yrs
              </li>
          ))}
        </ul>

        <h2>Add Candidate</h2>

        <form onSubmit={handleSubmit}>
          <input
              placeholder="Name"
              onChange={e => setForm({ ...form, name: e.target.value })}
          /><br/>

          <input
              placeholder="Email"
              onChange={e => setForm({ ...form, email: e.target.value })}
          /><br/>

          <input
              placeholder="Experience"
              onChange={e => setForm({ ...form, experience: e.target.value })}
          /><br/>

          <button type="submit">Save</button>
        </form>
      </div>
  );
}
