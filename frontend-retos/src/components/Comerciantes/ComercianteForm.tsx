// src/components/Comerciantes/ComercianteForm.tsx
import React from 'react';
import { useFormik } from 'formik';
import * as Yup from 'yup';

interface ComercianteFormProps {
    initialValues: any;
    onSubmit: (values: any) => void;
    onCancel: () => void;
}

const ComercianteForm: React.FC<ComercianteFormProps> = ({ initialValues, onSubmit, onCancel }) => {
    const formik = useFormik({
        initialValues,
        validationSchema: Yup.object({
            nombreRazonSocial: Yup.string().required('Requerido'),
            correoElectronico: Yup.string().email('Correo inválido').required('Requerido'),
            municipio: Yup.string().required('Requerido'),
            telefono: Yup.string(),
            estado: Yup.string().oneOf(['Activo', 'Inactivo']).required('Requerido')
        }),
        onSubmit,
    });

    return (
        <form onSubmit={formik.handleSubmit}>
            <input
                name="nombreRazonSocial"
                placeholder="Razón Social"
                value={formik.values.nombreRazonSocial}
                onChange={formik.handleChange}
            />
            {formik.touched.nombreRazonSocial && typeof formik.errors.nombreRazonSocial === 'string' && (
                <div>{formik.errors.nombreRazonSocial}</div>
            )}
            <input
                name="correoElectronico"
                placeholder="Correo Electrónico"
                value={formik.values.correoElectronico}
                onChange={formik.handleChange}
            />
            {formik.touched.correoElectronico && typeof formik.errors.correoElectronico === 'string' && (
                <div>{formik.errors.correoElectronico}</div>
            )}
            <input
                name="municipio"
                placeholder="Municipio"
                value={formik.values.municipio}
                onChange={formik.handleChange}
            />
            {formik.touched.municipio && typeof formik.errors.municipio === 'string' && (
                <div>{formik.errors.municipio}</div>
            )}
            <input
                name="telefono"
                placeholder="Teléfono"
                value={formik.values.telefono}
                onChange={formik.handleChange}
            />
            <select
                name="estado"
                value={formik.values.estado}
                onChange={formik.handleChange}
            >
                <option value="Activo">Activo</option>
                <option value="Inactivo">Inactivo</option>
            </select>
            <button type="submit">Guardar</button>
            <button type="button" onClick={onCancel}>Cancelar</button>
        </form>
    );
};

export default ComercianteForm;
