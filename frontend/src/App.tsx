// src/App.tsx
import { Routes, Route, Link } from 'react-router-dom';
import { Container, Typography, Button, Stack } from '@mui/material';
import UsersPage from './pages/UsersPage';
import RegisterPage from './pages/RegisterPage';

const App = () => {
    return (
        <Container>
            <Typography variant="h3" gutterBottom>NNPIA - Single-page application</Typography>

            <Stack direction="row" spacing={2} sx={{ mb: 4 }}>
                <Button component={Link} to="/users" variant="contained">
                    Seznam uživatelů
                </Button>
                <Button component={Link} to="/register" variant="contained">
                    Registrace
                </Button>
            </Stack>

            <Routes>
                <Route path="/users" element={<UsersPage />} />
                <Route path="/register" element={<RegisterPage />} />
                <Route path="/" element={<UsersPage />} />
            </Routes>
        </Container>
    );
};

export default App;