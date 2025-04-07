// src/pages/RegisterPage.tsx
import { Container, Typography } from '@mui/material';
import UserForm from '../UserForm';

const RegisterPage = () => {
    return (
        <Container>
            <Typography variant="h5" gutterBottom>Registrace uživatele</Typography>
            <UserForm />
        </Container>
    );
};

export default RegisterPage;