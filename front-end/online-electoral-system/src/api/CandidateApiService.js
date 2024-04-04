import { apiClient } from "./VoterApiService";

//get all the candidate for a specific
export const retrieveAllCandidateApi = (positionId) => {
  return apiClient.get(`api/candidates/${positionId}`);
};

export const retrieveCandidateApi = (id) => {
  return apiClient.get(`api/candidates/id/${id}`);
}

//post a candidate
export const createCandidateApi = (candidate) => {
  return apiClient.post("/api/candidates", candidate);
};

//delete a candidate
export const deleteCandidateApi = (id) => {
  return apiClient.delete(`/api/candidates/${id}`);
};

export const updateCandidateApi = (id,candidate) => {
  return apiClient.put(`/api/candidates/${id}`,candidate);
}
