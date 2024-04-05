import axios from "axios";

export const apiClient = axios.create({
  baseURL: "http://localhost:8080",
});

export const retrieveVoterListApi = () => {
  return apiClient.get("/api/voter-list");
}
  
export const retrieveVoterApi = (id) => {
  return apiClient.get(`/api/voters/${id}`);
}
  

export const deleteVoterApi = (voterId) => {
  return apiClient.delete(`/api/voters/${voterId}`);
}
  

export const updateVoterApi = (id,voter) => {
  return apiClient.patch(`/api/voters/${id}`,voter);
}
  

export const addnewVoterApi = (voter) => {
  return apiClient.post(`/api/voters`, voter);
}
