import React from "react";
import { Link, Outlet } from "react-router-dom";
import{useNavigate} from "react-router-dom"
import { useAuth } from "../security/AuthContext";

export default function AdminLayout({ children }) {
  // const positionId = 'president'
  
  const navigate = useNavigate()
  function exit() {
    navigate("/#")
  }
  return (
    <div className="flex min-h-screen">
      {/* Left Side */}
      <div className="bg-gray-200 w-[20%] p-8  gap-20">
        <div className="text-blue-900 content-center p-4 text-center">JT</div>

        <ul className="space-y-2">
          <li>
            <Link
              to="/admin/voter-list"
              className="block text-blue-500  hover:text-blue-700"
            >
              Voter
            </Link>
          </li>
          <li>
            <Link
              to="/admin/candidate-dashboard"
              className="block text-blue-500 hover:text-blue-700"
            >
              Candidate
            </Link>
          </li>
          <li>
            <Link
              to="/admin/election-result"
              className="block text-blue-500 hover:text-blue-700"
            >
              Election Result
            </Link>
          </li>
        </ul>
      </div>

      {/* Right Side */}
      <div className="flex-1">
        <div className="bg-brand_color py-1 px-1 flex justify-between items-center">
          <div></div>
          <div>
            <h2 className="text-lg text-white font-semibold mb-2 font-mono">
              Admin Panel
            </h2>
          </div>
          <div>
            <button className="text-black bg-red-400 px-4 py-1 rounded hover:bg-red-600" onClick={exit}>
              logout
            </button>
          </div>
        </div>

        <div className="p-4">
          <Outlet />
        </div>
      </div>
    </div>
  );
}
