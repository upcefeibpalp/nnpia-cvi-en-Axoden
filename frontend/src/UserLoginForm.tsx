import { useState, FormEvent, ChangeEvent } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { login } from './authenticationSlice';
import { TextField, Button, Box, Typography } from '@mui/material';
import axios from 'axios';

const UserLoginForm = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:9000/api/v1/auth/login', {
                email,
                password,
            });
            const token = response.data.token;
            dispatch(login(token));
            navigate('/');
        } catch (error) {
            console.error('Chyba při přihlašování:', error);
        }
    };

    const handleEmailChange = (e: ChangeEvent<HTMLInputElement>) => {
        setEmail(e.target.value);
    };

    const handlePasswordChange = (e: ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value);
    };

    return (
        <Box component="form" onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Typography variant="h6" gutterBottom>
                Přihlášení uživatele
            </Typography>
            <TextField
                fullWidth
                margin="normal"
                label="Email"
                value={email}
                onChange={handleEmailChange}
            />
            <TextField
                fullWidth
                margin="normal"
                label="Heslo"
                type="password"
                value={password}
                onChange={handlePasswordChange}
            />
            <Button type="submit" variant="contained" sx={{ mt: 3 }}>
                Přihlásit
            </Button>
        </Box>
    );
};

export default UserLoginForm;