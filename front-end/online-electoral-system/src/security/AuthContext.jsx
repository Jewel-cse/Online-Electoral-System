import { createContext, useContext, useState, useEffect } from "react";
import { jwtDecode } from "jwt-decode";

export const AuthContext = createContext();
export const useAuth = () => useContext(AuthContext);

export default function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const[issignup,setIsignup] = useState(false);

  // // Check if user is authenticated on component mount
  //  useEffect(() => {
  //   const token =  localStorage.getItem("accessToken");
  //   if (token) {
  //     console.log({token})
  //     const decoded = token ? jwtDecode(token): {};
  //     const currentTime = Date.now() / 1000;
  //     if (decoded.exp && decoded.exp < currentTime) {
  //       setUser(null);
  //       localStorage.removeItem("accessToken");
  //     } else {
  //       setUser(decoded);
  //     }
  //   }
  // }, []);

  // Function to login with accessToken
  async function login(voterId, password) {
    try {
      const response = await fetch("http://localhost:8080/auth/sign-in", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ voterId, password }),
      });

      if (!response.ok) {
        throw new Error("Invalid credentials");
      }

      const responseData = await response.json();
      const { token,refreshToken } = responseData; // Destructure token from responseData

      if(!token)  return false;
      const decoded =jwtDecode(token);

      setUser(decoded);
      localStorage.setItem("accessToken", token);
      localStorage.setItem("refreshToken",refreshToken);

      return true;
    } catch (error) {
      console.error("Login failed:", error.message);
      return false;
    }
  }

  // Function to sign up
  async function signUp(voterId, password,role) {
    try {
      const response = await fetch("http://localhost:8080/auth/sign-up", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ voterId, password,role }),
      });

      if (!response.ok) {
        throw new Error("Sign-up failed");
      }
      setIsignup(true);
      return true;
    } catch (error) {
      console.error("Sign-up failed:", error.message);
      return false;
    }
  }

  // Function to logout
  function logout() {
    setUser(null);
    localStorage.removeItem("accessToken");
    localStorage.removeItem("refreshToken");
  }

  return (
    <AuthContext.Provider value={{ user,issignup, login, signUp, logout }}>
      {children}
    </AuthContext.Provider>
  );
}
