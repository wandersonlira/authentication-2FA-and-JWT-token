# Authentication-2FA-and-JWT-token
> [!NOTE]
> **Este projeto implementar um sistema de autenticação, combinando autenticação de dois fatores (2FA) com tokens JWT para oferecer um login seguro e eficiente. A arquitetura do sistema envolve tanto a parte backend quanto o frontend, sendo o backend responsável pela geração e validação do token JWT e pela implementação do 2FA, enquanto o frontend, desenvolvido em React.js, lida com a visualização da interface.**
>
> **O sistema de autenticação implementado neste projeto já possui a integração funcional tanto da autenticação de dois fatores (2FA) quanto do token JWT. A autenticação de dois fatores está operacional, sendo realizada por meio do envio de um código único por e-mail, garantindo uma camada adicional de segurança no processo de login. Além disso, o token JWT está sendo gerado com sucesso após o login, permitindo o acesso a recursos protegidos com mecanismos de expiração e renovação para maior segurança.**
>
> **Atualmente, o frontend já gerencia a autenticação e autorização do usuário com 2FA e token JWT, proporcionando uma experiência fluida de login. A próxima fase do projeto é expandir para outras formas de 2FA, como `SMS` e `WhatsApp`.**
>
> **Essa combinação de 2FA e JWT proporciona uma solução robusta e eficiente, oferecendo um sistema de login seguro e com boa escalabilidade.**

## 🎨 Frontend
### Interface do usuário
O vídeo abaixo demonstram a navegação desde a inserção do nome de usuário e senha, passando pela autenticação do código de 6 dígitos, até o acesso à tela principal, com base nas permissões do usuário.

https://github.com/user-attachments/assets/32f615d2-a126-49f7-b972-a5e52679bb8a

## 🧑‍💻 Backend
### 1. Authentication-2FA

A imagem abaixo ilustra a confirmação do código de 6 dígitos enviado para o e-mail do usuário registrado no sistema, acrescentando uma camada adicional de segurança.

![Image](https://github.com/user-attachments/assets/77931929-27db-4788-9df9-befc07f79576)

### 2. JWT-token

Após a validação do código, é gerada a autenticação utilizando um token JWT, o qual concede ao usuário as permissões necessárias para acessar os endpoints. Quando o token expira, um refresh token, com um período de validade mais longo, pode ser gerado, permitindo que o usuário permaneça autenticado sem a necessidade de realizar o login novamente.

***Observação:*** *Neste exemplo, o token tem uma duração de 3 minutos, configurado para facilitar a realização de testes.*

![Image](https://github.com/user-attachments/assets/c718421c-0e7d-4431-a17c-bc534487a151)
