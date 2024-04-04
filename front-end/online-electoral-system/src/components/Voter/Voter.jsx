import { useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { ErrorMessage, Field, Formik,Form } from "formik";
import { addnewVoterApi, retrieveVoterApi } from "../../api/VoterApiService";

export default function VoterComponent() {
  //const { id } = useParams();
  const [name, setName] = useState("");
  const [voterId,setVoterId] = useState()
  const [password, setPassword] = useState("");
  const [voted, setVoted] = useState(false);

  const navigate = useNavigate()

  // useEffect(
  //   () => retrieveVoterApi(),
  //   [voterId] //id change holei retrieveTodo() call hobe, [id] dependency array
  // );

  // function retrieveVoter() {
  //   retrieveVoterApi(voterId)
  //     .then((response) => {
  //       setName(response.data.name);
  //       setPassword(response.data.password);
  //       setVoted(response.data.voted);
  //       console.log(response.data);
  //     })
  //     .catch((error) => console.log(error));
  // }

  function onSubmit(values) {
    console.log(values, "-for submit");
    const voter = {
      voterId: values.voterId,
      name: values.name,
      password: values.password,
      voted: false,
    };
    
    addnewVoterApi(voter)
      .then(() => {
        navigate("/admin/voter-list");
      })
      .catch((error) => console.log(error));
  }

  function validate(values) {
    let error = {};
    if (values.voterId < 1000) error.voterId = "Enter the integer after 1000";
    if (!values.password) error.password = "enter valid password";
    if (!values.name) error.name = "enter valid name";

    console.log(values);
    return error;
  }

  return (
    <div className="container">
      <h2>Enter Voter details</h2>

      <div>
        <Formik
          initialValues={{ voterId, name, password }}
          enableReinitialize={true}
          onSubmit={onSubmit}
          validate={validate}
          validateOnChange={false}
          validationOnBlur={false}
        >
          {(props) => (
            <Form>
              <ErrorMessage
                voterId="voterId"
                component="div"
                className="alert alert-warning"
              />

              <ErrorMessage
                name="name"
                component="div"
                className="alert alert-warning"
              />
              <ErrorMessage
                password="password"
                component="div"
                className="alert alert-warning"
              />

              <fieldset className="from-group">
                <label>VoterId</label>
                <Field type="number" className="form-control" name="voterId"></Field>
              </fieldset>
              <fieldset className="from-group">
                <label>Name</label>
                <Field
                  type="text"
                  className="form-control"
                  name="name"
                ></Field>
              </fieldset>
              <fieldset className="from-group">
                <label>Password</label>
                <Field
                  type="text"
                  className="form-control"
                  name="password"
                ></Field>
              </fieldset>
              <div>
                <button
                  style={{
                    background: "blue",
                    display: "flex", // Set display to flex
                    justifyContent: "center", // Center the button horizontally
                    alignItems: "center", // Center the button vertically
                    width: "100px",
                    margin: "0 auto",
                  }}
                  className="btn btn-success m-5 px-8 "
                  type="submit"
                >
                  Save
                </button>
              </div>
            </Form>
          )}
        </Formik>
      </div>
    </div>
  );
}
