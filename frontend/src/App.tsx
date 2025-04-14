import { Routes, Route, Link, useNavigate } from 'react-router-dom';
import { Container, Typography, Button, Stack } from '@mui/material';
import UsersPage from './pages/UsersPage';
import RegisterPage from './pages/RegisterPage';
import UserLoginForm from './UserLoginForm';
import { useSelector, useDispatch } from 'react-redux';
import { logout } from './authenticationSlice';

const App = () => {
    const token = useSelector((state: any) => state.auth.token);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handleLogout = () => {
        dispatch(logout()); // Odhlášení pomocí dispatch
        navigate('/login');
    };

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
                {token ? (
                    <Button onClick={handleLogout} variant="contained">
                        Odhlásit
                    </Button>
                ) : (
                    <Button component={Link} to="/login" variant="contained">
                        Přihlásit
                    </Button>
                )}
            </Stack>

            <Routes>
                <Route path="/users" element={<UsersPage />} />
                <Route path="/register" element={<RegisterPage />} />
                <Route path="/login" element={<UserLoginForm />} />
                <Route path="/" element={<UsersPage />} />
            </Routes>
        </Container>
    );
};

export default App;