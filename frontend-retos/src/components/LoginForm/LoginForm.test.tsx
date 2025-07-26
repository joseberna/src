import React from 'react';
import { render, fireEvent, screen } from '@testing-library/react';
import  LoginForm  from './LoginForm';

test('debería mostrar error si campos están vacíos y se intenta enviar', async () => {
  render(<LoginForm />);
  fireEvent.click(screen.getByText(/Iniciar sesión/i));
  expect(await screen.findAllByText(/Requerido/i)).toHaveLength(2); // correo y contraseña
});

test('debería mostrar error si no acepta términos', async () => {
  render(<LoginForm />);
  fireEvent.change(screen.getByPlaceholderText(/Correo/i), { target: { value: 'mail@mail.com' } });
  fireEvent.change(screen.getByPlaceholderText(/Contraseña/i), { target: { value: 'abc12345' } });
  fireEvent.click(screen.getByText(/Iniciar sesión/i));
  expect(await screen.findByText(/Debes aceptar los términos/i)).toBeInTheDocument();
});
