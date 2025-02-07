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
            localStorage.setItem('username', username);
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

// ------------------------------------------   N E W   I M P L E M E N T A T I O N   T E S T   -----------------------------------------


// import React, { useState } from 'react';

// // Componente para o cadastro de novo livro
// function CadastroLivro({ onCadastrar }) {
//   const [titulo, setTitulo] = useState('');
//   const [autor, setAutor] = useState('');

//   const handleSubmit = (e) => {
//     e.preventDefault();
//     onCadastrar({ titulo, autor });
//     setTitulo('');
//     setAutor('');
//   };

//   return (
//     <div>
//       <h2>Cadastrar Novo Livro</h2>
//       <form onSubmit={handleSubmit}>
//         <div>
//           <label>Título:</label>
//           <input
//             type="text"
//             value={titulo}
//             onChange={(e) => setTitulo(e.target.value)}
//             required
//           />
//         </div>
//         <div>
//           <label>Autor:</label>
//           <input
//             type="text"
//             value={autor}
//             onChange={(e) => setAutor(e.target.value)}
//             required
//           />
//         </div>
//         <button type="submit">Cadastrar</button>
//       </form>
//     </div>
//   );
// }

// // Componente principal (onde listamos os livros)
// function App() {
//   const [livros, setLivros] = useState([
//     { titulo: 'Livro A', autor: 'Autor A' },
//     { titulo: 'Livro B', autor: 'Autor B' }
//   ]);
//   const [showCadastro, setShowCadastro] = useState(false);

//   const handleCadastrarLivro = (livro) => {
//     setLivros([...livros, livro]);
//     setShowCadastro(false); // Fecha a tela de cadastro após o livro ser cadastrado
//   };

//   return (
//     <div>
//       <h1>Lista de Livros</h1>
      
//       {/* Botão para mostrar/ocultar a tela de cadastro */}
//       <button onClick={() => setShowCadastro(!showCadastro)}>
//         {showCadastro ? 'Voltar para Lista' : 'Cadastrar Novo Livro'}
//       </button>
      
//       {/* Renderiza a tela de cadastro condicionalmente */}
//       {showCadastro ? (
//         <CadastroLivro onCadastrar={handleCadastrarLivro} />
//       ) : (
//         <div>
//           <ul>
//             {livros.map((livro, index) => (
//               <li key={index}>{livro.titulo} - {livro.autor}</li>
//             ))}
//           </ul>
//         </div>
//       )}
//     </div>
//   );
// }

// export default App;
