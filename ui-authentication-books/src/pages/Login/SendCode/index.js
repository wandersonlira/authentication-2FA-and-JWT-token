import React, {useState} from "react";
import { useNavigate } from "react-router-dom";
import { FiLock} from "react-icons/fi";
import api from '../../../services/api';
import './styles.css';
import logoAuth from '../../../assets/logoAuth.png';


export default function SendCode() {
    const [code, setCode] = useState('');


    const handleChange = (event) => {
        const value = event.target.value;

        if (/^\d*$/.test(value)) {
            setCode(value);
        }
    };

    const navigate = useNavigate();

    async function login(e) {
        e.preventDefault();

        const data = {
            code
        };

        try {
            const response = await api.get('/auth/validate', data);
            localStorage.setItem('username', response.data.username);
            localStorage.setItem('accessToken', response.data.accessToken);

            navigate('/book')
        } catch (error) {
            alert('Falha no login. Tente novamente!')
        }
        
    }


    return (
        <div className="send-container">
            <img src={logoAuth} alt="auth-logo" />
            <section className="form">
                <form onSubmit={login}>
                    <h1>Consulte sua caixa de entrada</h1>
                    <span>Insira o código de <strong>6 dígitos</strong> enviado para o seu e-mail cadastrado para finalizar o seu login.</span>
                    <div className="input-container">
                        <FiLock size={18} />
                        <input 
                            type="text" 
                            value={code}
                            onChange={handleChange}
                            placeholder="Código de 6 dígitos" 
                        />
                    </div>
                    <button className="button" type="submit">Fazer login</button>
                </form>
            </section>  
        </div>
    );

}