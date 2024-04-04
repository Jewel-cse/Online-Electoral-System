import { useNavigate, useParams } from "react-router-dom";
import {
  retrieveCandidateApi,
  createCandidateApi,
  updateCandidateApi,
} from "../../api/CandidateApiService";
import { useEffect, useState } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";

export default function Candidate() {
  const { id } = useParams(); // Correctly extract id from URL params
  const [candidatedata, setCandidatedata] = useState({
    name: "",
    positionId: "",
    symbol: "",
  });

  const navigate = useNavigate();

  useEffect(() => {
    if (id !== "-1") {
      // Make sure to avoid unnecessary API call for -1 id
      retrieveCandidate();
    }
  }, [id]);

  function retrieveCandidate() {
    retrieveCandidateApi(id)
      .then((response) => {
        const { name, positionId, symbol } = response.data;
        setCandidatedata({ name, positionId, symbol });
      })
      .catch((error) => console.log(error));
  }

  function onSubmit(values) {
    console.log(values, "-for submit");
    const candidate = {
      name: values.name,
      positionId: values.positionId,
      symbol: values.symbol,
    };
    if (id !== "-1") {
      updateCandidateApi(id, candidate) // Pass id to updateCandidateApi
        .then((response) => {
          navigate(`/admin/candidates/${candidate.positionId}`);
        })
        .catch((error) => console.log(error));
    } else {
      createCandidateApi(candidate)
        .then((response) => {
          navigate(`/admin/candidates/${candidate.positionId}`);
        })
        .catch((error) => console.log(error));
    }
  }

  function validate(values) {
    let error = {};
    if (values.name.length < 3)
      error.name = "Enter at least 3 characters for name";
    if (
      values.positionId !== "mp" &&
      values.positionId !== "president" &&
      values.positionId !== "chairman" &&
      values.positionId !== "member"
    )
      error.positionId = "Enter a valid positionId";

    if (values.symbol.length < 3)
      error.symbol = "Enter at least 3 characters for symbol";
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
          initialValues={candidatedata}
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
                <Field type="text" className="form-control" name="name" />
              </fieldset>
              <fieldset className="from-group">
                <label>Position</label>
                <Field type="text" className="form-control" name="positionId" />
              </fieldset>
              <fieldset className="from-group">
                <label>Symbol</label>
                <Field type="text" className="form-control" name="symbol" />
              </fieldset>
              <div>
                <button
                  style={{
                    background: "blue",
                    display: "flex",
                    justifyContent: "center",
                    alignItems: "center",
                    width: "100px",
                    margin: "0 auto",
                  }}
                  className="btn btn-success m-5 px-8"
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
