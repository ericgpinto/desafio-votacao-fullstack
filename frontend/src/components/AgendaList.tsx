import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import OpenVotingSessionModal from "./OpenVotingSessionModal";
import { getAllAgendas, getVoteResult } from "../services/agendaServices";

interface Agenda {
  id: string;
  title: string;
  description: string;
  voteOpeningTime: string | null;
  voteClosingTime: string | null;
}

interface VoteResult {
  totalVotes: number;
  countingYesVotes: number;
  countingNoVotes: number;
}

export default function AgendaList() {
  const [agendas, setAgendas] = useState<Agenda[]>([]);
  const [selectedAgendaId, setSelectedAgendaId] = useState<string | null>(null);
  const [showModal, setShowModal] = useState(false);
  const [results, setResults] = useState<{ [key: string]: VoteResult }>({});

  const navigate = useNavigate();

  useEffect(() => {
    getAllAgendas()
      .then((res) => setAgendas(res.data))
      .catch((err) => console.error(err));
  }, []);

  const handleVoteClick = (agendaId: string) => {
    navigate(`/agendas/${agendaId}`);
  };

  const handleOpenVotingClick = (agendaId: string) => {
    setSelectedAgendaId(agendaId);
    setShowModal(true);
  };

  const handleShowResultsClick = (agendaId: string) => {
    getVoteResult(agendaId)
      .then((res) => {
        setResults((prev) => ({ ...prev, [agendaId]: res.data }));
      })
      .catch((err) => console.error(err));
  };

  const isVotingOpen = (agenda: Agenda) => {
    if (!agenda.voteOpeningTime || !agenda.voteClosingTime) return false;
    const now = new Date();
    const opening = new Date(agenda.voteOpeningTime);
    const closing = new Date(agenda.voteClosingTime);
    return now >= opening && now <= closing;
  };

  const isVotingClosed = (agenda: Agenda) => {
    if (!agenda.voteClosingTime) return false;
    return new Date() > new Date(agenda.voteClosingTime);
  };

  const formatDate = (dateStr: string | null) => {
    if (!dateStr) return "Votação ainda não foi aberta";
    const date = new Date(dateStr);
    return date.toLocaleString("pt-BR", {
      dateStyle: "short",
      timeStyle: "short",
    });
  };

  return (
    <div className="p-8">
      <h1 className="text-2xl font-bold mb-6">Pautas</h1>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {agendas.map((agenda) => (
          <div
            key={agenda.id}
            className="bg-white shadow-md rounded-md p-6 flex flex-col justify-between"
          >
            <div>
              <h2 className="text-lg font-semibold mb-2">{agenda.title}</h2>
              <p className="text-gray-700 text-sm mb-2">{agenda.description}</p>

              <p className="text-sm text-gray-600">
                <strong>Início:</strong> {formatDate(agenda.voteOpeningTime)}
              </p>
              <p className="text-sm text-gray-600">
                <strong>Fim:</strong> {formatDate(agenda.voteClosingTime)}
              </p>
            </div>

            <div className="mt-4 flex flex-wrap gap-2">
              {!agenda.voteOpeningTime && !agenda.voteClosingTime ? (
                <button
                  className="bg-blue-500 hover:bg-blue-600 text-white px-3 py-1 rounded text-sm"
                  onClick={() => handleOpenVotingClick(agenda.id)}
                >
                  Abrir votação
                </button>
              ) : isVotingOpen(agenda) ? (
                <button
                  className="bg-green-500 hover:bg-green-600 text-white px-3 py-1 rounded text-sm"
                  onClick={() => handleVoteClick(agenda.id)}
                >
                  Votar
                </button>
              ) : isVotingClosed(agenda) ? (
                <button
                  className="bg-gray-600 hover:bg-gray-700 text-white px-3 py-1 rounded text-sm"
                  onClick={() => handleShowResultsClick(agenda.id)}
                >
                  Ver resultados
                </button>
              ) : null}
            </div>

            {results[agenda.id] && (
              <div className="mt-4 bg-gray-100 p-3 rounded text-sm">
                <p>
                  <strong>Total de Votos:</strong>{" "}
                  {results[agenda.id].totalVotes}
                </p>
                <p>
                  ✅ <strong>Sim:</strong> {results[agenda.id].countingYesVotes}
                </p>
                <p>
                  ❌ <strong>Não:</strong> {results[agenda.id].countingNoVotes}
                </p>
              </div>
            )}
          </div>
        ))}
      </div>

      {showModal && selectedAgendaId && (
        <OpenVotingSessionModal
          agendaId={selectedAgendaId}
          onClose={() => {
            setShowModal(false);
            setSelectedAgendaId(null);
          }}
        />
      )}
    </div>
  );
}
