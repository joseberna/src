// src/components/Shared/HeaderUser.tsx
import React from "react";
import decodeJWT from "../../utils/jwt";

const HeaderUser = () => {
  const token = localStorage.getItem("token");
  let nombre = "";
  let rol = "";

  if (token) {
    try {
      const user = decodeJWT(token);
      nombre = user.nombre || "";
      rol = user.rol || "";
    } catch (e) {
      // Token inválido
    }
  }

  if (!nombre && !rol) return null; // No mostrar si no hay usuario

  return (
    <div style={{ position: "absolute", top: 25, right: 32, fontWeight: 600 }}>
      {nombre} | {rol}
    </div>
  );
};

export default HeaderUser;
