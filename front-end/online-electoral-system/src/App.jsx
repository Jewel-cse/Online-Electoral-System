import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import HeaderComponent from "./components/Header";
import HomeComponent from "./components/HomePage";
import DemoComponent from "./components/DemoCo";
import FooterComponent from "./components/Footer";
import VoterListComponent from "./components/Voter/Voter-list";
import AdminPanelComponent from "./components/AdminPanel";
import AdminLayout from "./components/AdminLayout";
import NotFound from "./components/NotFound";
import VoterComponent from "./components/Voter/Voter";
import VotineInterface from "./components/vote/VoteInterface";

import "bootstrap/dist/css/bootstrap.css";
import Ballot from "./components/vote/Ballot";
import CandidateList from "./components/candidate/Candidate-list";
import Candidate from "./components/candidate/Candidate";
import CandidateDashboard from "./components/candidate/CandidateDashboard";

export default function App() {
  return (
    <BrowserRouter>
      {/* <HeaderComponent/> */}
      <Routes>
        <Route index element={<HomeComponent />} />
        {/* Inside admin panel */}
        <Route path="/admin" element={<AdminLayout />}>
          {/* voter operation */}
          <Route path="voter-list" element={<VoterListComponent />} />
          <Route path="voter/:id" element={<VoterComponent/>} />
          {/* candidateoperation */}
          <Route path="candidate-dashboard" element = {<CandidateDashboard/>}/>
          <Route path="candidates/:positionId" element={<CandidateList />} />
          <Route path="candidate/id/:id" element={<Candidate />} />
        </Route>

        {/* vote inter face */}
        <Route path="/user/voting-interface" element={<VotineInterface />} />
        {/* <Route path="/user/voting-interface/Ballot" element={<Ballot />} /> */}

        <Route path="*" element={<NotFound />} />
      </Routes>
      {/* <FooterComponent/> */}
    </BrowserRouter>
  );
}
