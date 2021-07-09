# coleta-consciente-backend


para rodar o projeto:
  subir docker compose
  subir autorization-server
  subir user
  
  
api de login
http://localhost:9092/oauth/token?grant_type=password&username={email}&password={password}

user padrão: admin@admin.com
password padrão: 123456

api cadastro:
localhost:8080/api/user

body:
{
    "name": "Jefferson",
    "email": "jefferson_marques17@hotmail.com",
    "password": "123456"
}

api get user:
localhost:8080/api/user/{id_usuario}

lembrando que para fazer as chamadas precisa mandar o:
Authorization: Bearer {token da api de login}
