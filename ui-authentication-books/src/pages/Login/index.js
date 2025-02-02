import React, {useState} from "react";
import { useNavigate } from "react-router-dom";
import { FiLock, FiUser} from "react-icons/fi";
import './styles.css';

import api from '../../services/api';
import logoImage from '../../assets/logo.svg';
import logoAuth from '../../assets/logoAuth.png';


export default function Login() {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const navigate = useNavigate();

    async function login(e) {
        e.preventDefault();

        const data = {
            username,
            password
        };

        try {
            await api.post('/auth/signin', data);
            localStorage.setItem('localStorage_username', username);
            navigate('/auth')
        } catch (error) {
            alert('Falha no login. Tente novamente!')
        }
    }


    return (
        <div className="login-container">
            <section className="form">
                <img src={logoImage} alt="Lira Logo" />
                <form onSubmit={login}>
                    <h1>Faça login para continuar sua jornada de leitura</h1>
                    <div className="input-container">
                        <FiUser size={18} />
                        <input 
                            type="text" 
                            value={username}
                            onChange={e => setUsername(e.target.value)}
                            placeholder="Nome de usuário" 
                        />
                    </div>
                    <div className="input-container">
                        <FiLock size={18} />
                        <input 
                            type="password" 
                            value={password}
                            onChange={e => setPassword(e.target.value)}
                            placeholder="Senha" 
                        />
                    </div>
                    <button className="button" type="submit">Entrar</button>
                </form>
            </section>

            <img src={logoAuth} alt="Login" />
        </div>
    );
}
