import React from "react";
import { Link } from "react-router-dom";

const HeaderComponent = () => {
  return (
    <header>
      <h1 className="text-black text-3xl text-center pt-8">
        Online Voting System
      </h1>
      <nav className="flex justify-between px-8 py-4 ">
        <ul className="flex gap-12">
          <li>
            {/* <a href="#">Home</a> */}
            <Link className="nav-link" to="#">
              Home
            </Link>
          </li>
          <li>
            <Link className="nav-link" to="#">
              Guide
            </Link>
            {/* <a href="#">Guide</a> */}
          </li>
          <li>
            <Link className="nav-link" to="/admin">
              Admin
            </Link>
            {/* <a href="#">Contact</a> */}
          </li>
        </ul>
        <ul className="flex gap-12">
          <li>
            <Link className="nav-link" to="#">
              logout
            </Link>
            
          </li>
          <li>
            <Link className="nav-link" to="#">login</Link>
          </li>
        </ul>
      </nav>
    </header>
  );
};

export default HeaderComponent;
