import  { useEffect, useState } from "react";
import { retrieveResult } from "../../apiservice/resultApiService";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from "recharts";


const IndividualResultCompo = () => {
  const [presidentList, setPresidentList] = useState([]);
  const [mpList, setMpList] = useState([]);
  const [chairmanList, setChairmanList] = useState([]);
  const [memberList, setMemberList] = useState([]);

  useEffect(() => {
    refreshCandidate();
  }, []);

  const refreshCandidate = () => {
    const positions = ["president", "mp", "chairman", "member"];

    positions.forEach((positionId) => {
      retrieveResult(positionId)
        .then((response) => {
          switch (positionId) {
            case "president":
              setPresidentList(response.data);
              break;
            case "mp":
              setMpList(response.data);
              break;
            case "chairman":
              setChairmanList(response.data);
              break;
            case "member":
              setMemberList(response.data);
              break;
            default:
              break;
          }
        })
        .catch((error) =>
          console.log(`Error fetching ${positionId} data: `, error)
        );
    });
  };

//   console.log(presidentList);

  return (
    <div
      style={{
        display: "flex",
        flexWrap: "wrap",
        justifyContent: "space-around",
      }}
    >
      <div style={{ width: "35%" }}>
        <h2 style={{ textAlign: "center" }}>President</h2>
        <ResponsiveContainer aspect={4.0 / 3.0}>
          <BarChart
            data={presidentList}
            margin={{ top: 20, right: 30, left: 20, bottom: 20 }}
          >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey={(item) => `${item.id.symbol}`} />
            <YAxis />
            <Tooltip />
            <Bar dataKey="numberOfVote" fill="#8804d8" />
          </BarChart>
        </ResponsiveContainer>
      </div>

      <div style={{ width: "35%" }}>
        <h2 style={{ textAlign: "center" }}>Mp</h2>
        <ResponsiveContainer aspect={4.0 / 3.0}>
          <BarChart
            data={mpList}
            margin={{ top: 20, right: 30, left: 20, bottom: 20 }}
          >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey={(item) => `${item.id.symbol}`} />
            <YAxis />
            <Tooltip />

            <Bar dataKey="numberOfVote" fill="#82ca9d" />
          </BarChart>
        </ResponsiveContainer>
      </div>

      <div style={{ width: "35%" }}>
        <h2 style={{ textAlign: "center" }}>Chairman</h2>
        <ResponsiveContainer aspect={4.0 / 3.0}>
          <BarChart
            data={chairmanList}
            margin={{ top: 20, right: 30, left: 20, bottom: 20 }}
          >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey={(item) => `${item.id.symbol}`} />
            <YAxis />
            <Tooltip />

            <Bar dataKey="numberOfVote" fill="#ffc658" />
          </BarChart>
        </ResponsiveContainer>
      </div>

      <div style={{ width: "35%" }}>
        <h1 style={{ textAlign: "center" }}>Member </h1>
        <ResponsiveContainer aspect={4.0 / 3.0}>
          <BarChart
            data={memberList}
            margin={{ top: 20, right: 30, left: 20, bottom: 20 }}
          >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey={(item) => `${item.id.symbol}`} />
            <YAxis />
            <Tooltip />
            <Bar dataKey="numberOfVote" fill="#ff7300" />
          </BarChart>
        </ResponsiveContainer>
      </div>
    </div>
  );
};

export default  IndividualResultCompo;