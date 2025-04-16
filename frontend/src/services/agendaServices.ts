import axios from "axios";
import api from "./api";

export const getAllAgendas = () => api.get("/agendas");
export const getAgendaById = (id: string) => axios.get(`${"/agendas"}/${id}`);
export const openVotingSession = (
  id: string,
  data: { voteClosingTime?: string }
) => api.put(`${"/agendas"}/${id}/open-voting`, data);
export const getVoteResult = (id: string) =>
  api.get(`${"/agendas"}/${id}/counting-votes`);
export const registerAgenda = (data: { title: string; description: string }) =>
  api.post(`${"/agendas"}/register`, data);
