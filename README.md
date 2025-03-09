# Authentication-2FA-and-JWT-token

![GitHub repo size](https://img.shields.io/github/repo-size/wandersonlira/authentication-2FA-and-JWT-token?style=for-the-badge)
![GitHub language count](https://img.shields.io/github/languages/count/wandersonlira/authentication-2FA-and-JWT-token?style=for-the-badge)
![GitHub forks](https://img.shields.io/github/forks/wandersonlira/authentication-2FA-and-JWT-token?style=for-the-badge)
![Bitbucket open issues](https://img.shields.io/github/issues/wandersonlira/authentication-2FA-and-JWT-token?style=for-the-badge)

> [!NOTE]
> **Este projeto implementa um sistema de autenticação eficiente, combinando autenticação de dois fatores (2FA) com token JWT. O sistema segue boas práticas de segurança e busca fornecer uma solução robusta para o processo de login. A arquitetura é dividida entre frontend e backend, onde o backend é responsável pela geração e validação de tokens JWT e pela implementação do 2FA, enquanto o frontend lida com a interface e manipulação do token para o acesso às rotas.**

## 📝 Sobre

A ideia inicial era apenas estudar sobre os algoritmos HOTP e TOTP e como eles funcionam em autenticação de dois fatores (2FA). No entanto, à medida que fui aprofundando no estudo, decidi criar um mini sistema para implementar essas técnicas e compartilhar o código no GitHub.

Este projeto implementa um sistema de autenticação que utiliza autenticação de dois fatores (2FA) combinada com token JWT para um login eficiente e seguro. 

## 🏗️ Arquitetura

A arquitetura do sistema é dividida da seguinte forma:

- **Backend**: Responsável pela geração e validação do token JWT, além de implementar o sistema de autenticação de dois fatores (2FA). O backend é o único componente que possui a lógica de negócios relacionada ao usuário como o e-mail, garantindo que mesmo se um atacante souber o nome de usuário e senha, ele não terá acesso ao e-mail do usuário ou aos códigos de autenticação gerados.
  
- **Frontend**: Lida com a interface de usuário, permitindo que o usuário forneça seu token para acessar as rotas do sistema. O frontend não possui acesso direto às informações sensíveis do backend.

O sistema foi projetado utilizando **Clean Architecture** e o princípio de **Inversão de Dependência**, para garantir um bom desacoplamento e organização do código.

## ⚙️ Tecnologias Utilizadas

- **HMAC256**: Utilizado para garantir a integridade dos dados e autenticar a origem das mensagens.
- **PBKDF2**: Algoritmo para derivação de chaves criptográficas a partir de senhas, transformando uma senha simples em uma chave segura.
- **Tokens JWT**: Utilizados para autenticação e autorização no sistema, com políticas de blacklist e refresh tokens.
- **Flyway Migrations**: Para versionamento de banco de dados e controle das mudanças de esquema.
- **Spring Data JPA**: Para simplificar e otimizar a manipulação de dados.
- **Spring Security**: Utilizado para fornecer segurança especialmente no contexto de autenticação e autorização.

## 🔜 Próximas Implementações

- **Integração com Google Authenticator**: Adicionar suporte para autenticação via Google Authenticator.
- **Reenvio de código de 6 dígitos**: Já implementado no backend, permitirá reenvio caso o código expire.
- **Redefinição de senha**: Implementação de funcionalidade para redefinir senha no sistema.

## ⚠️ Considerações

- O frontend foi projetado para ser amigável e funcional, embora não seja o foco principal deste projeto.
- A forma como os access tokens e refresh tokens estão armazenados atualmente não segue as melhores práticas de segurança recomendadas para um ambiente de produção. Para mais detalhes, recomendo a seguinte leitura: [JWT Seguro - Blog L. Santos](https://blog.lsantos.dev/jwt-seguro/)


## 📽️ Vídeo de Demonstração
### Interface do usuário
O vídeo demonstra o funcionamento do sistema e como a autenticação de dois fatores (2FA) com JWT é implementada permitindo ao usuário acesso as rotas de forma segura.

https://github.com/user-attachments/assets/30ddb2d4-5e5c-42a7-8140-7b255ef69fe6

## 🧑‍💻 Backend
### 1. Authentication-2FA

A imagem abaixo ilustra a confirmação do código de 6 dígitos enviado para o e-mail do usuário registrado no sistema, acrescentando uma camada adicional de segurança.

![Image](https://github.com/user-attachments/assets/77931929-27db-4788-9df9-befc07f79576)

### 2. JWT-token

Após a validação do código, é gerada a autenticação utilizando um token JWT, o qual concede ao usuário as permissões necessárias para acessar os endpoints. Quando o token expira, um refresh token, com um período de validade mais longo, pode ser gerado, permitindo que o usuário permaneça autenticado sem a necessidade de realizar o login novamente.

***Observação:*** *Neste exemplo, o token tem uma duração de 3 minutos, configurado para facilitar a realização de testes.*

![Image](https://github.com/user-attachments/assets/c718421c-0e7d-4431-a17c-bc534487a151)
