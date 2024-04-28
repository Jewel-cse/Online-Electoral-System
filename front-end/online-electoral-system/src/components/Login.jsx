import React, { useState } from 'react';
import { useAuth } from '../security/AuthContext';
import { useNavigate } from 'react-router-dom';

function LoginComponent() {
  const { login,signUp ,issignup} = useAuth();
  const [voterId, setVoterId] = useState('');
  const [password, setPassword] = useState('');
  const [role,setRole] = useState('')
  const [error, setError] = useState('');
  const navigate = useNavigate();
  
  const handleLogin = async (event) => {
    event.preventDefault(); 

    if(issignup){   //already sign up-> do login
      const success = await login(voterId, password);
      if (!success) {
        setError('Login failed. Please try again.');
      }
      else{
        navigate("/#");
          // setTimeout(()=>{
          //   window.location.reload()
          // }, 500)
        }
    }
    else{  //do sign up
      const success = await signUp (voterId, password,role);
      if (!success) {
        setError('Sign up failed. Please try again.');
      }
      else{
        navigate("/login");
        }
    }
  };

  return (
    <div className="flex justify-center items-center h-screen">
      <div className="w-full max-w-md">
        <form onSubmit={handleLogin} className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">

          {issignup && <h2 className="text-2xl mb-4 text-center">Login</h2>}
          {!issignup && <h2 className="text-2xl mb-4 text-center">Sign Up</h2>}
          {error && <div className="text-red-500 mb-4 text-center">{error}</div>}
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="voterId">VoterId:</label>
            <input 
              type="text" 
              id="voterId" 
              value={voterId} 
              onChange={(e) => setVoterId(e.target.value)} 
              className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              placeholder="Enter your voterId"
            />
          </div>
          <div className="mb-6">
            <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="password">Password:</label>
            <input 
              type="password" 
              id="password" 
              value={password} 
              onChange={(e) => setPassword(e.target.value)} 
              className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              placeholder="Enter your password"
            />
          </div>
          {!issignup && 
              <div className="mb-4">
              <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="voterId">Role:</label>
              <input 
                type="text" 
                id="role" 
                value={role} 
                onChange={(e) => setRole(e.target.value)} 
                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                placeholder="Enter your role"
                />
              </div>
          }
          <div className="flex items-center justify-center">
            <button type="submit" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
              Login
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default LoginComponent;
