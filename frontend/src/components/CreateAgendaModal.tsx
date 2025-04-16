import { registerAgenda } from "@/services/agendaServices";
import { useState } from "react";
import { toast } from "sonner";

interface Props {
  onClose: () => void;
}

export default function CreateAgendaModal({ onClose }: Props) {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");

  const handleCreate = () => {
    if (!title || !description) {
      toast.error("Preencha todos os campos");
      return;
    }

    registerAgenda({ title, description })
      .then(() => {
        toast.success("Pauta cadastrada com sucesso!");
        onClose();
        window.location.reload();
      })
      .catch((err) => {
        console.error(err);
        toast.error("Erro ao cadastrar pauta");
      });
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white p-6 rounded-md shadow-md w-full max-w-md">
        <h2 className="text-xl font-semibold mb-4">Nova Pauta</h2>
        <input
          type="text"
          placeholder="Título"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          className="w-full border px-3 py-2 rounded mb-4"
        />
        <textarea
          placeholder="Descrição"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          className="w-full border px-3 py-2 rounded mb-4"
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
            onClick={handleCreate}
          >
            Criar
          </button>
        </div>
      </div>
    </div>
  );
}
