// src/App.tsx with apiService and corrections

// Make sure React is imported
import React, { useState, useEffect } from 'react';
import { userService } from './apiService';
// import User from './User'; // Remove this import if the 'User' component is not used in the JSX
import UserTable, { UserData } from './UserTable';
import { Container, Typography, CircularProgress, Alert } from '@mui/material'; // Import necessary MUI components

// Base URL check reminder: Ensure your backend is running on the port specified in apiService.ts (defaulted to 9000 here).
// If your backend runs on 8080, update baseURL in apiService.ts accordingly.

const App: React.FC = () => {
    // --- Re-add State Declarations ---
    const [users, setUsers] = useState<UserData[]>([]);
    const [isLoading, setLoading] = useState<boolean>(false); // Use setLoading here
    const [error, setError] = useState<string | null>(null);

    // --- Fetch Users Function (using userService) ---
    const fetchUsers = async () => {
        try {
            setLoading(true); // Correct setter name
            setError(null);
            const response = await userService.getAll(); // Use the service
            console.log('Fetched users:', response.data);
            // Ensure the data structure from the backend matches UserData[]
            // If the backend returns the full User entity including password,
            // UserData should reflect that, although sending passwords to the frontend is not ideal.
            setUsers(response.data);
        } catch (err: any) { // Catch block with type annotation
            console.error('Error fetching users:', err);
            setError(err.response?.data?.message || err.message || 'Failed to fetch users. Please try again.'); // Provide more specific error if possible
        } finally {
            setLoading(false); // Correct setter name
        }
    };

    // --- useEffect Hook to Fetch Data on Mount ---
    useEffect(() => {
        fetchUsers();
    }, []); // Empty dependency array ensures it runs once on mount

    // --- Toggle Active Function (using userService) ---
    const toggleActive = async (id: number) => {
        const user = users.find(u => u.id === id);
        if (!user) return;

        // Security Note: Sending password back like this is generally discouraged.
        // The UserUpdateDTO on the backend currently requires it based on the previous code.
        // Ideally, only changed fields or a specific DTO for status updates should be used.
        // Also, storing plain passwords in frontend state (if 'user.password' holds it) is insecure.
        const updateData = {
            email: user.email,
            // If password isn't actually available or needed for this update based on backend logic, remove it.
            // Assuming here it's required by the current UserUpdateDTO structure.
            password: user.password, // Be cautious with this
            active: !user.active
        };

        try {
            // Optimistic UI update can be done here, but let's update after success for simplicity
            setError(null); // Clear previous errors
            await userService.update(id, updateData); // Use the service

            // Update local state upon successful backend update
            setUsers((prevUsers: UserData[]) => // --- Add type UserData[] to prevUsers ---
                prevUsers.map(u => u.id === id ? { ...u, active: !u.active } : u)
            );
        } catch (err: any) { // Catch block with type annotation
            console.error('Error updating user:', err);
            setError(err.response?.data?.message || err.message || 'Failed to update user. Please try again.'); // Provide more specific error if possible
            // Optional: Revert optimistic UI update here if it was implemented
        }
        // No finally block needed here unless managing specific loading state for this action
    };

    // --- Return JSX ---
    return (
        <Container>
            <Typography variant="h3" gutterBottom>NNPIA - Single-page application</Typography>
            <Typography variant="h5" gutterBottom>Users Table:</Typography>

            {/* Loading Indicator */}
            {isLoading && <CircularProgress sx={{ display: 'block', margin: '20px auto' }} />}

            {/* Error Message */}
            {error && <Alert severity="error" sx={{ marginBottom: 2 }}>{error}</Alert>}

            {/* User Table - Render only when not loading and data is available */}
            {!isLoading && users.length > 0 && (
                <UserTable users={users} toggleActive={toggleActive} />
            )}

            {/* Message if no users are loaded and not loading */}
            {!isLoading && !error && users.length === 0 && (
                <Typography>No users found or failed to load.</Typography>
            )}
        </Container>
    );
};

export default App;