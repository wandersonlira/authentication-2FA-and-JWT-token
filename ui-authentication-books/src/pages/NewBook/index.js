import React, {useState, useEffect, useCallback} from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { FiArrowLeft } from "react-icons/fi";

import api from '../../services/api';

import './styles.css';

import logo from '../../assets/logo.svg';


export default function NewBook() {
    const [id, setId] = useState(null);
    const [author, setAuthor] = useState('');
    const [launchDate, setLaunchDate] = useState('');
    const [price, setPrince] = useState('');
    const [title, setTitle] = useState('');

    const navigate = useNavigate();
    const {bookId} = useParams();

    const accessToken = localStorage.getItem('accessToken');

    const loadBook = useCallback( async () => {
        try {
            const response = await api.get(`api/books/v1/${bookId}`, {
                headers: {
                    Authorization: `Bearer ${accessToken}`
                }
            });
            let adjustedDate = response.data.launchDate.split("T", 10)[0];
            setId(response.data.id);
            setTitle(response.data.title);
            setAuthor(response.data.author);
            setPrince(response.data.price);
            setLaunchDate(adjustedDate);
        } catch (error) {
            alert('Erro ao carregar livro! Tente novamente!');
            navigate('/book')
        }
    }, [accessToken, bookId, navigate]);
    

    useEffect(() => {
        if (bookId === '0') return;
        else loadBook();
    }, [bookId, loadBook]);


    async function saveOrUpdate(e) {
        e.preventDefault();
        const data = {title, author, launchDate, price}
        try {
            if (bookId === '0') {
                await api.post('api/books/v1/register', data, {
                    headers: {
                        Authorization: `Bearer ${accessToken}`
                    }
                }); 
            } else {
                await api.put(`api/books/v1/${id}`, data, {
                    headers: {
                        Authorization: `Bearer ${accessToken}`
                    }
                }); 
            }
            navigate('/book')    
        } catch (error) {
            alert('Erro ao registrar o livro! Tente novamente!');
        }
    }


    return (
        <div className="new-book-container">
            <div className="content">
                <section className="form">
                    <img src={logo} alt="Lira" />
                    <h1>{bookId === '0' ? 'Adicionar' : 'Atualizar'} Novo Livro</h1>
                    <p>Insira as informações do livro e clique em {bookId === '0' ? "'Adicionar'" : "'Atualizar'"}!</p>
                    <Link className="back-link" to="/book">
                        <FiArrowLeft size={16} color="#251fc5" />
                        Voltar aos livros
                    </Link>
                </section>
                <form onSubmit={saveOrUpdate}>
                    <input
                        type="text" 
                        placeholder="Título" 
                        value={title}
                        onChange={e => setTitle(e.target.value)}
                        />
                    <input
                        type="text" 
                        placeholder="Autor"
                        value={author}
                        onChange={e => setAuthor(e.target.value)}
                        />
                    <input
                        type="date" 
                        placeholder="Data"
                        value={launchDate}
                        onChange={e => setLaunchDate(e.target.value)}
                        />
                    <input
                        type="month" 
                        placeholder="Preço" 
                        value={price}
                        onChange={e => setPrince(e.target.value)}
                        />

                    <button className="button" type="submit">{bookId === '0' ? 'Adicionar' : 'Atualizar'}</button>
                </form>
            </div>
        </div>
    );
}