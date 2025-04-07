// src/components/UserForm.tsx
import { useForm } from 'react-hook-form';
import { z } from 'zod';
import { zodResolver } from '@hookform/resolvers/zod';
import { Button, TextField, Box, Typography } from '@mui/material';

// Validační schéma
const userSchema = z.object({
    email: z.string().email('Neplatný email'),
    password: z.string()
        .min(6, 'Heslo musí mít alespoň 6 znaků')
        .regex(/[A-Z]/, 'Heslo musí obsahovat velké písmeno')
        .regex(/[0-9]/, 'Heslo musí obsahovat číslo'),
    active: z.boolean().optional().default(true)
});

type UserFormData = z.infer<typeof userSchema>;

const UserForm = () => {
    const { register, handleSubmit, formState: { errors } } = useForm<UserFormData>({
        resolver: zodResolver(userSchema),
        defaultValues: {
            active: true
        }
    });

    const onSubmit = (data: UserFormData) => {
        console.log('Form data:', data);
    };

    return (
        <Box component="form" onSubmit={handleSubmit(onSubmit)} sx={{ mt: 3 }}>
            <Typography variant="h6" gutterBottom>
                Přidat nového uživatele
            </Typography>

            <TextField
                fullWidth
                margin="normal"
                label="Email"
                {...register('email')}
                error={!!errors.email}
                helperText={errors.email?.message}
            />

            <TextField
                fullWidth
                margin="normal"
                label="Heslo"
                type="password"
                {...register('password')}
                error={!!errors.password}
                helperText={errors.password?.message}
            />

            <Button
                type="submit"
                variant="contained"
                sx={{ mt: 3 }}
            >
                Přidat uživatele
            </Button>
        </Box>
    );
};

export default UserForm;