
export function LoginComponent() {
  const [username, setUsername] = useState();
  function handleUsernameChange(event) {
    //console.log(event.target.value)
    setUsername(event.target.value);
  }

  const [password, setPassword] = useState();
  function handlePasswordChange(event) {
    //console.log(event.target.value)
    setPassword(event.target.value);
  }
  const [showSuccedMessage, setShowSuccedMessage] = useState(false);
  const [showFailedMessage, setShowFailedMessage] = useState(false);

  function handleSubmit() {
    console.log(username);
    console.log(password);

    if (username === "jewel" && password === "1234") {
      console.log("succeed");
      setShowSuccedMessage(true);
      setShowFailedMessage(false);
    } else {
      console.log("failed");
      setShowSuccedMessage(false);
      setShowFailedMessage(true);
    }
  }

  return (
    <div className="Login">
      {showSuccedMessage && (
        <div className="successMessage">Authenticated successfully</div>
      )}
      {showFailedMessage && (
        <div className="failedMessage">
          Authentication failed,please check your credentials
        </div>
      )}

      <div className="LogingForm">
        <div>
          <label>User Name</label>
          <input
            type="text"
            name="username"
            value={username}
            onChange={handleUsernameChange}
          ></input>
        </div>
        <div>
          <label>Password</label>
          <input
            type="password"
            name="password"
            value={password}
            onChange={handlePasswordChange}
          ></input>
        </div>
        <div>
          <button type="button" name="login" onClick={handleSubmit}>
            login
          </button>
        </div>
      </div>
    </div>
  );
}
