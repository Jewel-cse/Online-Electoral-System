import React from "react";
import Button from "react-bootstrap/Button";
import Table from "react-bootstrap/Table";

const Ballot = () => {
  // Sample data of candidates
  const candidates = [
    { name: "Candidate 1", symbol: "Symbol 1" },
    { name: "Candidate 2", symbol: "Symbol 2" },
    { name: "Candidate 3", symbol: "Symbol 3" },
    // Add more candidates as needed
  ];

  const handleVoteClick = (candidateName) => {
    // Handle vote submission logic here
    console.log(`Voted for ${candidateName}`);
  };

  return (
    <div className="container">
      <h1 className="text-center my-4" style={{fontSize:'40px'}} >President Election</h1>
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
                  onClick={() => handleVoteClick(candidate.name)}
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
