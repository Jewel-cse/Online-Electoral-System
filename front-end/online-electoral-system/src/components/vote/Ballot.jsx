import React, { useEffect, useState } from "react";
import Button from "react-bootstrap/Button";
import Table from "react-bootstrap/Table";
import { retrieveAllCandidateApi } from "../../apiservice/CandidateApiService";
import { processVote} from "../../apiservice/votingApiService";
import { useNavigate } from "react-router-dom";

const Ballot = ({ positionId }) => {
  const [candidates, setCandidates] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    refreshCandidates();
  }, [positionId]);

  function refreshCandidates() {
    retrieveAllCandidateApi(positionId)
      .then((response) => {
        setCandidates(response.data);
      })
      .catch((error) => console.log(error));
  }

  const handleVoteClick = (symbol) => {
    console.log(`Voted for ${symbol}`);
    //retrieveCandidateApi(voterId, positionId, symbol)
      const voteData = {
        positionId: positionId,
        symbol: symbol
      };

    processVote(voteData)
      .then(() => {
        navigate("/user/voting-interface");
      })
      .catch((error) => console.log(error));
  };

  return (
    <div className="container">
      <h1 className="text-center my-4" style={{ fontSize: "40px"}}>
        {positionId} Election
      </h1>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th style={{ textAlign: "center" }}> Name</th>
            <th style={{ textAlign: "center" }}>Symbol</th>
            <th style={{ textAlign: "center" }}>Action</th>
          </tr>
        </thead>
        <tbody>
          {candidates.map((candidate, index) => (
            <tr key={index}>
              <td style={{ textAlign: "center" }}>{candidate.name}</td>
              <td style={{ textAlign: "center" }}>{candidate.symbol}</td>
              <td style={{ textAlign: "center" }}>
                <Button
                  variant="info"
                  onClick={() => handleVoteClick(candidate.symbol)}
                  style={{ color: "black" }}
                >
                  Do Vote
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default Ballot;
