// src/utils/jwt.ts
const decodeJWT = (token: string): any => {
  try {
    const payload = token.split(".")[1];
    return JSON.parse(atob(payload));
  } catch {
    return {};
  }
};

export default decodeJWT;
