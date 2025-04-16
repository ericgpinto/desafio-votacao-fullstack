import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getAgendaById } from "../services/agendaServices";
import { getAllAssociates, vote } from "../services/voteServices";
import { toast } from "sonner";

interface Agenda {
  id: string;
  title: string;
  description: string;
  voteOpeningTime: string | null;
  voteClosingTime: string | null;
}

interface Associate {
  id: string;
  name: string;
}

export default function AgendaDetail() {
  const { id } = useParams();
  const [agenda, setAgenda] = useState<Agenda | null>(null);
  const [associates, setAssociates] = useState<Associate[]>([]);
  const [selectedAssociate, setSelectedAssociate] = useState<string>("");
  const [voteValue, setVoteValue] = useState<"YES" | "NO" | "">("");
  const navigate = useNavigate();

  useEffect(() => {
    if (!id) return;

    getAgendaById(id)
      .then((res) => setAgenda(res.data))
      .catch((err) => console.error(err));

    getAllAssociates()
      .then((res) => setAssociates(res.data))
      .catch((err) => console.error(err));
  }, [id]);

  const handleVote = () => {
    if (!id || !selectedAssociate || !voteValue) return;

    vote(id, selectedAssociate, { vote: voteValue })
      .then(() => {
        toast.success("Voto computado com sucesso!");
        navigate("/");
      })
      .catch((err) => {
        console.error(err);
        toast.error("Erro ao computar o voto");
      });
  };

  return (
    <div className="p-6 max-w-2xl mx-auto bg-white rounded shadow-md">
      <h1 className="text-2xl font-bold mb-4">{agenda?.title}</h1>
      <p className="text-gray-600 mb-4">{agenda?.description}</p>

      <div className="mb-4">
        <label className="block font-medium mb-2">Associado</label>
        <select
          className="w-full border px-3 py-2 rounded"
          value={selectedAssociate}
          onChange={(e) => setSelectedAssociate(e.target.value)}
        >
          <option value="">Selecione um associado</option>
          {associates.map((assoc) => (
            <option key={assoc.id} value={assoc.id}>
              {assoc.name}
            </option>
          ))}
        </select>
      </div>

      <div className="mb-4">
        <label className="block font-medium mb-2">Voto</label>
        <div className="flex gap-4">
          <label>
            <input
              type="radio"
              value="SIM"
              checked={voteValue === "YES"}
              onChange={() => setVoteValue("YES")}
            />{" "}
            Sim
          </label>
          <label>
            <input
              type="radio"
              value="NAO"
              checked={voteValue === "NO"}
              onChange={() => setVoteValue("NO")}
            />{" "}
            Não
          </label>
        </div>
      </div>

      <div className="flex justify-end space-x-2">
        <button
          onClick={() => navigate("/")}
          className="px-4 py-2 bg-gray-400 hover:bg-gray-500 text-white rounded"
        >
          Cancelar
        </button>
        <button
          onClick={handleVote}
          className="px-4 py-2 bg-green-500 hover:bg-green-600 text-white rounded"
        >
          Confirmar
        </button>
      </div>
    </div>
  );
}
