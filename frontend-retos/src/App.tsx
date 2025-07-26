import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import LoginForm from "./components/LoginForm/LoginForm";
import HomePage from "./pages/HomePage";
import HeaderUser from "./components/Shared/HeaderUser";

function App() {
  return (
    <BrowserRouter>
      <HeaderUser />
      <Routes>
        <Route path="/" element={<LoginForm />} />
        <Route path="/home" element={<HomePage />} />
       
      </Routes>
    </BrowserRouter>
  );
}
export default App;
