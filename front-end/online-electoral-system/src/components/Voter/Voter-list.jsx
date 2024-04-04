import React, { useEffect, useState } from "react";
import { FiEdit } from "react-icons/fi";
import { FaTrash } from "react-icons/fa";
import {deleteVoterApi,retrieveVoterListApi,} from "../../api/VoterApiService";
import { useNavigate, useParams } from "react-router-dom";

const VoterListComponent = () => {
  //show the list: get voter list

  const{id} = useParams()
  const [voters, setVoters] = useState([]);
  const [deleteMessage, setDeleteMessage] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    refreshVoters();
  }, [id]);
  function refreshVoters() {
    retrieveVoterListApi()
      .then((response) => {
        setVoters(response.data);
      })
      .catch((error) => console.log(error));
  }

  //delete voter

  function deleteVoter(voterId) {
    deleteVoterApi(voterId)
      .then(() => {
        setDeleteMessage(`Delete voter id with ${voterId}`);
        refreshVoters();
      })
      .catch((error) => console.log(error));
  }

  //add new Voter
  function addVoter() {
    console.log("add new -clicked");
    navigate("/admin/voter");
  }

  return (
    <div className="overflow-x-auto px-8 py-8">
      <div className="flex justify-between mb-4">
        <div>
          <h1>Voter list</h1>
        </div>
        
        <div>
          <button
            className=" bg-teal-500 px-4 py-1 rounded hover:bg-teal-600 text-white"
            onClick={addVoter}
          >
            Add new
          </button>
        </div>
      </div>
      <div>
        <table className="table-auto border-collapse border border-black w-full text-center ">
          <thead>
            <tr>
              <th className="px-4 py-2 bg-gray-200 border border-gray-500">
                Voter Id
              </th>
              <th className="px-4 py-2 bg-gray-200 border border-gray-500">
                Name
              </th>
              <th className="px-4 py-2 bg-gray-200 border border-gray-500">
                Status
              </th>
              <th className="px-4 py-2 bg-gray-200 border border-gray-500">
                Action
              </th>
            </tr>
          </thead>
          <tbody>
            {voters.map((voter) => {
              return (
                <tr key={voter.voterId}>
                  <td className="px-4 py-2 border border-gray-500">
                    {voter.voterId}
                  </td>
                  <td className="px-4 py-2 border border-gray-500">
                    {voter.name}
                  </td>
                  <td className="px-4 py-2 border border-gray-500">
                    <span
                      className={`${
                        voter.voted ? "bg-green-400 " : "bg-red-400 "
                      } text-xs rounded-xl px-3 py-1 text-white`}
                    >
                      {voter.voted.toString()}
                    </span>
                  </td>
                  <td className="space-x-6 w-[20%]">
                    <button
                      className="text-black bg-teal-600 px-4 py-1 rounded hover:bg-teal-700"
                      //onClick={() => updateVoter(voter.voterId)}
                    >
                      <FiEdit color="#fff" />
                    </button>
                    <button
                      className="text-black bg-red-500 px-4 py-1 rounded hover:bg-red-600"
                      onClick={() => deleteVoter(voter.voterId)}
                    >
                      <FaTrash color="#fff" />
                    </button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default VoterListComponent;
