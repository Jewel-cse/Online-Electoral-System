import { createContext, useContext, useState, useEffect } from "react";
import {jwtDecode } from "jwt-decode";

export const AuthContext = createContext();
export const useAuth = () => useContext(AuthContext);

 export default function AuthProvider({ children }) {
  const [user, setUser] = useState(null);

  // Check if user is authenticated on component mount
  useEffect(() => {
    const token = localStorage.getItem("jwt");
    if (token) {
      const decoded = jwtDecode(token);
      const currentTime = Date.now() / 1000; 
      if (decoded.exp && decoded.exp < currentTime) {
        setUser(null);
        localStorage.removeItem("jwt");
      } else {
        setUser(decoded);
      }
    }
  }, []);

  // Function to login with JWT
  async function login(voterId, password) {
    try {
      const response = await fetch("http://localhost:8080/auth/sign-in", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({voterId, password })
      });
    
      if (!response.ok) {
        throw new Error("Invalid credentials");
      }
    
      const responseData = await response.json(); 
      //console.log(responseData)
      const { token } = responseData; // Destructure token from responseData
  
      //console.log("Received token:", token); 
  
      const decoded = jwtDecode(token);
  
      setUser(decoded);
      localStorage.setItem("jwt", token);
      return true;
    } catch (error) {
      console.error("Login failed:", error.message);
      return false;
    }
  }

  // Function to sign up
  async function signUp(voterId, password) {
    try {
      const response = await fetch("http://localhost:8080/auth/sign-up", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ voterId, password })
      });

      if (!response.ok) {
        throw new Error("Sign-up failed");
      }

      // Assuming sign-up was successful and no response body is expected
      return true;
    } catch (error) {
      console.error("Sign-up failed:", error.message);
      return false;
    }
  }

  // Function to logout
  function logout() {
    //console.log('logout')
    setUser(null);
    localStorage.removeItem("jwt"); // Remove token from localStorage on logout
  }

  
  return (
    <AuthContext.Provider value={{ user, login, signUp, logout }}>
      {children}
    </AuthContext.Provider>
  );
}