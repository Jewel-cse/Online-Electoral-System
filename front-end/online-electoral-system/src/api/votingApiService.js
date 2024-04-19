import { apiClient } from "./VoterApiService";

//cast vote using get method 
export const retrieveCandidateApi = (voterId,positionId,symbol) => {
  return apiClient.get(`/api/cast-vote/${voterId}/${positionId}/${symbol}`);
};

//cast vote using post method =>voteData(voterId,positionId,symbol)
export const processVote = (voteData) => {
  return apiClient.post("api/cast-vote", voteData);
};