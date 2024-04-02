import React from "react";

const HeaderComponent = () => {
  return (
    <header>
      <h1 className="text-black text-3xl text-center pt-8">Online Voting System</h1>
      <nav className="flex justify-between px-8 py-4 ">
        <ul className="flex gap-12">
          <li>
            <a href="#">Home</a>
          </li>
          <li>
            <a href="#">About</a>
          </li>
          <li>
            <a href="#">Contact</a>
          </li>
        </ul>
        <ul className="flex gap-12">
          <li>
            <a href="#">Logout</a>
          </li>
          <li>
            <a href="#">Login</a>
          </li>
        </ul>
      </nav>
    </header>
  );
};

export default HeaderComponent;
