import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { FiPower, FiEdit, FiTrash2 } from "react-icons/fi";

import './styles.css';
import LogoImage from '../../assets/logo.svg'
import api from "../../services/api";


export default function Book() {
    const navigate = useNavigate();
    const [userInfo, setUserInfo] = useState({ fullName: '', accessToken: '' });

    useEffect(() => {
        const localStorag_fullName = localStorage.getItem('localStorage_fullName');
        const localStorag_accessToken = localStorage.getItem('accessToken');
        if (localStorag_fullName && localStorag_accessToken) {
            setUserInfo({ fullName: localStorag_fullName, accessToken: localStorag_accessToken });
        } else {
            alert('Usuário não autenticado!');
            navigate('/');
        }
    }, [navigate]);


    const handleClick = () => {
        localStorage.clear();
        navigate('/');

    };

    async function editBook(e) {
        e.preventDefault();
        try {
            console.log(`TOKEN: ${userInfo.accessToken}`)
            const response = await api.get('/auth/edit', {
                headers: {
                    Authorization: `Bearer ${userInfo.accessToken}`
                }
            });      
            alert(`Livro editado com sucesso! Status: ${response.status}`)    
        } catch (error) {
            alert(`Houve um erro: ${error.response ? error.response.data : error.message}`);
        }     
    }


    return (
        <div className="book-container">
            <header>
                <img src={LogoImage} alt="Lira" />
                <span>Bem-vindo, <strong>{userInfo.fullName}</strong></span>
                <Link className="button">Novo Livro</Link>
                <button
                 type="button"
                 onClick={handleClick}>
                    <FiPower size={18} color="#251fc5" />
                </button>
            </header>

            <h1>Livros Registrados</h1>
            <ul>
                <li>
                    <strong>Título:</strong>
                    <p>A revolução dos bichos</p>
                    <strong>Autor:</strong>
                    <p>George Orwell</p>
                    <strong>Preço:</strong>
                    <p>R$ 29,90</p>
                    <strong>Data de lançamento:</strong>
                    <p>10/01/2007</p>

                    <button type="button" onClick={editBook}
                    >
                        <FiEdit size={20} color="#A73D40" />
                    </button>
                    <button type="button">
                        <FiTrash2 size={20} color="#A73D40" />
                    </button>
                </li>

                <li>
                    <strong>Título:</strong>
                    <p>A revolução dos bichos</p>
                    <strong>Autor:</strong>
                    <p>George Orwell</p>
                    <strong>Preço:</strong>
                    <p>R$ 29,90</p>
                    <strong>Data de lançamento:</strong>
                    <p>10/01/2007</p>

                    <button type="button">
                        <FiEdit size={20} color="#A73D40" />
                    </button>
                    <button type="button">
                        <FiTrash2 size={20} color="#A73D40" />
                    </button>
                </li>

                <li>
                    <strong>Título:</strong>
                    <p>A revolução dos bichos</p>
                    <strong>Autor:</strong>
                    <p>George Orwell</p>
                    <strong>Preço:</strong>
                    <p>R$ 29,90</p>
                    <strong>Data de lançamento:</strong>
                    <p>10/01/2007</p>

                    <button type="button">
                        <FiEdit size={20} color="#A73D40" />
                    </button>
                    <button type="button">
                        <FiTrash2 size={20} color="#A73D40" />
                    </button>
                </li>

                <li>
                    <strong>Título:</strong>
                    <p>A revolução dos bichos</p>
                    <strong>Autor:</strong>
                    <p>George Orwell</p>
                    <strong>Preço:</strong>
                    <p>R$ 29,90</p>
                    <strong>Data de lançamento:</strong>
                    <p>10/01/2007</p>

                    <button type="button">
                        <FiEdit size={20} color="#A73D40" />
                    </button>
                    <button type="button">
                        <FiTrash2 size={20} color="#A73D40" />
                    </button>
                </li>
            </ul>
        </div>
    );
}