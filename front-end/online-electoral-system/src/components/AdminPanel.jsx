import React from "react";
import { Link } from "react-router-dom";

//localhost: 8080/admin-panel

export default function AdminPanelComponent() {
  return (
    <div className="flex h-screen">
      {/* Left Side */}
      <div className="bg-gray-200 w-[20%] p-8  gap-20">
        <div className="text-blue-900 content-center p-4 text-center">JT</div>

        <ul className="space-y-2">
          <li>
            <Link href="#" className="block text-blue-500  hover:text-blue-700">
              Voter
            </Link>
          </li>
          <li>
            <a href="#" className="block text-blue-500 hover:text-blue-700">
              Candidate
            </a>
          </li>
          <li>
            <a href="#" className="block text-blue-500 hover:text-blue-700">
              Election Result
            </a>
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
            <button className="text-black bg-red-400 px-4 py-1 rounded hover:bg-red-600">
              logout
            </button>
          </div>
        </div>

        <div className="p-4">ghhhhhh</div>
      </div>
    </div>
  );
}
