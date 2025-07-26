// src/pages/HomePage.tsx
import React, { useEffect, useState } from 'react';
import ComerciantesTable from '../components/Comerciantes/ComerciantesTable';
import HeaderUser from '../components/Shared/HeaderUser';


interface Comerciante {
    idComerciante: number;
    nombreRazonSocial: string;
    telefono?: string;
    correoElectronico: string;
    fechaRegistro: string;
    cantidadEstablecimientos: number;
    estado: string;
}
const HomePage: React.FC = () => {
    const [comerciantes, setComerciantes] = useState<Comerciante[]>([]); 
    const [page, setPage] = useState(0);
    const [pageSize, setPageSize] = useState(5);
    const [total, setTotal] = useState(0);


    const user = JSON.parse(localStorage.getItem('user') || '{}');

    useEffect(() => {
        console.log('Pagesss', { page, pageSize });
        fetchComerciantes(page, pageSize);
    }, [page, pageSize]);

    const fetchComerciantes = async (page: number, size: number) => {
        const token = localStorage.getItem('token');
        const res = await fetch(`http://localhost:9090/api/comerciantes?page=${page}&size=${size}`, {
            headers: { Authorization: `Bearer ${token}` }
        });
        const data = await res.json();
        console.log(data);
        setComerciantes(data.data.content);
        setTotal(data.data.totalElements || 0);
    };

    const handleCreate = () => { };
    const handleDownloadCSV = () => { window.open('http://localhost:9090/api/comerciantes/reporte', '_blank'); };

    return (
        <div>
            <HeaderUser />
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', margin: '2rem 0 1rem 0' }}>
                <h2>Lista de Comerciantes</h2>
                <div>
                    <button onClick={handleCreate}>Crear Formulario Nuevo</button>
                    {user.rol === 'Administrador' && (
                        <button onClick={handleDownloadCSV} style={{ marginLeft: '1rem' }}>Descargar Reporte en CSV</button>
                    )}
                </div>
            </div>
            <ComerciantesTable
                comerciantes={comerciantes}
                page={page}
                pageSize={pageSize}
                total={total}
                onPageChange={setPage}
                onPageSizeChange={setPageSize}
                onEdit={() => { }} // función vacía temporal
                onToggleEstado={() => { }} // función vacía temporal
                onDelete={() => { }} // función vacía temporal
                userRole={user?.rol || 'Auxiliar'} // ejemplo, ajusta según tu contexto de usuario
            />

        </div>
    );
};

export default HomePage;
