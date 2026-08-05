# Ludex

Hub de avaliação de jogos e comparador de preços (Steam, Epic, Nuuvem, GOG), com foco no mercado BR.

## Stack

- **Frontend**: React 19 + TypeScript + Vite + Tailwind CSS v4
- **Backend**: Java 21 + Spring Boot 4.1 + Spring Data JPA
- **Banco**: PostgreSQL

## Rodando localmente

### Backend
```
cd backend
./mvnw spring-boot:run
```
Sobe em `http://localhost:8080`. Precisa de um Postgres local — configure via variáveis de ambiente `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD` (defaults em `application.properties` apontam pra `localhost:5433/ludex`).

### Frontend
```
cd frontend
npm install
npm run dev
```
Sobe em `http://localhost:5173`.

## Deploy

- Frontend → Vercel
- Backend + banco → Render (free tier)
