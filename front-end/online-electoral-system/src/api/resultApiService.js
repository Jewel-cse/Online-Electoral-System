import { apiClient } from "./VoterApiService";

export const retrieveWinnersApi = () => {
    return apiClient.get("/api/election-winners");
} 

export const retrieveResult = (positionId) => {
  return apiClient.get(`/api/election-result/${positionId}`);
};