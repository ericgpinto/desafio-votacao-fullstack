import axios from "axios";
import api from "./api";

export const getAllAssociates = () => api.get(`${"/votes"}/associates`);
export const vote = (
  agendaId: string,
  associateId: string,
  data: { vote: "YES" | "NO" }
) =>
  axios.post(`${"/votes"}/agenda/${agendaId}/associate/${associateId}`, data);
