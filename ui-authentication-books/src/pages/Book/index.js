import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { FiPower, FiEdit, FiTrash2 } from "react-icons/fi";

import './styles.css';
import LogoImage from '../../assets/logo.svg'
import api from "../../services/api";


export default function Book() {
    const navigate = useNavigate();
    const [userInfo, setUserInfo] = useState({ fullName: '', accessToken: '', refreshToken: ''});
    const [books, setBooks] = useState([]);

    useEffect(() => {
        const localStorag_fullName = localStorage.getItem('fullName');
        const localStorag_accessToken = localStorage.getItem('accessToken');
        const localStorage_refreshToken = localStorage.getItem('refreshToken');
        if (localStorag_fullName && localStorag_accessToken && localStorage_refreshToken) {
            setUserInfo({ fullName: localStorag_fullName, accessToken: localStorag_accessToken, refreshToken: localStorage_refreshToken});
        } else {
            localStorage.clear();
            alert('Usuário não autenticado!');
            navigate('/');
        }

        api.get('/api/books/v1/', {
            headers: {
                Authorization: `Bearer ${localStorag_accessToken}`
            }
        }).then(response => {
            setBooks(response.data)
        });
        
    }, [navigate]);


    const logout = () => {
        localStorage.clear();
        navigate('/');
    };


    async function editBook(id) {
        try {
            navigate(`new/${id}`)
        } catch (error) {
            alert(`Houve um erro: ${error.response ? error.response.data : error.message}`);
        }     
    };

    async function deleteBook(id) {
        try {
            await api.delete(`/api/books/v1/${id}`, {
                headers: {
                    Authorization: `Bearer ${userInfo.accessToken}`
                }
            });
            setBooks(books.filter(book => book.id !== id));            
        } catch (error) {
            alert(`Houve um erro: ${error.response ? error.response.data : error.message}`);
        }    
    };


    return (
        <div className="book-container">
            <header>
                <img src={LogoImage} alt="Lira" />
                <span>Bem-vindo, <strong>{userInfo.fullName}</strong></span>
                <Link className="button" to="new/0">Novo Livro</Link>
                <button
                 type="button"
                 onClick={logout}>
                    <FiPower size={18} color="#251fc5" />
                </button>
            </header>

            <h1>Livros Registrados</h1>
            <ul>
                {books.map(book => (
                    <li key={book.id}>
                        <strong>Título:</strong>
                        <p>{book.title}</p>
                        <strong>Autor:</strong>
                        <p>{book.author}</p>
                        <strong>Preço:</strong>
                        <p>{Intl.NumberFormat('pt-BR', {style: 'currency', currency: 'BRL'}).format(book.price)}</p>
                        <strong>Data de lançamento:</strong>
                        <p>{Intl.DateTimeFormat('pt-BR', { timeZone: 'UTC' }).format(new Date(book.launchDate))}</p>

                        <button type="button" onClick={() => editBook(book.id)}>
                            <FiEdit size={20} color="#A73D40" />
                        </button>
                        <button onClick={() => deleteBook(book.id)} type="button">
                            <FiTrash2 size={20} color="#A73D40" />
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    );

}