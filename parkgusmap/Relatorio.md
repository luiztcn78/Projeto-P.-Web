# RELATÓRIO DE PROJETO – Estratégias de Branching em Git
  
## Identificação - **Integrantes da equipe**:
- Vinícius Gabriel Santos Bezerra
- Marcelo Arthur Santos Moura
- Luiz Tenório Carvalho Neto
- **Repositório GitHub**: https://github.com/luiztcn78/Projeto-P.-Web.git

## 1. Estrutura Inicial 
- Branch principal (`main`) criada e configurada? 
``` Sim, a branch Main foi criada e configurada em 20 de setembro e já estava sendo utilizada desde então.```
- Branch `develop` criada a partir de `main`?   
``` Sim, a branch Develop foi criada pouco tempo depois da criação da branch main para melhor organização do código.```
- Descreva como foi feita a configuração inicial (comandos usados e prints).
``` Após a criação do repositório, foram adicionados os arquivos: README.md e LICENSE.txt, utilizando os comandos: "git init", "git remote",  "git add" e "git push", comandos que foram utilizados um tempo depois para a adição dos primeiros códigos do projeto.```
<img src="imagens/InicioCmt.png">
## 2. Fase 1 – Git Flow
### 2.1 Features - Quais features foram criadas?   
- Liste as branches de features e descreva as alterações.:
  - Feature/DTOEvento: Feature utilizada para a criação e configuração do DTO da entidade Evento;
  - Feature/EventoControler: Feature utilizada para a criação do Controller da entidade Evento e depois para a implementação do DTO no controller;
- 
- Prints dos commits:
<img src="imagens/featuresVini.png">
<img src="imagens/featuresVini2.png">
### 2.2 Conflitos - Onde ocorreram conflitos (arquivos/linhas)?   
- Como os conflitos foram resolvidos?   
- Inclua prints mostrando os marcadores (`<<<<<<<`, `=======`, `>>>>>>>`) e o
arquivo final após resolução.
### 2.3 Release - Criada branch `release/2.0`?   
- Alterações preparatórias (ex.: versão, documentação).   
- Integração com `main` e geração da tag `v2.0`.
### 2.4 Hotfix - Qual foi o problema corrigido?   
- Como foi feito o merge do hotfix em `main` e `develop`?   
- Inclua prints e comandos usados.
### 2.5 Uso de Rebase - Em qual feature aplicaram `git rebase`?   
- Explique o que mudou no histórico.
##   
3. Fase 2 – Trunk-Based Development
### 3.1 Branches Curtos - Quais branches foram criados a partir de `main`?   
- Quantos commits cada um teve?
### 3.2 Squash - Qual merge foi feito usando **squash**?   
- Explique por que foi escolhido squash em vez de merge normal.
### 3.3 Tag Final - Tag criada: `v3.0`.
- Prints do histórico.

## 4. Histórico de Commits
   Inclua saída do comando:
```bash 
git log --oneline --graph --all