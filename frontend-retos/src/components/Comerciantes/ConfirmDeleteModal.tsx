// src/components/Comerciantes/ConfirmDeleteModal.tsx
import React from 'react';

interface Props {
  open: boolean;
  onConfirm: () => void;
  onCancel: () => void;
  nombre: string;
}

const ConfirmDeleteModal: React.FC<Props> = ({ open, onConfirm, onCancel, nombre }) => {
  if (!open) return null;
  return (
    <div className="modal-backdrop">
      <div className="modal-content">
        <p>¿Seguro que deseas eliminar el comerciante <b>{nombre}</b>?</p>
        <button onClick={onConfirm}>Sí, eliminar</button>
        <button onClick={onCancel}>Cancelar</button>
      </div>
    </div>
  );
};

export default ConfirmDeleteModal;
