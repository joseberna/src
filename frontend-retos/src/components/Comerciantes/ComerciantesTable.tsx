// src/components/Comerciantes/ComerciantesTable.tsx
import React from 'react';
import ComercianteRow from './ComercianteRow';

interface Comerciante {
  idComerciante: number;
  nombreRazonSocial: string;
  telefono?: string;
  correoElectronico: string;
  fechaRegistro: string;
  cantidadEstablecimientos: number;
  estado: string;
}

interface Props {
  comerciantes?: Comerciante[];
  page: number;
  pageSize: number;
  total: number;
  onPageChange: (n: number) => void;
  onPageSizeChange: (n: number) => void;
  onEdit: (c: Comerciante) => void;
  onToggleEstado: (c: Comerciante) => void;
  onDelete: (c: Comerciante) => void;
  userRole: string;
}

const ComerciantesTable: React.FC<Props> = ({
  comerciantes, page, pageSize, total,
  onPageChange, onPageSizeChange, onEdit, onToggleEstado, onDelete, userRole
}) => {
  const pages = Math.ceil(total / pageSize);
  console.log('Total de Comerciantes:', total);
  console.log('Comerciantes:', comerciantes);


  return (
    <div>
      <table>
        <thead>
          <tr>
            <th>Razón Social</th>
            <th>Teléfono</th>
            <th>Correo Electrónico</th>
            <th>Fecha Registro</th>
            <th>No. Establecimientos</th>
            <th>Estado</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {comerciantes?.map((c) =>
            <ComercianteRow
              key={c.idComerciante}
              comerciante={c}
              onEdit={() => onEdit(c)}
              onToggleEstado={() => onToggleEstado(c)}
              onDelete={() => onDelete(c)}
              userRole={userRole}
            />
          )}
        </tbody>
      </table>
      {/* Paginación */}
      <div style={{ marginTop: 16 }}>
        <select value={pageSize} onChange={e => onPageSizeChange(Number(e.target.value))}>
          {[5, 10, 15].map(size => <option key={size} value={size}>{size} por página</option>)}
        </select>
        <span> Página: </span>
        {[...Array(pages)].map((_, idx) => (
          <button
            key={idx}
            onClick={() => onPageChange(idx + 1)}
            style={page === idx + 1 ? { fontWeight: 'bold' } : {}}
          >
            {idx + 1}
          </button>
        ))}
        <span> de {pages}</span>
      </div>
    </div>
  );
};

export default ComerciantesTable;
