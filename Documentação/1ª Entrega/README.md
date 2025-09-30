#  Best Umbrella  

**Projeto Mobile – Universidade Europeia / IADE**  
**Licenciatura em Engenharia Informática – 3º Semestre (2025/2026)**  

---

##  Identificação
- **Grupo:** G03  
- **Elementos:** Fábio Texeira, Feleciano Barata, Márcio Quintas, Joybeth Mateus e Taha-Wur Pereira.   
- **Nome do Projeto:** Best Umbrella  

---

##  Descrição  

O **Best Umbrella** é uma aplicação móvel que surgiu para resolver um problema comum em cidades: ser apanhado pela chuva sem proteção e acabar por comprar um guarda-chuva descartável de baixa qualidade.  
A proposta é simples e sustentável: oferecer um serviço de **aluguer temporário de guarda-chuvas** em pontos estratégicos da cidade, como universidades, estações, cafés parceiros e centros comerciais.  

O projeto aposta num modelo inovador e acessível, sem necessidade de infraestruturas caras como máquinas automáticas. Basta um **smartphone** e um **QR Code** para aceder ao serviço.  

---

##  Objetivos  

O principal objetivo do Best Umbrella é disponibilizar uma **experiência urbana prática, sustentável e inteligente**.  
Mais do que apenas um sistema de aluguer, o projeto pretende educar os utilizadores para hábitos de consumo conscientes, diminuindo o desperdício associado a guarda-chuvas descartáveis.  

Diferente de outras soluções internacionais, a app posiciona-se como uma alternativa **flexível, de baixo custo e altamente escalável**, capaz de crescer rapidamente em qualquer cidade através de **parcerias locais**.  

Com a integração de **APIs meteorológicas**, o Best Umbrella não apenas responde a necessidades imediatas, mas também atua de forma **proativa**, enviando notificações quando a chuva está prestes a começar, incentivando a reserva antecipada.  

---

## Público-Alvo  

- **Estudantes e trabalhadores urbanos** que se deslocam frequentemente e necessitam de soluções rápidas.  
- **Turistas**, que preferem alugar em vez de comprar um guarda-chuva que dificilmente irão usar mais de uma vez.  
- **Empresas e estabelecimentos parceiros**, que podem beneficiar de visibilidade e aumento do fluxo de clientes ao oferecerem pontos de recolha/devolução.  

---

##  Pesquisa de Mercado  

Existem empresas internacionais que já exploram o conceito de partilha de guarda-chuvas:  

- **Rentbrella (Brasil):** disponibiliza guarda-chuvas através de **estações automáticas** instaladas em locais estratégicos. Embora eficiente, exige investimento elevado em infraestrutura física.  
- **UmbraCity (Canadá):** opera principalmente em **campus universitários**, funcionando como uma biblioteca de guarda-chuvas para estudantes e professores. É útil em ambientes académicos, mas pouco escalável a nível urbano.  

O **Best Umbrella** diferencia-se por não depender de máquinas ou sistemas centralizados. Em vez disso, aposta em **QR Codes em pontos parceiros**, garantindo **baixo custo de implementação, maior flexibilidade e expansão rápida**.  

---

## Guiões de Teste  

A aplicação foi pensada com diferentes cenários de utilização que simulam situações reais:  

**1. Alugar um guarda-chuva**  
O utilizador abre a aplicação, consulta o mapa e identifica um ponto de aluguer próximo. Ao chegar ao local, faz o scan do QR Code para desbloquear o guarda-chuva e o sistema regista automaticamente o início do aluguer.  

**2. Devolver um guarda-chuva**  
Depois de utilizar, o utilizador escolhe no mapa um ponto de devolução. No local, faz novamente o scan do QR Code e o sistema confirma a entrega, encerrando o aluguer.  

**3. Receber alerta de chuva**  
Com base na API meteorológica, a app envia uma notificação quando a chuva está prestes a começar. O utilizador recebe o alerta no telemóvel e pode reservar de imediato um guarda-chuva num ponto próximo.  

**4. Consultar histórico de alugueres**  
O utilizador pode aceder ao seu perfil e verificar todos os alugueres efetuados, incluindo datas, locais de recolha e devolução, e custos associados.  

---

##  Requisitos  

**Funcionais**  
O sistema deve permitir que o utilizador se registe e faça login de forma segura, que reserve um guarda-chuva antecipadamente, que inicie e encerre um aluguer através da leitura de QR Codes e que consulte o histórico de utilizações. Além disso, a aplicação deve enviar notificações meteorológicas e possibilitar a avaliação da experiência em cada ponto parceiro.  

**Não Funcionais**  
A aplicação deve ser compatível com Android 10 ou superior, garantir conformidade com o RGPD e proteger todos os dados pessoais através de encriptação. A base de dados deverá ser relacional, segura e escalável, e a interface deve apresentar uma navegação intuitiva, rápida e acessível para qualquer utilizador.  

---

##  Modelo de Domínio  

O modelo inicial é composto por quatro entidades principais que estruturam o sistema:  
- O **Utilizador**, que possui identificação única, nome, email, password encriptada e um rating associado.  
- O **GuardaChuva**, identificado por um estado e localização, sendo registado em diferentes pontos da cidade.  
- O **Aluguer**, que guarda toda a informação relativa ao processo, como as datas de início e fim, custo e associação ao utilizador.  
- O **Ponto de Aluguer**, que representa o local físico e o parceiro responsável pelo guarda-chuva.  

Este modelo poderá ser expandido com novas entidades, como notificações personalizadas e sistema de fidelização.  

---


## 💳 Modelo de Pagamento  

Um dos aspetos centrais do **Best Umbrella** é garantir que o processo de aluguer seja simples e acessível. Para isso, a aplicação irá suportar diferentes métodos de pagamento digitais, adequados tanto para utilizadores locais como turistas.  

### Métodos de Pagamento Disponíveis  
- **Cartão de Crédito/Débito** → integrado através de serviços como **Stripe** ou **Adyen**, garantindo segurança e conformidade com normas internacionais (PCI DSS).  
- **MB WAY** → solução popular em Portugal, ideal para estudantes e trabalhadores urbanos que procuram rapidez em microtransações.  
- **Google Pay e Apple Pay** → pagamentos móveis sem necessidade de inserir dados de cartão sempre que for feito um aluguer.  
- **PayPal** → pensado para turistas, permitindo uma opção internacionalmente reconhecida.  

### Modelos de Utilização  
- **Pay-per-use (pagar por utilização):** o utilizador paga apenas pelo tempo de utilização do guarda-chuva (ex.: 1€ por 24h).  
- **Depósito reembolsável:** para incentivar a devolução, o utilizador paga um valor de caução (ex.: 5€) que é devolvido assim que o guarda-chuva for entregue num ponto autorizado.  
- **Subscrição mensal:** opção para utilizadores frequentes, com um valor fixo (ex.: 5€/mês) que permite alugueres ilimitados.  
- **Campanhas promocionais:** primeiros 30 minutos grátis ou descontos em parcerias com cafés, universidades e centros comerciais, para atrair novos utilizadores.  

### Depósito de Segurança  
O **depósito reembolsável** funciona como um mecanismo de confiança:  
1. No momento do primeiro aluguer, é cobrado um valor de caução (ex.: 5€).  
2. Este valor fica bloqueado no método de pagamento do utilizador, mas não é consumido caso o guarda-chuva seja devolvido.  
3. Assim que a devolução é confirmada via QR Code, o sistema liberta o depósito.  
4. Caso o guarda-chuva não seja devolvido no prazo definido, o valor da caução é convertido em pagamento automático, cobrindo o custo de reposição.  

Este sistema garante que os utilizadores têm motivação para devolver os guarda-chuvas e reduz drasticamente o risco de perdas para a plataforma.  

### Estratégia Inicial  
Na primeira fase, o **Best Umbrella** irá disponibilizar **Cartão de Crédito/Débito via Stripe** e **MB WAY** como principais métodos de pagamento. Estes garantem simplicidade e abrangem a maioria dos utilizadores em Portugal.  
Num segundo momento, será integrada a compatibilidade com **Google Pay, Apple Pay e PayPal**, de forma a ampliar o alcance para turistas e mercados internacionais.  

---

##  Mockups  

Os primeiros protótipos desenvolvidos no Figma apresentam:  
- Um **mapa interativo** com os pontos de aluguer disponíveis.  
- Um **ecrã de reserva** que confirma a disponibilidade e o tempo de utilização.  
- Um **scanner de QR Code** integrado na aplicação, que simplifica o processo de recolha e devolução.  
- Um **histórico de alugueres** com detalhes sobre datas, locais e custos.  
- Um **sistema de notificações**, que alerta para chuva iminente ou promoções de parceiros.  

*(https://www.figma.com/make/R6owKAQrHkWpdB7z8hp85O/Umbrella-Sharing-App?node-id=0-1&t=pFGLOQdrlCiDIgsW-1)*  

---

##  Roadmap  

- **Entrega 1 (set 2025):** Ideia, pesquisa de mercado, requisitos e mockups iniciais  
- **Entrega 2 (nov 2025):** Protótipo funcional (login, mapa, reservas)  
- **Entrega 3 (jan 2026):** Integração de QR Code e API meteorológica  
- **Entrega 4 (fev 2026):** Versão final com testes e apresentação  

---

##  Conclusão  

O **Best Umbrella** é mais do que uma simples aplicação de aluguer: é uma resposta tecnológica a um problema urbano real. O projeto alia **sustentabilidade, inovação e conveniência**, oferecendo uma alternativa prática e responsável para os utilizadores.  

Com um modelo escalável, sem dependência de infraestruturas complexas, o Best Umbrella está preparado para crescer em diferentes cidades, promovendo **mobilidade urbana sustentável** e incentivando o consumo consciente.  

---








