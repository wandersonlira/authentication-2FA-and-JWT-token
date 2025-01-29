import React, {useState} from "react";
import { FiLock} from "react-icons/fi";
import './styles.css';
import logoAuth from '../../../assets/logoAuth.png'


export default function SendCode() {
    const [code, setCode] = useState('');

    const handleChange = (event) => {
        const value = event.target.value;

        if (/^\d*$/.test(value)) {
            setCode(value);
        }
    };


    return (
        <div className="send-container">
            <img src={logoAuth} alt="auth-logo" />
            <section className="form">
                <form onSubmit="">
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