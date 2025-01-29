import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { FiPower, FiEdit, FiTrash2 } from "react-icons/fi";

import './styles.css';
import LogoImage from '../../assets/logo.svg'


export default function Book() {
    const navigate = useNavigate();
    const [userInfo, setUserInfo] = useState({ username: '', accessToken: '' });

    useEffect(() => {
        const username = localStorage.getItem('username');
        const accessToken = localStorage.getItem('accessToken');
        if (username && accessToken) {
            setUserInfo({ username, accessToken });
        } else {
            alert('User not authenticated!');
            navigate('/login');
        }
    }, [navigate]);

    return (
        <div className="book-container">
            <header>
                <img src={LogoImage} alt="Lira" />
                <span>Bem-vindo, <strong>{userInfo.username}</strong></span>
                <Link className="button">Novo Livro</Link>
                <button type="button">
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