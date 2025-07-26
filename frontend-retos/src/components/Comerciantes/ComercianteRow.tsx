// src/components/Comerciantes/ComercianteRow.tsx
import React from 'react';

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
  comerciante: Comerciante;
  onEdit: () => void;
  onToggleEstado: () => void;
  onDelete: () => void;
  userRole: string;
}

const ComercianteRow: React.FC<Props> = ({ comerciante, onEdit, onToggleEstado, onDelete, userRole }) => (
  <tr>
    <td>{comerciante.nombreRazonSocial}</td>
    <td>{comerciante.telefono || '-'}</td>
    <td>{comerciante.correoElectronico}</td>
    <td>{comerciante.fechaRegistro}</td>
    <td>{comerciante.cantidadEstablecimientos}</td>
    <td>
      <span style={{
        color: comerciante.estado === 'Activo' ? 'green' : 'red',
        fontWeight: 'bold'
      }}>
        {comerciante.estado}
      </span>
    </td>
    <td>
      <button onClick={onEdit} title="Editar">✏️</button>
      <button onClick={onToggleEstado} title={comerciante.estado === 'Activo' ? "Inactivar" : "Activar"}>
        {comerciante.estado === 'Activo' ? '🚫' : '✅'}
      </button>
      {userRole === 'Administrador' && (
        <button onClick={onDelete} title="Eliminar">🗑️</button>
      )}
    </td>
  </tr>
);

export default ComercianteRow;
