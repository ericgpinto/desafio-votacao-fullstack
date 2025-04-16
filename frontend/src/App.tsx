import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import AgendaList from "./components/AgendaList";
import AgendaDetail from "./components/AgendaDetail";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<AgendaList />} />
        <Route path="/agendas/:id" element={<AgendaDetail />} />
      </Routes>
    </Router>
  );
}

export default App;
