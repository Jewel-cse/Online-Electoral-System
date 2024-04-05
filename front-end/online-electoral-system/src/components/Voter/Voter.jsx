import { useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { ErrorMessage, Field, Formik,Form } from "formik";
import { addnewVoterApi, retrieveVoterApi, updateVoterApi } from "../../api/VoterApiService";

export default function VoterComponent() {
  
  const { id } = useParams()
  const [voterdata, setVoterdata] = useState({
    voterId: "",
    name: "",
    password: "",
    voted:""
  })
  const navigate = useNavigate()

  useEffect(
    () => {
      if (id !== "-1") {
        retrieveVoter();
      }
    }, 
    [id] //id change holei retrieveTodo() call hobe, [id] dependency array
  );

  function retrieveVoter() {
    retrieveVoterApi(id)
      .then((response) => {
        const { voterId, name, password, voted } = response.data
        setVoterdata({voterId,name,password,voted})
      })
      .catch((error) => console.log(error));
  }

  function onSubmit(values) {
    const voter = {
      voterId: values.voterId,
      name: values.name,
      password: values.password,
      voted: values.voted,
    };

    // Update voter
    if (id !== "-1") {
      updateVoterApi(id, voter)
        .then((response) => {
          navigate("/admin/voter-list"); // Navigate to voter list after updating
        })
        .catch((error) => console.log(error));
    } else {
      // Create new voter
      addnewVoterApi(voter)
        .then((response) => {
          navigate("/admin/voter-list"); // Navigate to voter list after adding new voter
        })
        .catch((error) => console.log(error));
    }
  }
  
  
  function validate(values) {
    let error = {};
    if (values.voterId < 1000) error.voterId = "Enter the integer after 1000";
    if (!values.password) error.password = "enter valid password";
    if (!values.name) error.name = "enter valid name";

    console.log(values," -For validateion");
    return error;
  }

  return (
    <div className="container">
      <h2 className="heading">Enter Voter details</h2>

      <div>
        <Formik
          initialValues={voterdata}
          enableReinitialize={true}
          onSubmit={onSubmit}
          validate={validate}
          validateOnChange={false}
          validationOnBlur={false}
        >
          {(props) => (
            <Form>
              <ErrorMessage
                name="voterId"
                component="div"
                className="alert alert-warning"
              />

              <ErrorMessage
                name="name"
                component="div"
                className="alert alert-warning"
              />
              <ErrorMessage
                name="password"
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
