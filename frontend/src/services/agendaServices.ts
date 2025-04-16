import axios from "axios";

const API_BASE = "http://localhost:8080/api/v1/agendas";

export const getAllAgendas = () => axios.get(API_BASE);
export const getAgendaById = (id: string) => axios.get(`${API_BASE}/${id}`);
export const openVotingSession = (
  id: string,
  data: { voteClosingTime?: string }
) => axios.put(`${API_BASE}/${id}/open-voting`, data);
export const getVoteResult = (id: string) =>
  axios.get(`${API_BASE}/${id}/counting-votes`);
export const registerAgenda = (data: { title: string; description: string }) =>
  axios.post(`${API_BASE}/register`, data);
