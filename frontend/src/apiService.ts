// apiService.ts - Create this file to centralize API calls
import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:9000/api/v1/',
    headers: {
        'Content-Type': 'application/json'
    }
});

// Request interceptor - you can add auth token here
apiClient.interceptors.request.use(
    config => {
        // Add any request transformations here
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

// Response interceptor
apiClient.interceptors.response.use(
    response => {
        return response;
    },
    error => {
        // Handle global errors here (e.g., 401 Unauthorized)
        return Promise.reject(error);
    }
);

// User service functions
export const userService = {
    getAll: () => apiClient.get('/users'),
    getById: (id: number) => apiClient.get(`/users/${id}`),
    update: (id: number, data: any) => apiClient.put(`/users/${id}`, data),
    create: (data: any) => apiClient.post('/users', data),
    delete: (id: number) => apiClient.delete(`/users/${id}`)
};