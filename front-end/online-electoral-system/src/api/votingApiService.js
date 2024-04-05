import { apiClient } from "./VoterApiService";

//get all the candidate for a specific
export const retrieveCandidateApi = (voterId,positionId,symbol) => {
  return apiClient.get(`/api/cast-vote/${voterId}/${positionId}/${symbol}`);
};

