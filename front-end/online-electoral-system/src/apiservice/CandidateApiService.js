import { apiClient } from "./api";

//get all the candidate for a specific
export const retrieveAllCandidateApi = (positionId) => {
  return apiClient.get(`api/v1/admin-user/candidates/${positionId}`);
};

export const retrieveCandidateApi = (id) => {
  return apiClient.get(`api/v1/secure/admin/candidates/id/${id}`);
}

//post a candidate
export const createCandidateApi = (candidate) => {
  return apiClient.post("/api/v1/secure/admin/candidates", candidate);
};

//delete a candidate
export const deleteCandidateApi = (id) => {
  return apiClient.delete(`/api/v1/secure/admin/candidates/${id}`);
};

export const updateCandidateApi = (id,candidate) => {
  return apiClient.put(`/api/v1/secure/admin/candidates/${id}`,candidate);
}
