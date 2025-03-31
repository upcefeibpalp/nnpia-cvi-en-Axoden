import React from 'react';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button } from '@mui/material';

export interface UserData {
    id: number;
    email: string;
    password: string;
    active: boolean;
}

interface UserTableProps {
    users: UserData[];
    toggleActive: (id: number) => void;
}

const UserTable: React.FC<UserTableProps> = ({ users, toggleActive }) => {
    return (
        <TableContainer component={Paper}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>ID</TableCell>
                        <TableCell>Email</TableCell>
                        <TableCell>Password</TableCell>
                        <TableCell>Active</TableCell>
                        <TableCell>Actions</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {users.map((user) => (
                        <TableRow key={user.id}>
                            <TableCell>{user.id}</TableCell>
                            <TableCell>{user.email}</TableCell>
                            <TableCell>{user.password}</TableCell>
                            <TableCell>{user.active ? 'Yes' : 'No'}</TableCell>
                            <TableCell>
                                <Button variant="contained" onClick={() => toggleActive(user.id)}>
                                    Toggle Active
                                </Button>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
};

export default UserTable;
