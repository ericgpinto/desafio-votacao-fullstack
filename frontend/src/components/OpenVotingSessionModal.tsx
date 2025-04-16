import { useState } from "react";
import { openVotingSession } from "../services/agendaServices";
import { toast } from "sonner";

interface Props {
  agendaId: string;
  onClose: () => void;
}

export default function OpenVotingSessionModal({ agendaId, onClose }: Props) {
  const [closingTime, setClosingTime] = useState("");

  const handleOpen = () => {
    const payload = closingTime ? { voteClosingTime: closingTime } : {};

    openVotingSession(agendaId, payload)
      .then(() => {
        toast.success("Votação aberta com sucesso!");
        onClose();
        window.location.reload();
      })
      .catch((err) => {
        console.error(err);
        toast.error("Erro ao abrir a votação.");
      });
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white p-6 rounded-md shadow-md w-full max-w-md">
        <h2 className="text-xl font-semibold mb-4">Abrir Votação</h2>
        <label className="block mb-2 text-gray-700">
          Horário de encerramento (opcional)
        </label>
        <input
          type="datetime-local"
          value={closingTime}
          onChange={(e) => setClosingTime(e.target.value)}
          className="w-full px-3 py-2 border rounded mb-4"
        />
        <div className="flex justify-end space-x-2">
          <button
            className="bg-gray-400 hover:bg-gray-500 text-white px-4 py-2 rounded"
            onClick={onClose}
          >
            Cancelar
          </button>
          <button
            className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded"
            onClick={handleOpen}
          >
            Abrir
          </button>
        </div>
      </div>
    </div>
  );
}
