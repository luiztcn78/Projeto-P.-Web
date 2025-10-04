# RELATÓRIO DE PROJETO – Estratégias de Branching em Git
##      
Identificação - **Integrantes da equipe**:   - Nome 1   - Nome 2   - Nome 3   - **Repositório GitHub**: [link aqui]
##   
1. Estrutura Inicial - Branch principal (`main`) criada e configurada?   - Branch `develop` criada a partir de `main`?   - Descreva como foi feita a configuração inicial (comandos usados e prints).
##   
2. Fase 1 – Git Flow
### 2.1 Features - Quais features foram criadas?   - Liste as branches de features e descreva as alterações.   - Prints dos commits:
### 2.2 Conflitos - Onde ocorreram conflitos (arquivos/linhas)?   - Como os conflitos foram resolvidos?   - Inclua prints mostrando os marcadores (`<<<<<<<`, `=======`, `>>>>>>>`) e o
arquivo final após resolução.
### 2.3 Release - Criada branch `release/2.0`?   - Alterações preparatórias (ex.: versão, documentação).   - Integração com `main` e geração da tag `v2.0`.
### 2.4 Hotfix - Qual foi o problema corrigido?   - Como foi feito o merge do hotfix em `main` e `develop`?   - Inclua prints e comandos usados.
### 2.5 Uso de Rebase - Em qual feature aplicaram `git rebase`?   - Explique o que mudou no histórico.
##   
3. Fase 2 – Trunk-Based Development
### 3.1 Branches Curtos - Quais branches foram criados a partir de `main`?   - Quantos commits cada um teve?
### 3.2 Squash - Qual merge foi feito usando **squash**?   - Explique por que foi escolhido squash em vez de merge normal.
### 3.3 Tag Final - Tag criada: `v3.0`.
- Prints do histórico.
##   
4. Histórico de Commits
   Inclua saída do comando:
```bash 
git log --oneline --graph --all