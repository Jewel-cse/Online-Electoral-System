import React, { useEffect, useState } from "react";
import { FiEdit } from "react-icons/fi";
import { FaTrash } from "react-icons/fa";
import {
  deleteCandidateApi,
  retrieveAllCandidateApi,
} from "../../api/CandidateApiService";
import { useNavigate, useParams } from "react-router-dom";

const CandidateList = () => {
  const { positionId } = useParams();
  const [candidates, setCandidate] = useState([]);
  const [deleteMessage, setDeleteMessage] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    refreshCandidate();
  }, [positionId]);
  function refreshCandidate() {
    retrieveAllCandidateApi(positionId)
      .then((response) => {
        setCandidate(response.data);
      })
      .catch((error) => console.log(error));
  }

  //add candidate function
  function addCandidate() {
    console.log("add new button clicked!!!");
    navigate(`/admin/candidate/id/-1`);
  }

  function updateCandidate(id) {
    console.log('update button clicked!!')
    navigate(`/admin/candidate/id/${id}`)
  }

  //delete candidate function
  function deleteCandidate(id) {
    console.log(`you clicked delete button of ${id}`);
    deleteCandidateApi(id)
      .then(() => {
        setDeleteMessage(`Delete candidate with id ${id}`);
        refreshCandidate();
      })
      .catch((error) => {
        console.log(error);
      });
  }
  return (
    <div className="overflow-x-auto px-8 py-8">
      <div className="flex justify-between mb-4">
        <div>
          <h1 className="heading">{positionId} list</h1>
        </div>
        <div>
          {deleteMessage && <h2 style={{color:"red"}}>{ deleteMessage}</h2>}
        </div>
        <div>
          <button
            className=" bg-teal-500 px-4 py-1 rounded hover:bg-teal-600 text-white"
            onClick={addCandidate}
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
                Name
              </th>
              <th className="px-4 py-2 bg-gray-200 border border-gray-500">
                Symbol
              </th>
              <th className="px-4 py-2 bg-gray-200 border border-gray-500">
                Action
              </th>
            </tr>
          </thead>
          <tbody>
            {candidates.map((Candidate) => {
              return (
                <tr key={Candidate.id}>
                  <td className="px-4 py-2 border border-gray-500">
                    {Candidate.name}
                  </td>
                  <td className="px-4 py-2 border border-gray-500">
                    {Candidate.symbol}
                  </td>

                  <td className="space-x-6 w-[20%]">
                    <button
                      className="text-black bg-teal-600 px-4 py-1 rounded hover:bg-teal-700"
                      onClick={() => updateCandidate(Candidate.id)}
                    >
                      <FiEdit color="#fff" />
                    </button>
                    <button
                      className="text-black bg-red-500 px-4 py-1 rounded hover:bg-red-600"
                      onClick={() => deleteCandidate(Candidate.id)}
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

export default CandidateList;
