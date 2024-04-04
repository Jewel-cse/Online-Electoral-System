import { useState } from "react";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import { useNavigate } from "react-router-dom";

function CandidateDashboard() {

    let positionId = ''
    const navigate = useNavigate()

    function presidentList() {
        positionId = 'president'
        gotoCandidateList(positionId)
    }
    function mpList() {
        positionId = 'mp'
        gotoCandidateList(positionId)
    }
    function chairmanList() {
        positionId = 'chairman'
        gotoCandidateList(positionId)
    }
    function memberList() {
        positionId = 'member'
        gotoCandidateList(positionId)
    }

    function gotoCandidateList(positionId) {
        console.log(`goto to ${positionId}`)
        navigate(`/admin/candidates/${positionId}`);
    }

  return (
    <div className="container">
      <div className="flex justify-center flex-wrap m-5 gap-12">
        <div>
          <Card border="primary" style={{ width: "18rem" }}>
            <Card.Header className=" bg-blue-300 ">
              See President Candidate
            </Card.Header>
            <Card.Body>
              <Card.Title>President</Card.Title>
              <Card.Text>To manipulate President list </Card.Text>
              <Button variant="primary" onClick={presidentList}>
                <p style={{ color: "black" }}>Go</p>
              </Button>
            </Card.Body>
          </Card>
        </div>
        <div>
          <Card border="primary" style={{ width: "18rem" }}>
            <Card.Header className=" bg-blue-300 ">
              See MP Candidate
            </Card.Header>
            <Card.Body>
              <Card.Title>MP</Card.Title>
              <Card.Text>To manipulate mp list</Card.Text>
              <Button variant="primary" onClick={mpList} >
                <p style={{ color: "black" }}>Go </p>
              </Button>
            </Card.Body>
          </Card>
        </div>
      </div>
      <div className="flex justify-center flex-wrap m-5 gap-12">
        <div>
          <Card border="primary" style={{ width: "18rem" }}>
            <Card.Header className=" bg-blue-300 ">
              See Chairman Candidate
            </Card.Header>
            <Card.Body>
              <Card.Title>Chairman</Card.Title>
              <Card.Text>To manipulate Chairman list</Card.Text>
              <Button variant="primary" onClick={chairmanList} >
                <p style={{ color: "black" }}>Go </p>
              </Button>
            </Card.Body>
          </Card>
        </div>
        <div>
          <Card border="primary" style={{ width: "18rem" }}>
            <Card.Header className=" bg-blue-300 ">
              See Member Candidate
            </Card.Header>
            <Card.Body>
              <Card.Title>Member</Card.Title>
              <Card.Text>To manipulate President list</Card.Text>
              <Button variant="primary" onClick={memberList} >
                <p style={{ color: "black" }}>Go </p>
              </Button>
            </Card.Body>
          </Card>
        </div>
      </div>
    </div>
  );
}

export default CandidateDashboard;
