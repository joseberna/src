import React, { useState } from 'react';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import './LoginForm.styles.css';
import { login } from '../../api/authApi';
import  decodeJWT  from '../../utils/jwt';
import { useNavigate } from 'react-router-dom';

const LoginForm = () => {
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const formik = useFormik({
        initialValues: {
            email: '',
            password: '',
            terms: false,
        },
        validationSchema: Yup.object({
            email: Yup.string().email('Correo inválido').required('Campo obligatorio'),
            password: Yup.string().required('Campo obligatorio'),
            terms: Yup.boolean().oneOf([true], 'Debes aceptar los términos'),
        }),
        onSubmit: async (values) => {
            const token = await login(values.email, values.password);
            console.log(token);
            const userDecoded = decodeJWT(token);
            localStorage.setItem('token', token);
            localStorage.setItem('user', JSON.stringify(userDecoded));
            navigate('/home');
            setError('');

        },
    });

    return (
        <div className="login-bg">
            <form className="login-card" onSubmit={formik.handleSubmit}>
                <h2>Debes iniciar sesión para acceder a <b>la plataforma</b></h2>
                <span className="login-subtitle">
                    Digita tu documento de identidad del propietario o representante legal y la contraseña
                </span>
                <input

                    type="email"
                    placeholder="Correo electrónico"
                    {...formik.getFieldProps('email')}
                />
                {formik.touched.email && formik.errors.email && <div className="form-error">{formik.errors.email}</div>}
                <input

                    type="password"
                    placeholder="Contraseña"
                    {...formik.getFieldProps('password')}
                />
                {formik.touched.password && formik.errors.password && <div className="form-error">{formik.errors.password}</div>}
                <label className="terms-label">
                    <input

                        type="checkbox"
                        {...formik.getFieldProps('terms')}
                        checked={formik.values.terms}
                    />
                    <span>Acepto términos y condiciones</span>
                </label>
                {formik.touched.terms && formik.errors.terms && <div className="form-error">{formik.errors.terms}</div>}
                {error && <div className="form-error">{error}</div>}
                <button type="submit" className="login-btn" disabled={formik.isSubmitting}>
                    Iniciar sesión
                </button>
            </form>
        </div>
    );
};

export default LoginForm;
