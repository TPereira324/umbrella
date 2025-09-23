# 🌂 Best Umbrella – Entrega 1

**Projeto Mobile – Universidade Europeia**  
**Licenciatura em Engenharia Informática – 3º Semestre (2025/2026)**  

---

## 📌 Identificação
- **Grupo:** g03  
- **Elementos:** Feleciano Barata, Márcio Quintas, Taha-Wur Pereira.   
- **Nome do Projeto:** Best Umbrella  


---

##  Palavras-chave
Aluguer, guarda-chuva, Android, Kotlin, Spring Boot, PostgreSQL, mobilidade urbana, sustentabilidade.  

---

##  Descrição
O **Best Umbrella** é uma aplicação móvel para **aluguer de guarda-chuvas** em locais estratégicos da cidade (estações, universidades, cafés parceiros, centros comerciais).  

O projeto pretende responder ao problema frequente de utilizadores apanhados pela chuva inesperada, evitando a compra de guarda-chuvas descartáveis.  

---

##  Objetivos
- Criar uma plataforma de **aluguer de guarda-chuvas** fácil e acessível.  
- Reduzir o desperdício associado ao consumo de guarda-chuvas baratos e descartáveis.  
- Integrar **geolocalização, QR Codes e API meteorológica**.  
- Desenvolver backend com **Spring Boot** e base de dados relacional em **PostgreSQL/MySQL**.  

---

##  Público-Alvo
- Estudantes e trabalhadores urbanos.  
- Turistas que não querem comprar guarda-chuva.  
- Empresas que queiram disponibilizar pontos de aluguer.  

---

##  Pesquisa de Mercado
- **Rentbrella (Brasil)** – sistema de estações automáticas de aluguer.  
- **UmbraCity (Canadá)** – aluguer em campus universitários.  
- **Best Umbrella** → foco em **pontos parceiros com QR Code**, solução simples e escalável.  

---

##  Guiões de Teste
### Caso Core – **Alugar um guarda-chuva**
1. Utilizador abre a app.  
2. Vê no mapa um ponto de aluguer disponível.  
3. Reserva um guarda-chuva.  
4. No local, faz scan QR Code e inicia aluguer.  

### Caso 2 – **Devolver guarda-chuva**
1. Utilizador abre o mapa e vê pontos de devolução.  
2. Escolhe um próximo e faz scan QR Code.  
3. O sistema confirma devolução e encerra aluguer.  

### Caso 3 – **Receber alerta de chuva**
1. API Meteo prevê chuva em breve.  
2. Utilizador recebe notificação: “Vai chover em 15min. Reserva já um guarda-chuva!”.  
3. Pode reservar diretamente a partir da notificação.  

---

##  Requisitos
### Funcionais
- Registo/Login.  
- Reserva de guarda-chuva.  
- Aluguer via QR Code.  
- Devolução em pontos registados.  
- Histórico de alugueres.  
- Notificações meteorológicas.  

### Não Funcionais
- Compatível com Android 10+.  
- Cumprimento do RGPD.  
- BD relacional e segura.  
- Interface intuitiva.  

---

##  Modelo de Domínio (inicial)
- **Utilizador** (id, nome, email, password, rating).  
- **GuardaChuva** (id, estado, localização).  
- **Aluguer** (id, utilizador_id, guarda_chuva_id, data_início, data_fim, custo).  
- **PontoAluguer** (id, nome, localização).  

---

##  Mockups (Figma – versão inicial)
- **Mapa** com pontos de aluguer.  
- **Ecrã de reserva**.  
- **Ecrã QR Code scan**.  
- **Histórico de alugueres**.  

*(https://www.figma.com/make/R6owKAQrHkWpdB7z8hp85O/Umbrella-Sharing-App?node-id=0-1&t=pFGLOQdrlCiDIgsW-1)*  

---



