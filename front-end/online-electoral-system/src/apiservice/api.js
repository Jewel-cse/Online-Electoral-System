import axios from 'axios';

const accessToken =  localStorage.getItem('accessToken') ? localStorage.getItem('accessToken') : null;
export const apiClient = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    Authorization: `Bearer ${accessToken}`
  }
});

const storeAccessToken = (accessToken) =>  {
   localStorage.setItem('accessToken', accessToken);
};

const getAccessToken = () => {
  return localStorage.getItem('accessToken');
};

const storeRefreshToken = (refreshToken) => {
  localStorage.setItem('refreshToken', refreshToken);
};

const getRefreshToken = () => {
  return localStorage.getItem('refreshToken');
};


apiClient.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;

    if (
      error.response.status === 403 &&
      !originalRequest._retry &&
      getRefreshToken()
    ) {
      originalRequest._retry = true; // Prevent infinite retry loop

      try {
        const refreshToken = getRefreshToken();
        if (!refreshToken) {
          // Handle missing refresh token
          console.error('Refresh token is missing. Redirecting to login...');
          return Promise.reject(error);
        }

        const response = await axios.post('http://localhost:8080/auth/refresh', { refreshToken });
        if (!response.data || !response.data.token) {
          // Handle token refresh failure
          console.error('Token refresh failed. Redirecting to login...');
          return Promise.reject(error);
        }

        storeAccessToken(response.data.token);
        apiClient.defaults.headers.common['Authorization'] = `Bearer ${response.data.token}`;
        originalRequest.headers['Authorization'] = `Bearer ${response.data.token}`;

        return apiClient(originalRequest);
      } catch (error) {
        // Handle other errors
        console.error('An error occurred during token refresh:', error);
        return Promise.reject(error);
      }
    }

    return Promise.reject(error);
  }
);


