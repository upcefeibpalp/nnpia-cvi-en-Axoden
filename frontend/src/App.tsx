import React, { useState } from 'react';
import User from './User';
import UserTable, { UserData } from './UserTable';
import { Container, Typography } from '@mui/material';

const App: React.FC = () => {
    const [users, setUsers] = useState<UserData[]>([
        { id: 1, email: 'admin@upce.cz', password: 'admin', active: true },
        { id: 2, email: 'test@upce.cz', password: 'test', active: false },
        { id: 3, email: 'user@upce.cz', password: 'user', active: true },
    ]);

    const toggleActive = (id: number) => {
        setUsers((prevUsers) =>
            prevUsers.map((user) =>
                user.id === id ? { ...user, active: !user.active } : user
            )
        );
    };

    return (
        <Container>
            <Typography variant="h3" gutterBottom>NNPIA - Single-page application</Typography>
            <User id={users[0].id} email={users[0].email} password={users[0].password} active={users[0].active} />
            <Typography variant="h5" gutterBottom>Users Table:</Typography>
            <UserTable users={users} toggleActive={toggleActive} />
        </Container>
    );
};

export default App;
