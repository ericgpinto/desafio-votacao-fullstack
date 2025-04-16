import axios from "axios";

const api = axios.create({
  baseURL: "https://desafio-votacao-backend.onrender.com/api/v1",
});

export default api;
