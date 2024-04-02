import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";

function VoteInterface() {
  return (
    <div className="container">
      <div className="flex justify-center flex-wrap m-5 gap-12">
        <div>
          <Card border="primary" style={{ width: "18rem" }}>
            <Card.Header className=" bg-blue-300 ">
              Cast President Vote
            </Card.Header>
            <Card.Body>
              <Card.Title>President</Card.Title>
              <Card.Text>Do vote to your favourite President</Card.Text>
              <Button variant="primary">
                <p style={{ color: "black" }}>Go somewhere</p>
              </Button>
            </Card.Body>
          </Card>
        </div>
        <div>
          <Card border="primary" style={{ width: "18rem" }}>
            <Card.Header className=" bg-blue-300 ">Cast MP Vote</Card.Header>
            <Card.Body>
              <Card.Title>MP</Card.Title>
              <Card.Text>Do vote to your favourite Mp</Card.Text>
              <Button variant="primary">
                <p style={{ color: "black" }}>Go somewhere</p>
              </Button>
            </Card.Body>
          </Card>
        </div>
      </div>
      <div className="flex justify-center flex-wrap m-5 gap-12">
        <div>
          <Card border="primary" style={{ width: "18rem" }}>
            <Card.Header className=" bg-blue-300 ">
              Cast Chairman Vote
            </Card.Header>
            <Card.Body>
              <Card.Title>Chairman</Card.Title>
              <Card.Text>Do vote to your favourite chairman</Card.Text>
              <Button variant="primary">
                <p style={{ color: "black" }}>Go somewhere</p>
              </Button>
            </Card.Body>
          </Card>
        </div>
        <div>
          <Card border="primary" style={{ width: "18rem" }}>
            <Card.Header className=" bg-blue-300 ">
              Cast Member Vote
            </Card.Header>
            <Card.Body>
              <Card.Title>Member</Card.Title>
              <Card.Text>Do vote to your favourite member</Card.Text>
              <Button variant="primary">
                <p style={{ color: "black" }}>Go somewhere</p>
              </Button>
            </Card.Body>
          </Card>
        </div>
      </div>
    </div>
  );
}

export default VoteInterface;
