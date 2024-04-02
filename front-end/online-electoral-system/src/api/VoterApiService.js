import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8080",
});

export const retrieveVoterListApi = () => apiClient.get("/api/voter-list");
export const retrieveVoterApi = (voterId) =>
  apiClient.get(`/api/voters/${voterId}`);

export const deleteVoterApi = (voterId) =>
  apiClient.delete(`api/voters/${voterId}`);

export const updateVoterApi = (voterId) =>
  apiClient.patch(`/api/voters/${voterId}`);

export const addnewVoterApi = (voter) => apiClient.post(`/api/voters`,voter);
