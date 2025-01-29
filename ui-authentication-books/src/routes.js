import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Book from "./pages/Book";
import SendCode from "./pages/Login/SendCode";

export default function AppRoutes() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/auth" element={<SendCode />} />
                <Route path="/book" element={<Book />} />
                
            </Routes>
        </Router>
    );
}