# Authentication-2FA-and-JWT-token
> [!NOTE]
> **Este projeto implementar um sistema de autenticação, combinando autenticação de dois fatores (2FA) com tokens JWT para oferecer um login seguro e eficiente. A arquitetura do sistema envolve tanto a parte backend quanto o frontend, sendo o backend responsável pela geração e validação do token JWT e pela implementação do 2FA, enquanto o frontend, desenvolvido em React.js, lida com a visualização da interface.**
>
> **Atualmente, o sistema de autenticação de dois fatores está funcional, sendo realizado através do envio de um código único por e-mail. Contudo, essa funcionalidade de 2FA ainda não foi integrada ao frontend, que no momento está utilizando exclusivamente o token JWT para gerenciar o processo de autenticação e autorização do usuário.**
>
> **Por sua vez, o token JWT é gerado após o login bem-sucedido, garantindo que o usuário tenha acesso a recursos protegidos, com mecanismos de expiração e renovação de token para maior segurança. A interface do frontend, desenvolvida em React.js, já está configurada para permitir uma experiência de usuário fluida, exibindo as telas de login e autenticação com o token JWT.**
>
> **Embora a autenticação de dois fatores já esteja em funcionamento no backend, ela será integrada à interface do frontend em uma próxima fase do projeto. A meta é expandir a solução, permitindo que os usuários possam também utilizar outras formas de 2FA, como `SMS` e `WhatsApp`, além do `e-mail`.**

## 🎨 Frontend
### Interface do usuário
As telas abaixo demonstram a navegação desde a inserção do nome de usuário e senha, passando pela autenticação do código de 6 dígitos, até o acesso à tela principal, com base nas permissões do usuário.

![login](https://github.com/user-attachments/assets/9f0e33d1-a960-45e6-8dd8-a3f67ff17bb5)
![auth](https://github.com/user-attachments/assets/5946d0af-5a8e-4861-8966-1fabe386f191)
![book](https://github.com/user-attachments/assets/4c9926fd-23c0-4182-b302-616e8a0766c4)

## 🧑‍💻 Backend
### 1. Authentication-2FA

A imagem abaixo ilustra a confirmação do código de 6 dígitos enviado para o e-mail do usuário registrado no sistema, acrescentando uma camada adicional de segurança.

![Image](https://github.com/user-attachments/assets/77931929-27db-4788-9df9-befc07f79576)

### 2. JWT-token

Após a validação do código, é gerada a autenticação utilizando um token JWT, o qual concede ao usuário as permissões necessárias para acessar os endpoints. Quando o token expira, um refresh token, com um período de validade mais longo, pode ser gerado, permitindo que o usuário permaneça autenticado sem a necessidade de realizar o login novamente.

***Observação:*** *Neste exemplo, o token tem uma duração de 3 minutos, configurado para facilitar a realização de testes.*

![Image](https://github.com/user-attachments/assets/d119f497-9b17-4227-ace9-cd744f7a8fe3)
