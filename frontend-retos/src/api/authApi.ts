import axios from 'axios';

export interface LoginResponse {
  token: string;
}
const API_URL = process.env.REACT_APP_API_URL;

export const login = async (correo: string, contrasena: string) => {
  const response = await axios.post<LoginResponse>(`${API_URL}/api/auth/login`, {
    correoElectronico: correo,
    contrasena,
  });
  return response.data.token;
};
