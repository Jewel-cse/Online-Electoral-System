import { Form, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { addnewVoterApi, retrieveVoterApi } from "../api/VoterApiService";
import { ErrorMessage, Field, Formik } from "formik";

export default function VoterComponent() {
  const { voterId } = useState(0);
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const [voted, setVoted] = useState(false);

  useEffect(
    () => retrieveVoterApi(),
    [voterId] //id change holei retrieveTodo() call hobe, [id] dependency array
  );

  function retrieveVoter() {
    retrieveVoterApi(voterId)
      .then((response) => {
        setName(response.data.name);
        setPassword(response.data.password);
        setVoted(response.data.voted);
        console.log(response.data);
      })
      .catch((error) => console.log(error));
  }

  function onSubmit(values) {
    console.log(values, "-for submit");
    const voter = {
      voterId: values.voterId,
      name: values.name,
      password: values.password,
      voted: false,
    };
    addnewVoterApi(voter)
      .then((response) => {
        nevigate("/admin/voter-list");
      })
      .catch((error) => console.log(error));
  }

  function validate(values) {
    let error = {};
    if (values.voterId < 1000) error.voterId = "Enter the integer after 1000";
    if (values.password == null) error.password = "enter valid password";
    if (values.name == null) error.name = "enter valid name";

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
              <ErrorMessage voterId="voterId" component="div" />

              <ErrorMessage name="name" component="div" />
              <ErrorMessage password="password" component="div" />

              <label htmlFor="voterId">voterId</label>
              <Field id="voterId" name="voterId" placeholder="1000" />

              <label htmlFor="name">name</label>
              <Field id="name" name="name" placeholder="jewel" />

              <label htmlFor="password">password</label>
              <Field id="password" name="password" placeholder="jewel123" />
            </Form>
          )}
        </Formik>
      </div>
    </div>
  );
}
