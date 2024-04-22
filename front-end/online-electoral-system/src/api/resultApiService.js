import { apiClient } from "./VoterApiService";

export const retrieveWinnersApi = () => {
    return apiClient.get("/api/v1/election-winners");
} 

export const retrieveResult = (positionId) => {
  return apiClient.get(`/api/v1/election-result/${positionId}`);
};