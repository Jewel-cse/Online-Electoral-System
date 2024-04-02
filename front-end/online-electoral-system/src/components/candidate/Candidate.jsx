import { useNavigate, useParams } from "react-router-dom";

import {
  retrieveAllCandidateApi,
  createCandidateApi,
} from "../../api/CandidateApiService";
import { useEffect, useState } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";

export default function Candidate() {
  const { id } = useParams();

  const [name, setName] = useState("");
  const [symbol, setSymbol] = useState("");
  const [positionId, setPositionId] = useState("");

  const navigate = useNavigate();

  //   useEffect(
  //     () => retrieveCandidate(),
  //     [id] //id change holei retrieveTodo() call hobe, [id] dependency array
  //   );

  //   function retrieveCandidate() {
  //     if (id != -1) {
  //       retrieveTodoApi(username, id)
  //         .then((response) => {
  //           setDescription(response.data.description);
  //           setTargetDate(response.data.targetDate);
  //           console.log(response.data);
  //         })
  //         .catch((error) => console.log(error));
  //     }
  //   }

  function onSubmit(values) {
    console.log(values, "-for submit");
    const candidate = {
      name: values.name,
      positionId: values.positionId,
      symbol: values.symbol,
    };
    //console.log(todo,'-todo object')
    if (id == -1) {
      createCandidateApi(candidate)
        .then((response) => {
          navigate(`/admin/candidates/${candidate.positionId}`);
        })
        .catch((error) => console.log(error));
    }
    // else {
    //   updateTodoApi(username, id, todo)
    //     .then((response) => {
    //       navigate("/todos");
    //     })
    //     .catch((error) => console.log(error));
    // }
  }

  function validate(values) {
    let error = {};
    if (values.name.length < 3)
      error.name = "Enter atleast 3 characters for name";
    if (
      values.positionId != "mp" &&
      values.positionId != "president" &&
      values.positionId != "chairman" &&
      values.positionId != "member"
    )
      error.positionId = "Enter  a valid positionId";

    if (values.symbol.length < 3)
      error.name = "Enter atleast 3 characters for symbol";
    console.log(values, "- for validation");
    return error;
  }
  return (
    <div className="container">
      <h2 className="heading" style={{ textAlign: "center" }}>
        Enter Candidate Details
      </h2>
      <div>
        <Formik
          initialValues={{ name, positionId, symbol }}
          enableReinitialize={true}
          onSubmit={onSubmit}
          validate={validate}
          validateOnChange={false}
          validateOnBlur={false}
        >
          {(props) => (
            <Form>
              <ErrorMessage
                name="name"
                component="div"
                className="alert alert-warning"
              />
              <ErrorMessage
                name="positionId"
                component="div"
                className="alert alert-warning"
              />
              <ErrorMessage
                name="symbol"
                component="div"
                className="alert alert-warning"
              />

              <fieldset className="from-group">
                <label>Name</label>
                <Field type="text" className="form-control" name="name"></Field>
              </fieldset>
              <fieldset className="from-group">
                <label>Position</label>
                <Field
                  type="text"
                  className="form-control"
                  name="positionId"
                ></Field>
              </fieldset>
              <fieldset className="from-group">
                <label>Symbol</label>
                <Field
                  type="text"
                  className="form-control"
                  name="symbol"
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
