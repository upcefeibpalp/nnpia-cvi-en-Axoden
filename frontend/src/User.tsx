import React, { useState } from 'react';
import { Button, Card, CardContent, Typography } from '@mui/material';

interface UserProps {
    id: number;
    email: string;
    password: string;
    active: boolean;
}

const User: React.FC<UserProps> = ({ id, email, password, active: initialActive }) => {
    const [active, setActive] = useState(initialActive);

    return (
        <Card sx={{ marginBottom: 2 }}>
            <CardContent>
                <Typography variant="h6">User #{id}</Typography>
                <Typography>Email: {email}</Typography>
                <Typography>Password: {password}</Typography>
                <Typography>Active: {active ? "Yes" : "No"}</Typography>
                <Button onClick={() => setActive(!active)} variant="contained" sx={{ marginTop: 1 }}>
                    Toggle Active
                </Button>
            </CardContent>
        </Card>
    );
};

export default User;
