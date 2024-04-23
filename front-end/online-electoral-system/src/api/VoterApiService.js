import axios from "axios";

const jwt = localStorage.getItem('jwt') ? localStorage.getItem('jwt') : null;
export const apiClient = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    Authorization: `Bearer ${jwt}`
  }
});

export const retrieveVoterListApi = () => {
  return apiClient.get("/api/v1/admin-user/voters");
}
  
export const retrieveVoterApi = (id) => {
  return apiClient.get(`/api/v1/admin-user/voters/${id}`);
}
  
export const deleteVoterApi = (voterId) => {
  return apiClient.delete(`/api/v1/secure/voters/${voterId}`);
}
  
export const updateVoterApi = (id,voter) => {
  return apiClient.patch(`/api/v1/secure/voters/${id}`,voter);
}
  
export const addnewVoterApi = (voter) => {
  return apiClient.post(`/api/v1/secure/voters`, voter);
}
