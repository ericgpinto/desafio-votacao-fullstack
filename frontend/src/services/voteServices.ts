import axios from "axios";

const API_BASE = "http://localhost:8080/api/v1/votes";

export const getAllAssociates = () => axios.get(`${API_BASE}/associates`);
export const vote = (
  agendaId: string,
  associateId: string,
  data: { vote: "YES" | "NO" }
) =>
  axios.post(`${API_BASE}/agenda/${agendaId}/associate/${associateId}`, data);
