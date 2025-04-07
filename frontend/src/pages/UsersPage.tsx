// src/pages/UsersPage.tsx
import { Container, Typography, CircularProgress, Alert } from '@mui/material';
import { useState, useEffect } from 'react';
import { userService } from '../apiService';
import UserTable, { UserData } from '../UserTable';

const UsersPage = () => {
    const [users, setUsers] = useState<UserData[]>([]);
    const [isLoading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<string | null>(null);

    const fetchUsers = async () => {
        try {
            setLoading(true);
            setError(null);
            const response = await userService.getAll();
            setUsers(response.data);
        } catch (err: any) {
            setError(err.response?.data?.message || err.message || 'Failed to fetch users');
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchUsers();
    }, []);

    const toggleActive = async (id: number) => {
        const user = users.find(u => u.id === id);
        if (!user) return;

        try {
            setError(null);
            await userService.update(id, {
                email: user.email,
                password: user.password,
                active: !user.active
            });
            setUsers(prevUsers =>
                prevUsers.map(u => u.id === id ? { ...u, active: !u.active } : u)
            );
        } catch (err: any) {
            setError(err.response?.data?.message || err.message || 'Failed to update user');
        }
    };

    return (
        <Container>
            <Typography variant="h5" gutterBottom>Seznam uživatelů</Typography>

            {isLoading && <CircularProgress sx={{ display: 'block', margin: '20px auto' }} />}
            {error && <Alert severity="error" sx={{ mb: 2 }}>{error}</Alert>}

            {!isLoading && users.length > 0 && (
                <UserTable users={users} toggleActive={toggleActive} />
            )}

            {!isLoading && !error && users.length === 0 && (
                <Typography>Nebyli nalezeni žádní uživatelé.</Typography>
            )}
        </Container>
    );
};

export default UsersPage;