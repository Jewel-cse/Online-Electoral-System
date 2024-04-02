import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import HeaderComponent from "./components/Header";
import HomeComponent from "./components/HomePage";
import DemoComponent from "./components/DemoCo";
import FooterComponent from "./components/Footer";
import VoterListComponent from "./components/Voter-list";
import AdminPanelComponent from "./components/AdminPanel";
import AdminLayout from "./components/AdminLayout";
import NotFound from "./components/NotFound";
import VoterComponent from "./components/Voter";
import VotineInterface from "./components/vote/VoteInterface";

import "bootstrap/dist/css/bootstrap.css";
import Ballot from "./components/vote/Ballot";
import CandidateList from "./components/candidate/Candidate-list";
import Candidate from "./components/candidate/Candidate";

export default function App() {
  
  return (
    <BrowserRouter>
      <Routes>
        <Route index element={<HomeComponent />} />
        <Route path="/admin" element={<AdminLayout />}>
          <Route path="voter-list" element={<VoterListComponent />} />
          <Route path="/admin/voter" element={<VoterComponent />} />
        </Route>
        <Route
          path="/admin/candidates/:positionId"
          element={<CandidateList/>}
        ></Route>
        <Route path="/admin/candidate/:id" element = {<Candidate/>}></Route>
        <Route
          path="/user/voting-interface"
          element={<VotineInterface />}
        ></Route>
        <Route
          path="/user/voting-interface/Ballot"
          element={<Ballot />}
        ></Route>

        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
}
