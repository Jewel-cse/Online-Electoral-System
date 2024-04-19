import React, { useState, useEffect } from "react";

import { PieChart, Pie, Sector } from "recharts";

import { retrieveWinnersApi } from "../../api/resultApiService";
import IndividualResultCompo from "./IndividualResultCompo";

const ResultComponent = () => {
  const [winners, setWinners] = useState([]);

  useEffect(() => {
    refreshWinners();
  }, []); // Fetch winners data when component mounts

  const refreshWinners = () => {
    retrieveWinnersApi()
      .then((response) => {
        setWinners(response.data);
      })
      .catch((error) => console.log(error));
  };

  const renderActiveShape = (props) => {
    const RADIAN = Math.PI / 180;
    const {
      cx,
      cy,
      midAngle,
      innerRadius,
      outerRadius,
      startAngle,
      endAngle,
      fill,
      payload,
      percent,
      value,
    } = props;
    const sin = Math.sin(-RADIAN * midAngle);
    const cos = Math.cos(-RADIAN * midAngle);
    const sx = cx + (outerRadius + 10) * cos;
    const sy = cy + (outerRadius + 10) * sin;
    const mx = cx + (outerRadius + 30) * cos;
    const my = cy + (outerRadius + 30) * sin;
    const ex = mx + (cos >= 0 ? 1 : -1) * 22;
    const ey = my;
    const textAnchor = cos >= 0 ? "start" : "end";

    return (
      <g>
        <text x={cx} y={cy} dy={8} textAnchor="middle" fill={fill}>
          Winner {payload.positionId}
        </text>
        <Sector
          cx={cx}
          cy={cy}
          innerRadius={innerRadius}
          outerRadius={outerRadius}
          startAngle={startAngle}
          endAngle={endAngle}
          fill={fill}
        />
        <Sector
          cx={cx}
          cy={cy}
          startAngle={startAngle}
          endAngle={endAngle}
          innerRadius={outerRadius + 6}
          outerRadius={outerRadius + 10}
          fill={fill}
        />
        <path
          d={`M${sx},${sy}L${mx},${my}L${ex},${ey}`}
          stroke={fill}
          fill="none"
        />
        <circle cx={ex} cy={ey} r={2} fill={fill} stroke="none" />

        <text
          x={ex + (cos >= 0 ? 1 : -1) * 12}
          y={ey}
          dy={18}
          textAnchor={textAnchor}
          fill="#999"
        >
          {`(${payload.symbol}  ${value}%)`}
        </text>
      </g>
    );
  };

  const [activeIndex, setActiveIndex] = useState(0);

  const onPieEnter = (_, index) => {
    setActiveIndex(index);
  };

  //    console.log("Winner is : ",winners);

  return (
    <>
      <div className="flex bg-gray-700 mb-5 rounded-xl ">
        <div style={{ marginLeft: "100px" }}>
          <PieChart width={700} height={500}>
            <Pie
              activeIndex={activeIndex}
              activeShape={renderActiveShape}
              data={winners}
              cx="400px"
              cy="400px"
              innerRadius={110}
              outerRadius={150}
              fill="#998ff8"
              dataKey="percentageOfVote"
              onMouseEnter={onPieEnter}
            />
          </PieChart>
        </div>
        <div className="flex flex-col p-9 m-10 heading ">
          <div>
            <h1 className="text-gray-400">The Summary of Election</h1>
          </div>
          <div>
            <h1 className="text-gray-500">Total Voter : </h1>
          </div>
          <div>
            <h1 className="text-gray-500">
              Vote Received(President) : {winners[3]?.totalVoteCast}
            </h1>
          </div>
          <div>
            <h1 className="text-gray-500">
              Vote Received(MP) : {winners[2]?.totalVoteCast}
            </h1>
          </div>
          <div>
            <h1 className="text-gray-500">
              Vote Received(Chairman) : {winners[0]?.totalVoteCast}
            </h1>
          </div>
          <div>
            <h1 className="text-gray-500">
              Vote Received(Member) : {winners[1]?.totalVoteCast}
            </h1>
          </div>
        </div>
      </div>
      <>
        <IndividualResultCompo />
      </>
    </>
  );
};

export default ResultComponent;
